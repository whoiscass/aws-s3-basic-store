package com.cass.aws.upload.awsConfig;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.cass.aws.upload.fileStoreService.IFilesStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class AwsFileStoreService implements IFilesStoreService {

    @Autowired private final AmazonS3 s3;

    @Autowired private final BucketConfig bucketConfig;

    AwsFileStoreService(AmazonS3 s3, BucketConfig bucketConfig) {
        this.s3 = s3;
        this.bucketConfig = bucketConfig;
    }

    @Override
    public String uploadFile(String fileName,
                           Optional<Map<String,String>> optionalMetadata,
                           MultipartFile file) {

        ObjectMetadata metadata = new ObjectMetadata();
        optionalMetadata.ifPresent(map -> {
            if(!map.isEmpty()) {
                map.forEach(metadata::addUserMetadata);
            }
        });

        final String bucket =  bucketConfig.getBucketName();
        final String filePath =  bucketConfig.getAwsFolderPathName();

        try {
            final InputStream inputStream = file.getInputStream();
            s3.putObject(new PutObjectRequest(String.format("%s/%s", bucket, filePath), fileName, inputStream, metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("failed to store file to s3 Bucket", e);
        }

        return s3.getUrl(bucket, String.format("%s/%s", filePath, fileName)).toString();
    }

}

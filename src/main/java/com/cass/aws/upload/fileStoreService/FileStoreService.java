package com.cass.aws.upload.fileStoreService;

import com.cass.aws.upload.awsConfig.AwsFileStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStoreService {

    @Autowired private final AwsFileStoreService awsFileStoreService;

    FileStoreService(AwsFileStoreService awsFileStoreService) {
        this.awsFileStoreService = awsFileStoreService;
    }

    public String awsS3UploadFile(String fileName,
                             Optional<Map<String,String>> optionalMetadata,
                                  MultipartFile file) {
        return awsFileStoreService.uploadFile(fileName, optionalMetadata, file);
    }
}

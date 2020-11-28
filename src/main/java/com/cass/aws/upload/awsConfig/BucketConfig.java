package com.cass.aws.upload.awsConfig;

import org.springframework.stereotype.Service;

@Service
public class BucketConfig {

    private final String BucketName;
    private final String AwsFolderPathName;

    BucketConfig() {
        this.BucketName = "aws-java-storage";
        this.AwsFolderPathName = "testing";
    }

    public String getBucketName() {
        return BucketName;
    }

    public String getAwsFolderPathName() {
        return AwsFolderPathName;
    }
}

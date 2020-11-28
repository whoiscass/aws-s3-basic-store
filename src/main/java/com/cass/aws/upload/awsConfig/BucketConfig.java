package com.cass.aws.upload.awsConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BucketConfig {

    private final String BucketName;
    private final String AwsFolderPathName;

    BucketConfig() {
        this.BucketName = "aws-java-storage";
        this.AwsFolderPathName = "testing";
    }

    @Bean
    public String getBucketName() {
        return BucketName;
    }

    @Bean
    public String getAwsFolderPathName() {
        return AwsFolderPathName;
    }
}

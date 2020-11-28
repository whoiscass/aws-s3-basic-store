package com.cass.aws.upload.awsConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.core.env.Environment;

@Configuration
public class AwsConfig {

    @Autowired private final Environment env;

    private final String accessKey;
    private final String secretKey;
    private final String region;

    public AwsConfig(Environment env) {
        this.env = env;
        this.accessKey = this.env.getProperty("AWS_ACCESS_KEY");
        this.secretKey = this.env.getProperty("AWS_SECRET_KEY");
        this.region = this.env.getProperty("AWS_S3_REGION");
    }

    @Bean
    public AmazonS3 awsConfigS3() {

        AWSCredentials awsCredentials = new BasicAWSCredentials(
                accessKey,
                secretKey
        );

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(region)
                .build();
    }

}

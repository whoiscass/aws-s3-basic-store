package com.cass.aws.upload.awsConfig;

public enum BucketConfig {

    AWS_BUCKET_NAME("aws-java-storage"),
    AWS_FOLDER_PATH_NAME("testing");

    private String value;

    BucketConfig(String value) { this.value = value; }

    public String getBucketName() {
        return value;
    }

    public String getAwsFolderPathName() { return value; }
}

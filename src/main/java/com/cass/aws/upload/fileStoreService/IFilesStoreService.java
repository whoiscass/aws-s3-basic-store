package com.cass.aws.upload.fileStoreService;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

public interface IFilesStoreService {
    public String uploadFile(String fileName,
                             Optional<Map<String,String>> optionalMetadata,
                             InputStream inputStream);
}

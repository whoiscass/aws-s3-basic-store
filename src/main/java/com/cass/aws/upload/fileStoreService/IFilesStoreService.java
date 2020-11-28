package com.cass.aws.upload.fileStoreService;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;

public interface IFilesStoreService {
    String uploadFile(String fileName,
                             Optional<Map<String,String>> optionalMetadata,
                             MultipartFile file);
}

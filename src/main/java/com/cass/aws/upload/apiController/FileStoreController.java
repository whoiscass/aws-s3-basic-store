package com.cass.aws.upload.apiController;

import com.cass.aws.upload.fileStoreService.FileStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStoreController {

    @Autowired private final FileStoreService fileStoreService;

    public FileStoreController(FileStoreService fileStoreService) {
        this.fileStoreService = fileStoreService;
    }

    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) {

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-type", file.getContentType());
        metadata.put("Content-length", String.valueOf(file.getSize()));

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String fileName = String.format("%s_%s", dateFormatter.format(new Date()), file.getOriginalFilename());;

        final String response = fileStoreService.awsS3UploadFile(fileName, Optional.of(metadata), file);

        if(response == null || response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "failed to store file to s3 Bucket"));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("response",
                        fileStoreService.awsS3UploadFile(fileName, Optional.of(metadata), file)));

    }
}

package com.cass.aws.upload.apiController;

import com.cass.aws.upload.fileStoreService.FileStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import java.io.IOException;

@RestController
@RequestMapping("api/v1")
public class FileStoreController {

    @Autowired private final FileStoreService fileStoreService;

    public FileStoreController(FileStoreService fileStoreService) {
        this.fileStoreService = fileStoreService;
    }

    @PostMapping(path = "aws-s3/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) {

        String resourceUrl = null;

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-type", file.getContentType());
        metadata.put("Content-length", String.valueOf(file.getSize()));

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String fileName = String.format("%s_%s", dateFormatter.format(new Date()), file.getOriginalFilename());

        try {
            resourceUrl = fileStoreService.awsS3UploadFile(fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("resourceUrl", resourceUrl));

    }
}

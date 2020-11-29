package com.cass.aws.upload.routes;

import com.cass.aws.upload.apiController.FileStoreController;
import com.cass.aws.upload.apiController.HealthCheckController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1")
public class Routes {

    @Autowired private final HealthCheckController healthCheckController;
    @Autowired private final FileStoreController fileStoreController;

    Routes(HealthCheckController healthCheckController,
           FileStoreController fileStoreController) {

        this.healthCheckController = healthCheckController;
        this.fileStoreController = fileStoreController;
    }

    @GetMapping("ping") public String ping() {
        return healthCheckController.ping();
    }

    @PostMapping(path = "aws-s3/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) {
        return fileStoreController.upload(file);
    }

}

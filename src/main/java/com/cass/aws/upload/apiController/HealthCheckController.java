package com.cass.aws.upload.apiController;

import org.springframework.stereotype.Service;

@Service
public class HealthCheckController {

    public String ping() {
        return "pong";
    }

}

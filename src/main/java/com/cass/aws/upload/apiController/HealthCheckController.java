package com.cass.aws.upload.apiController;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class HealthCheckController {

    public String ping() {
        return "pong";
    }

}

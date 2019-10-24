package com.jramos.configserver.ConfigServerBucket.controller;

import com.jramos.configserver.ConfigServerBucket.services.interfaces.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RefreshScope
@RestController
public class ConfigController {

    @Autowired
    private EnvironmentService service;

    @GetMapping(path = "/{appName}/{profile}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Environment> getConfiguration(@PathVariable String profile, @PathVariable String appName) throws IOException {

        return new ResponseEntity<>(service.getProfileEnvironment(appName, profile), HttpStatus.OK);
    }
}

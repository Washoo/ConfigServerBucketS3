package com.jramos.configserver.ConfigServerBucket.services.interfaces;

import org.springframework.cloud.config.environment.Environment;

import java.io.IOException;

public interface EnvironmentService {

    Environment getProfileEnvironment(String appName, String profile) throws IOException;
}

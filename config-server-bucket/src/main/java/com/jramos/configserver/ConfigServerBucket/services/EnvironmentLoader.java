package com.jramos.configserver.ConfigServerBucket.services;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.jramos.configserver.ConfigServerBucket.adapter.interfaces.S3BucketAdapter;
import com.jramos.configserver.ConfigServerBucket.services.interfaces.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EnvironmentLoader implements EnvironmentService {

    @Autowired
    private S3BucketAdapter s3BucketAdapter;

    @Override
    public Environment getProfileEnvironment(String appName, String profile) {
        return buildEnvironmentContent(appName, profile);
    }

    private Environment buildEnvironmentContent(String appName, String profile) {

        PropertySource source = new PropertySource("master", buildSourceMap(appName, profile));
        Environment environment = new Environment(appName, profile);
        environment.add(source);
        return environment;
    }

    private Map<?, ?> buildSourceMap(String appName, String profile) {

        String key = "";
        Map<String, String> source = new HashMap<>();
        for (String line : new BufferedReader(new InputStreamReader(this.getFileContent(appName, profile))).lines().collect(Collectors.toList())) {

            if (line.endsWith(":")) {
                key += line.replace(":", ".");
            } else if (line.contains(":")) {
                key += line.substring(0, line.indexOf(":")).trim();
                source.put(key, line.substring(line.indexOf(":") + 1, line.length()).trim());
            }
        }

        return source;
    }

    private S3ObjectInputStream getFileContent(String appName, String profile) {
        return s3BucketAdapter.getFileObject(appName, profile);
    }
}

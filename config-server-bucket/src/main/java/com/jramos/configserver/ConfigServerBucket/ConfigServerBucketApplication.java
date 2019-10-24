package com.jramos.configserver.ConfigServerBucket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerBucketApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerBucketApplication.class, args);
	}

}

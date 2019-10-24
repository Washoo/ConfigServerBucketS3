package com.jramos.configserver.ConfigServerBucket.client;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsClient {

    @Bean
    public AmazonS3 getAwsClient() {

        BasicAWSCredentials awsCred = new BasicAWSCredentials("AKIAYLDCF4BZEX24WT5W", "efaS9mrsaEiuF17ItjCzBVeDJrWXMlQsh3iDJuN2");
        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(awsCred))
                .build();
        return amazonS3;
    }
}

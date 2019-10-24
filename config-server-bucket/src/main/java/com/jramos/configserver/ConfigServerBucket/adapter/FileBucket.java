package com.jramos.configserver.ConfigServerBucket.adapter;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.jramos.configserver.ConfigServerBucket.adapter.interfaces.S3BucketAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileBucket implements S3BucketAdapter {

    @Autowired
    private AmazonS3 awsClient;

    public S3ObjectInputStream getFileObject(String appName, String profile) {

        S3Object object = awsClient.getObject(new GetObjectRequest("config-server-spring", profile + "/" + appName + ".yml"));
        S3ObjectInputStream content = object.getObjectContent();
        return content;
    }
}

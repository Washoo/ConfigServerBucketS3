package com.jramos.configserver.ConfigServerBucket.adapter.interfaces;

import com.amazonaws.services.s3.model.S3ObjectInputStream;

public interface S3BucketAdapter {

    S3ObjectInputStream getFileObject(String appName, String profile);
}

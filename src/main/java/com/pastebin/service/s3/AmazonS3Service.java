package com.pastebin.service.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AmazonS3Service {

    private final AmazonS3 amazonS3;
    private final String bucketName = "your-bucket-name";

    public String uploadContent(String content) {
        String contentId = generateUniqueId();
        amazonS3.putObject(bucketName, contentId, content);
        return contentId;
    }

    public String getContent(String contentId) {
        S3Object s3Object = amazonS3.getObject(bucketName, contentId);
        InputStream inputStream = s3Object.getObjectContent();
        try {
            return new String(inputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteContent(String contentId) {
        amazonS3.deleteObject(bucketName, contentId);
    }

    private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}


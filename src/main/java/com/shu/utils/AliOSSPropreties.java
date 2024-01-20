package com.shu.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliOSSPropreties {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String catalogueCover;
    private String catalogueAvatar;
    private String catalogueBooks;
    private String catalogueMaterial;
}

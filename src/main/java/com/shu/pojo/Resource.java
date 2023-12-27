package com.shu.pojo;


import lombok.Data;

@Data
public class Resource {

    private Integer id;
    private String school;
    private String major;
    private String subject;
    private Float overallRating;
    private Integer downloadCount;
    private Integer clickCount;
    private String uploader;
    private Integer pointsRequired;
    private String summary;
    private String coverUrl;
    private String evaluation;


}


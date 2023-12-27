package com.shu.pojo;

import lombok.Data;

@Data
public class Book {

    private Integer id;
    private String name;
    private String isbn;
    private String publisher;
    private Float overallRating;
    private Integer downloadCount;
    private Integer clickCount;
    private String uploader;
    private Integer pointsRequired;
    private String summary;
    private String coverUrl;
    private String evaluation;
    private String category;

}


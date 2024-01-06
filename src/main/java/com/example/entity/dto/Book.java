package com.example.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Integer bookId;
    private String bookName;
    private String bookAuthor;
    private String bookVersion;
    private String bookPulishHouse;
    private String bookGrade;
    private Integer bookDownloadNum;
    private Integer bookClickNum;
    private String bookUploader;
    private Integer bookPoints;
    private String bookProfile;
    private String bookCoverUrl;
    private String bookCoverUuid;
    private String bookFileUuid;
    private String categoryName;
    private String categoryAlias;
    private LocalDate createTime;
    private LocalDate updateTime;
}

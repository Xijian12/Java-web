package com.example.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookReview {
    private int id;
    private String bookVersion;
    private String userComment;
    private int userGrade; // 评分为1到5
    private String userNickname;
    private String userEmail;
    private int bookId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
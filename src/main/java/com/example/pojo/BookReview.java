package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}

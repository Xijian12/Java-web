package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookReview {
    private int id;
    private String bookEdition;
    private String review;
    private int rating; // 评分为1到5
    private String userNickname;
    private String userAccount;
    private int bookId;
}

package com.shu.entity.vo.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookReviewwithavtar {
    private int id;
    private String bookVersion;
    private String userComment;
    private int userGrade; // 评分为1到5
    private String userNickname;
    private String userEmail;
    private int bookId;
    private  String avatarUrl;
    private LocalDateTime createTime;
}

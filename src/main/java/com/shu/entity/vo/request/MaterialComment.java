package com.shu.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialComment {
    private Integer commentId;
    private String school;
    private String major;
    private String subject;
    private String materialComment;
    private double materialGrade;
    private String userNickname;
    private String userEmail;
    private String userAvatarUrl;
    private Integer type;
    private Integer userId;
    private String role;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

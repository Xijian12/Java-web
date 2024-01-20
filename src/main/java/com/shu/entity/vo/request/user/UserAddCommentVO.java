package com.shu.entity.vo.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddCommentVO {
        private Integer materialId;
        private String userNickname;
        private String userEmail;
        private String userComment;
        private double userGrade;
        private Integer type;
}

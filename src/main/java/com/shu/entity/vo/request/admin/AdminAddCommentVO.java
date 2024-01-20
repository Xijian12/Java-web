package com.shu.entity.vo.request.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminAddCommentVO {
    private Integer materialId;
    private String adminAccount;
    private String adminComment;
    private double adminGrade;
    private Integer type;
}

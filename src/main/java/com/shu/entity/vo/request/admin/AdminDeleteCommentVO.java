package com.shu.entity.vo.request.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDeleteCommentVO {
    private List<Integer> commentIds;
    private String adminAccount;
}

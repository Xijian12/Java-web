package com.shu.entity.vo.request.admin;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserInfoVO {
    private String oldUserName;
    private String newUserName;
    private String newPassword;
    private Integer points;

    // Getters and Setters
}

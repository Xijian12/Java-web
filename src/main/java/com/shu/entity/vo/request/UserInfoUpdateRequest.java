package com.shu.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoUpdateRequest {
    private String oldUserName;
    private String newUserName;
    private String newPassword;
    private Integer points;

    // getters and setters
}


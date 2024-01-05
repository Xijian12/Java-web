package com.example.entity.vo.request;

import lombok.Data;

@Data
public class UpdateAvatarVO {
    private String username;
    private String newAvatarUrl;
    private String newAvatorUuid;
}

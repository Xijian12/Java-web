package com.shu.entity.vo.request;

import lombok.Data;

@Data
public class UpdateAvatarVO {
    private String username;
    private String newAvatarUrl;
    private String newAvatarUuid;

    public UpdateAvatarVO(String username, String fileUrl, String imageUUID) {
        this.username = username;
        this.newAvatarUrl=fileUrl;
        this.newAvatarUuid=imageUUID;
    }
}
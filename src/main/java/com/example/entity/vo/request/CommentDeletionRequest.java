package com.example.entity.vo.request;

import java.util.List;

public class CommentDeletionRequest {
    private List<Integer> ids;
    private String adminAccount;
// Getters and setters

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }
}
package com.shu.entity.vo.request;

import java.util.List;

public class DeleteBooksRequest {
    private String adminAccount;
    private List<Integer> ids; // 使用列表来存储多个ID

    // Getter和Setter方法

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
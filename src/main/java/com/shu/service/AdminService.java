package com.shu.service;

import com.shu.pojo.Admin;

public interface AdminService {

    Admin findAdminByAccount(String adminAccount);

    void setAdmin(String adminAccount, String adminPassword);

    void addAdmin(String adminAccount, String adminPassword);

    void register(String adminAccount, String adminPassword);


}








package com.shu.service;

import com.shu.pojo.Admin;

/**
 * @author 85131
 */
public interface AdminService {

    Admin findAdminByAccount(String adminAccount);

    void setAdmin(String adminAccount, String adminPassword);

    void addAdmin(String adminAccount, String adminPassword);

    void register(String adminAccount, String adminPassword);




    String findByAccount(String adminAccount);

    void add(String adminAccount, String adminPassword);
}








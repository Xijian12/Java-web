package com.shu.service.impl;

import com.shu.mapper.AdminMapper;
import com.shu.pojo.Admin;
import com.shu.service.AdminService;
import com.shu.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 85131
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findAdminByAccount(String adminAccount) {
        Admin admin = adminMapper.findByAccount(adminAccount);
        System.out.println(admin.getAdminAccount());
        System.out.println(admin.getAdminPassword());
        return admin;
    }
    @Override
    public void setAdmin(String adminAccount, String adminPassword) {

    }
    @Override
    public void addAdmin(String adminAccount, String adminPassword) {
        String md5String= Md5Util.getMD5String(adminPassword);
        adminMapper.add(adminAccount,md5String);
    }

    @Override
    public void register(String adminAccount, String adminPassword) {
        String md5String= Md5Util.getMD5String(adminPassword);
        adminMapper.add(adminAccount,md5String);
    }

    @Override
    public String findByAccount(String adminAccount) {
        return adminMapper.findByAccountString(adminAccount);
    }

    @Override
    public void add(String adminAccount, String adminPassword) {
        String md5String= Md5Util.getMD5String(adminPassword);
        adminMapper.add(adminAccount,md5String);

    }


}

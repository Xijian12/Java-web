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
    public Admin findAdminByAccount(String account) {
        Admin admin = adminMapper.findByAccount(account);
        return admin;
    }
    @Override
    public void setAdmin(String account, String password) {

    }
    @Override
    public void addAdmin(String account, String password) {
        String md5String= Md5Util.getMD5String(password);
        adminMapper.add(account,password);
    }

    @Override
    public void register(String account, String password) {
        String md5String= Md5Util.getMD5String(password);
        adminMapper.add(account,password);
    }


}

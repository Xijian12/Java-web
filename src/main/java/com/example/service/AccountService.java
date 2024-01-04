package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.dto.Account;
import com.example.entity.vo.request.*;
import com.example.entity.vo.response.DisplayAccountByAdminVO;
import com.example.entity.vo.response.DisplayAccountByUserVO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AccountService extends IService<Account>, UserDetailsService {
    Account findAccountByNameOrEmail(String text);
    String registerEmailVerifyCode(String type, String email, String address);
    String registerEmailAccount(EmailRegisterVO info);
    String resetEmailAccountPassword(EmailResetVO info);
    String resetConfirm(ConfirmResetVO info);

    DisplayAccountByUserVO userInfo(String username) throws UsernameNotFoundException;

    DisplayAccountByAdminVO adminInfo(String username) throws UsernameNotFoundException;

    String userInfoByName(String name);
}

package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.dto.Account;
import com.example.entity.vo.request.ConfirmResetVO;
import com.example.entity.vo.request.EmailRegisterVO;
import com.example.entity.vo.request.EmailResetVO;
import com.example.entity.vo.request.user.MaterialPage;
import com.example.entity.vo.response.DisplayAccountByAdminVO;
import com.example.entity.vo.response.DisplayAccountByUserVO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface AccountService extends IService<Account>, UserDetailsService {
    Account findAccountByNameOrEmail(String text);
    String registerEmailVerifyCode(String type, String email, String address);
    String registerEmailAccount(EmailRegisterVO info);
    String resetEmailAccountPassword(EmailResetVO info);
    String resetConfirm(ConfirmResetVO info);

    DisplayAccountByUserVO userInfo(String username) throws UsernameNotFoundException;

    DisplayAccountByAdminVO adminInfo(String username) throws UsernameNotFoundException;

    String userInfoByName(String name);

    String updateUserInfo(String username, String newPassword, String password, Integer points);

    MaterialPage getAllAccounts(Integer page, Integer pageSize);

    boolean updateAvatar(String username, String newAvatarUrl,String newAvatarUuid);

    String deleteUserAndRelatedInfo(String email);

    List<Account> findTopNAccounts(int i);
}

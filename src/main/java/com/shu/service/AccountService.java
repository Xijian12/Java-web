package com.shu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shu.entity.dto.Account;
import com.shu.entity.vo.request.ConfirmResetVO;
import com.shu.entity.vo.request.EmailRegisterVO;
import com.shu.entity.vo.request.EmailResetVO;
import com.shu.entity.vo.request.user.MaterialPage;
import com.shu.entity.vo.response.DisplayAccountByAdminVO;
import com.shu.entity.vo.response.DisplayAccountByUserVO;
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

    DisplayAccountByAdminVO adminInfoByEmail(String email) throws UsernameNotFoundException;

    String userInfoByName(String name);

    String updateUserInfo(String username, String newPassword, String password, Integer points);

    MaterialPage getAllAccounts(Integer page, Integer pageSize);

    boolean updateAvatar(String username, String newAvatarUrl,String newAvatarUuid);

    String deleteUserAndRelatedInfo(String email);

    List<Account> findTopNAccounts(int i);

    String signIn(String username);

    //更新用户积分
    String updateUserPoints(String userEmail,Integer points);
}

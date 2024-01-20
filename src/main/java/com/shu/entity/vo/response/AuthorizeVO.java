package com.shu.entity.vo.response;

import lombok.Data;

import java.util.Date;

/**
 * 登录验证成功的用户信息响应
 * @author 85131
 */
@Data
public class AuthorizeVO {
    String username;
    String role;
    String token;
    String email;
    Integer points;
    Date expire;
}

package com.shu.utils;

import com.shu.mapper.EmailVerifyCodeMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailVerifyCodeCleanTask {

    @Resource
    private EmailVerifyCodeMapper emailVerifyCodeMapper;

    /**
     * 每 5 分钟执行一次
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void cleanExpiredVerifyCode() {
        int count = emailVerifyCodeMapper.deleteVerifyCodeExpired();
        if (count > 0) {
            log.info("清理过期邮箱验证码 {} 条", count);
        }
    }
}
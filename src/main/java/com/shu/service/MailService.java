package com.shu.service;

import jakarta.annotation.Resource;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class MailService {

    private static final int MAX_RETRY = 3; // 最大重试次数

    @Resource
    private JavaMailSender sender;

    @Value("${spring.mail.from-name}")
    private String fromName;

    @Value("${spring.mail.username}")
    private String fromEmail;

    /**
     * 异步发送邮件，失败自动重试
     */
    @Async("mailExecutor")
    public void sendMail(String type, String email, Integer code) {
        log.info("当前线程: {}", Thread.currentThread().getName());
        for (int attempt = 1; attempt <= MAX_RETRY; attempt++) {
            try {
                MimeMessage message = buildMessage(type, email, code);
                if (message == null) {
                    log.warn("未知邮件类型: type={}", type);
                    return;
                }

                sender.send(message);
                log.info("邮件发送成功 email={}, attempt={}", email, attempt);
                return;

            } catch (Exception e) {

                log.error("邮件发送失败 email={}, attempt={}", email, attempt, e);

                if (attempt == MAX_RETRY) {
                    log.error("邮件发送彻底失败，已达最大重试次数 email={}", email);
                }

                long base = 2000L;
                long jitter = ThreadLocalRandom.current().nextLong(500);
                sleepQuietly(base + jitter);
            }
        }
    }

    private void sleepQuietly(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }

    private MimeMessage buildMessage(String type, String email, Integer code) throws Exception {
        return switch (type) {
            case "register" -> createMessage(
                    "欢迎注册小SHU书的网站",
                    "您的注册验证码为: " + code + "，有效时间3分钟。",
                    email
            );
            case "reset" -> createMessage(
                    "小SHU书密码重置",
                    "验证码: " + code + "，有效时间3分钟。",
                    email
            );
            default -> null;
        };
    }

    private MimeMessage createMessage(String title, String content, String email) throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

        // 显示名 + 真实邮箱
        helper.setFrom(new InternetAddress(fromEmail, fromName, "UTF-8"));

        helper.setTo(email);
        helper.setSubject(title);
        helper.setText(content, false);

        return message;
    }
}


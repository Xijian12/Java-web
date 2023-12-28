package com.shu.controller;

import com.shu.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @author 85131
 */
//登录认证
@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result<String> list(/*@RequestHeader(name="Authorization") String token, HttpServletResponse response*/) throws UnsupportedEncodingException {
        //验证token
//        try {
//            Map<String, Object> claims = JwtUtil.parseToken(token);
//            return Result.success("所有文章接口");
//        }catch(Exception e){
//            //http响应状态码为401
//            response.setStatus(401);
//            return Result.error("未登录");
//        }
        return Result.success("所有文章接口");
    }
}

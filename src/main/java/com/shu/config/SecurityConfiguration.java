package com.shu.config;

import com.shu.entity.RestBean;
import com.shu.entity.dto.Account;
import com.shu.entity.vo.response.AuthorizeVO;
import com.shu.filter.JwtAuthenticationFilter;
import com.shu.filter.RequestLogFilter;
import com.shu.service.AccountService;
import com.shu.utils.Const;
import com.shu.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * SpringSecurity相关配置
 */
@Configuration
public class SecurityConfiguration {

    @Resource
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Resource
    RequestLogFilter requestLogFilter;

    @Resource
    JwtUtils utils;

    @Resource
    AccountService service;

    /**
     * 针对于 SpringSecurity 6 的新版配置方法
     * @param http 配置器
     * @return 自动构建的内置过滤器链
     * @throws Exception 可能的异常
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(conf -> conf
                        .requestMatchers("/api/auth/**", "/error").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/admin/userInfo").permitAll() // 添加这一行
                        .requestMatchers("/user/userInfo").permitAll() // 添加这一行
                        .requestMatchers("/user/updateUserInfo").permitAll() // 添加这一行
                        .requestMatchers("/user/deleteUser").permitAll() // 用户注销
                        .requestMatchers("/admin/deleteUser").permitAll() // 管理员删除用户
                        .requestMatchers("/admin/adminInfo").permitAll() // 添加这一行
                        .requestMatchers("/admin/adminInfoByEmail").permitAll() // 添加这一行
                        .requestMatchers("/admin/updateUserInfo").permitAll() // 添加这一行
                        .requestMatchers("/admin/updateAvatar").permitAll() // 添加这一行
                        .requestMatchers("/admin/showAllAccounts").permitAll() // 添加这一行
                        .requestMatchers("/admin/signIn").permitAll() // 添加这一行
                        .requestMatchers("/admin/showTopNAccounts").permitAll() // 添加这一行
                        .requestMatchers("/upload/updateAvatar").permitAll() // 添加这一行
                        .requestMatchers("/upload/uploadAvatar").permitAll() // 添加这一行
                        .requestMatchers("/upload/uploadBookcover").permitAll() // 添加这一行
                        .requestMatchers("/upload/updateBookcover").permitAll() // 添加这一行
                        .requestMatchers("/upload/uploadBook").permitAll() // 添加这一行
                        .requestMatchers("/upload/updateBook").permitAll() // 添加这一行
                        .requestMatchers("/book/**").permitAll() // 添加这一行
                        .requestMatchers("/book/admin").permitAll() // 添加这一行
                        .requestMatchers("/book/{id}").permitAll()
                        .requestMatchers("/book/user").permitAll()
                        .requestMatchers("/category").permitAll()
                        .requestMatchers("/category/detail").permitAll()
                        .requestMatchers("/category/update").permitAll()
                        .requestMatchers("/category/{categoryIds}").permitAll()
                        .requestMatchers("/book/comment/**").permitAll()
                        .requestMatchers("/upload/uploadBookcover").permitAll() // 上传图书封面访问路径
                        .requestMatchers("/upload/updateBookcover").permitAll() // 更新图书封面访问路径
                        .requestMatchers("/upload/uploadBook").permitAll() // 上传图书访问路径
                        .requestMatchers("/upload/updateBook").permitAll() // 更新图书访问路径
                        .requestMatchers("/upload/uploadMaterialcover").permitAll() // 上传资料封面访问路径
                        .requestMatchers("/upload/updateMaterialcover").permitAll() // 更新资料封面访问路径
                        .requestMatchers("/upload/uploadMaterial").permitAll() // 上传资料访问路径
                        .requestMatchers("/upload/updateMaterial").permitAll() // 更新资料访问路径
                        .requestMatchers("/material").permitAll() // 添加资料信息访问路径
                        .requestMatchers("/material/{materialId}").permitAll() // 添加访问资料信息访问路径
                        .requestMatchers("/material/user").permitAll() // 添加用户访问资料信息访问路径
                        .requestMatchers("/material/admin").permitAll() // 添加管理员访问资料信息访问路径
                        .requestMatchers("/material/download").permitAll() // 添加下载资料信息访问路径
                        .requestMatchers("/material/comment/user").permitAll() // 用户给资料添加评论的访问路径
                        .requestMatchers("/material/comment/admin").permitAll() // 用户给资料添加评论的访问路径
                        .requestMatchers("/material/comment").permitAll() // 用户给资料添加评论的访问路径
                        .requestMatchers("/material/getClickDownloadBySchool").permitAll() //查询学校点击量下载量
                        .requestMatchers("/material/getClickDownloadByCategory").permitAll() //查询学科点击量下载量
                        .requestMatchers("/material/highest/{n}").permitAll() //查询综合评分最高的前N类资料
                        .requestMatchers("/material/downClicks/{materialId}").permitAll() // 根据ID查询资料的下载量和点击量
                        .requestMatchers("/material/{materialId}").permitAll() // 根据ID查询资料（已经实现）
                        .requestMatchers("/material/userUpload").permitAll() // 查询某个用户上传的所有资料
                        .requestMatchers("/material/materialslist").permitAll() // 资料列表查询(条件分页)
                        .requestMatchers("/material/school").permitAll() // 查询所有学校
                        .requestMatchers("/material/school/major").permitAll() // 查询学校下面的所有专业
                        .requestMatchers("/material/school/major/subject").permitAll() // 查询学校和专业下面的所有学科
                        .requestMatchers("/book/downloadRecord").permitAll()
                        .requestMatchers("/book/collectRecord").permitAll()
                        .requestMatchers("/book/clickNum").permitAll()
                        .requestMatchers("/book/downloadNum").permitAll()
                        .requestMatchers("/material/clickNum").permitAll()
                        .requestMatchers("/material/downloadNum").permitAll()
                        .requestMatchers("/material/SMS").permitAll()
                .anyRequest().hasAnyRole(Const.ROLE_DEFAULT)
                )
                .formLogin(conf -> conf
                        .loginProcessingUrl("/api/auth/login")
                        .failureHandler(this::handleProcess)
                        .successHandler(this::handleProcess)
                        .permitAll()
                )
                .logout(conf -> conf
                        .logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler(this::onLogoutSuccess)
                )
                .exceptionHandling(conf -> conf
                        .accessDeniedHandler(this::handleProcess)
                        .authenticationEntryPoint(this::handleProcess)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(conf -> conf
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(requestLogFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, RequestLogFilter.class)
                .build();
    }





    /**
     * 将多种类型的Handler整合到同一个方法中，包含：
     * - 登录成功
     * - 登录失败
     * - 未登录拦截/无权限拦截
     * @param request 请求
     * @param response 响应
     * @param exceptionOrAuthentication 异常或是验证实体
     * @throws IOException 可能的异常
     */
    private void handleProcess(HttpServletRequest request,
                               HttpServletResponse response,
                               Object exceptionOrAuthentication) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if(exceptionOrAuthentication instanceof AccessDeniedException exception) {
            writer.write(RestBean
                    .forbidden(exception.getMessage()).asJsonString());
        } else if(exceptionOrAuthentication instanceof Exception exception) {
            writer.write(RestBean
                    .unauthorized(exception.getMessage()).asJsonString());
        } else if(exceptionOrAuthentication instanceof Authentication authentication){
            User user = (User) authentication.getPrincipal();
            Account account = service.findAccountByNameOrEmail(user.getUsername());
            String jwt = utils.createJwt(user, account.getUsername(), account.getId());
            if(jwt == null) {
                writer.write(RestBean.forbidden("登录验证频繁，请稍后再试").asJsonString());
            } else {
                AuthorizeVO vo = account.asViewObject(AuthorizeVO.class, o -> o.setToken(jwt));
                vo.setExpire(utils.expireTime());
                vo.setEmail(account.getEmail());
                vo.setPoints(account.getPoints());
                writer.write(RestBean.success(vo).asJsonString());
            }
        }
    }

    /**
     * 退出登录处理，将对应的Jwt令牌列入黑名单不再使用
     * @param request 请求
     * @param response 响应
     * @param authentication 验证实体
     * @throws IOException 可能的异常
     */
    private void onLogoutSuccess(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String authorization = request.getHeader("Authorization");
        if(utils.invalidateJwt(authorization)) {
            writer.write(RestBean.success("退出登录成功").asJsonString());
            return;
        }
        writer.write(RestBean.failure(400, "退出登录失败").asJsonString());
    }
}

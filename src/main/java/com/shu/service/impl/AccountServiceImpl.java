package com.shu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.entity.dto.Account;
import com.shu.entity.vo.request.*;
import com.shu.entity.vo.request.user.MaterialPage;
import com.shu.entity.vo.response.DisplayAccountByAdminVO;
import com.shu.entity.vo.response.DisplayAccountByUserVO;
import com.shu.mapper.AccountMapper;
import com.shu.mapper.MaterialMapper;
import com.shu.service.AccountService;
import com.shu.utils.Const;
import com.shu.utils.FlowUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 账户信息处理相关服务
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    //验证邮件发送冷却时间限制，秒为单位
    @Value("${spring.web.verify.mail-limit}")
    int verifyLimit;

    @Resource
    AmqpTemplate rabbitTemplate;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    FlowUtils flow;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private MaterialMapper materialMapper;

    /**
     * 从数据库中通过用户名或邮箱查找用户详细信息
     * @param username 用户名
     * @return 用户详细信息
     * @throws UsernameNotFoundException 如果用户未找到则抛出此异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = this.findAccountByNameOrEmail(username);
        if(account == null)
            throw new UsernameNotFoundException("用户名或密码错误");
        return User
                .withUsername(username)
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }

   // 生成注册验证码存入Redis中，并将邮件发送请求提交到消息队列等待发送
    @Override
    public String registerEmailVerifyCode(String type, String email, String address){
        synchronized (address.intern()) {
            if(!this.verifyLimit(address))
                return "请求频繁，请稍后再试";
            Random random = new Random();
            int code = random.nextInt(899999) + 100000;
            Map<String, Object> data = Map.of("type",type,"email", email, "code", code);
            rabbitTemplate.convertAndSend(Const.MQ_MAIL, data);
            stringRedisTemplate.opsForValue()
                    .set(Const.VERIFY_EMAIL_DATA + email, String.valueOf(code), 3, TimeUnit.MINUTES);
            return null;
        }
    }

    /**
     * 邮件验证码注册账号操作，需要检查验证码是否正确以及邮箱、用户名是否存在重名
     * @param info 注册基本信息
     * @return 操作结果，null表示正常，否则为错误原因
     */
    public String registerEmailAccount(EmailRegisterVO info){
        String email = info.getEmail();
        String code = this.getEmailVerifyCode(email);
        if(code == null) return "请先获取验证码";
        if(!code.equals(info.getCode())) return "验证码错误，请重新输入";
        if(this.existsAccountByEmail(email)) return "该邮件地址已被注册";
        String username = info.getUsername();
        if(this.existsAccountByUsername(username)) return "该用户名已被他人使用，请重新更换";
        String password = passwordEncoder.encode(info.getPassword());
        Account account = new Account(null, info.getUsername(),
                password, email, Const.ROLE_DEFAULT, new Date());
        if(!this.save(account)) {
            return "内部错误，注册失败";
        } else {
            this.deleteEmailVerifyCode(email);
            return null;
        }
    }

    /**
     * 邮件验证码重置密码操作，需要检查验证码是否正确
     * @param info 重置基本信息
     * @return 操作结果，null表示正常，否则为错误原因
     */
    @Override
    public String resetEmailAccountPassword(EmailResetVO info) {
        String verify = resetConfirm(new ConfirmResetVO(info.getEmail(), info.getCode()));
        if(verify != null) return verify;
        String email = info.getEmail();
        String password = passwordEncoder.encode(info.getPassword());
        boolean update = this.update().eq("email", email).set("password", password).update();
        if(update) {
            this.deleteEmailVerifyCode(email);
        }
        return update ? null : "更新失败，请联系管理员";
    }

    /**
     * 重置密码确认操作，验证验证码是否正确
     * @param info 验证基本信息
     * @return 操作结果，null表示正常，否则为错误原因
     */
    @Override
    public String resetConfirm(ConfirmResetVO info) {
        String email = info.getEmail();
        String code = this.getEmailVerifyCode(email);
        if(code == null) return "请先获取验证码";
        if(!code.equals(info.getCode())) return "验证码错误，请重新输入";
        return null;
    }



    @Override
    public DisplayAccountByUserVO userInfo(String username) throws UsernameNotFoundException  {
        Account account = this.findAccountByNameOrEmail(username);
        if(account == null){
            throw new UsernameNotFoundException("用户名或密码错误");}
        // 创建 DisplayAccountByAdminVO 对象并返回
        return new DisplayAccountByUserVO(account);
    }

    @Override
    public DisplayAccountByAdminVO adminInfo(String username) throws UsernameNotFoundException {
        Account account = this.findAccountByNameOrEmail(username);
        if(account == null){
            throw new UsernameNotFoundException("用户名或密码错误");}
        return new DisplayAccountByAdminVO(account);
    }

    @Override
    public DisplayAccountByAdminVO adminInfoByEmail(String email) throws UsernameNotFoundException {
        Account account = this.findAccountByNameOrEmail(email);
        if(account == null){
            throw new UsernameNotFoundException("用户名或密码错误");}
        return new DisplayAccountByAdminVO(account);
    }

    @Override
    public String userInfoByName(String username) {
        Account account = this.findAccountByNameOrEmail(username);
        if(account == null)
            throw new UsernameNotFoundException("用户名或密码错误");
        return "用户名或密码错误";
    }


    @Override
    public String updateUserInfo(String oldUserName, String newUserName, String newPassword, Integer points) {
        // 检查旧用户名是否存在
        Account account = this.findAccountByNameOrEmail(oldUserName);
        if (account == null) {
            return "用户名或邮箱错误";
        }
        // 使用 UpdateWrapper 构建动态更新条件
        UpdateWrapper<Account> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("username", oldUserName); // 设置更新条件为旧用户名

        // 根据传入的参数动态设置更新字段
        boolean updateNeeded = false;
        if (newUserName != null && !newUserName.isEmpty() && !newUserName.equals(oldUserName)) {
            //MyBatis简化CRUD
            updateWrapper.set("username", newUserName);
            updateNeeded = true;
        }
        if (newPassword != null && !newPassword.isEmpty()) {
            updateWrapper.set("password", passwordEncoder.encode(newPassword));
            updateNeeded = true;
        }
        if (points != null) {
            updateWrapper.set("points", points);
            updateNeeded = true;
        }
        // 执行更新操作
        if (updateNeeded) {
            int result = accountMapper.update(null, updateWrapper);
            if (result > 0) {
                return "更新成功";
            } else {
                return "更新失败";
            }
        } else {
            return "无需更新";
        }
    }


    public MaterialPage getAllAccounts(Integer page,Integer pageSize) {
        //设置分页参数
        PageHelper.startPage(page,pageSize);

        //查询结果
        List<Account> accounts = accountMapper.selectList(null); // 检索所有账户
        //用PageHelper自带的Page类型对查询结果进行强制转型
        Page<Account> p = (Page<Account>) accounts;

        //对查询结果进行封装
        return new MaterialPage(p.getTotal(),p.getResult());
    }

    public boolean updateAvatar(String username, String newAvatarUrl,String newAvatarUuid) {
        // 查找用户，此处假设有一个方法来根据用户名获取用户实体
        Account account = this.findAccountByNameOrEmail(username);
        if (account != null) {
            account.setAvatarUrl(newAvatarUrl);
            account.setAvatarUuid(newAvatarUuid);
//            System.out.println(newAvatarUrl);
//            System.out.println(newAvatarUuid);
            // 更新用户信息
            return accountMapper.updateById(account) > 0;
        }
        return false;
    }

    //移除Redis中存储的邮件验证码
    private void deleteEmailVerifyCode(String email){
        String key = Const.VERIFY_EMAIL_DATA + email;
        stringRedisTemplate.delete(key);
    }
    //获取Redis中存储的邮件验证码
    private String getEmailVerifyCode(String email){
        String key = Const.VERIFY_EMAIL_DATA + email;
        return stringRedisTemplate.opsForValue().get(key);
    }
    //针对IP地址进行邮件验证码获取限流
    private boolean verifyLimit(String address) {
        String key = Const.VERIFY_EMAIL_LIMIT + address;
        return flow.limitOnceCheck(key, verifyLimit);
    }

    /**
     * 通过用户名或邮件地址查找用户
     * @param text 用户名或邮件
     * @return 账户实体
     */
    @Override
    public Account findAccountByNameOrEmail(String text){
        return this.query()
                .eq("username", text).or()
                .eq("email", text)
                .one();
    }

    /**
     * 查询指定邮箱的用户是否已经存在
     * @param email 邮箱
     * @return 是否存在
     */
    private boolean existsAccountByEmail(String email){
        return this.baseMapper.exists(Wrappers.<Account>query().eq("email", email));
    }

    /**
     * 查询指定用户名的用户是否已经存在
     * @param username 用户名
     * @return 是否存在
     */
    private boolean existsAccountByUsername(String username){
        return this.baseMapper.exists(Wrappers.<Account>query().eq("username", username));
    }

    @Override
    public String deleteUserAndRelatedInfo(String email) {
        // 开始事务
        // 1. 检查用户是否存在
        Account account = this.findAccountByNameOrEmail(email);
        if (account == null) return "用户不存在";
        try {
            // 2. 转移用户的上传内容给管理员
            this.transferMaterialsToAdmin(email);
//            // 3. 删除用户的评论
            this.deleteCommentsByUser(email);

//            // 4. 删除用户账户
            this.removeById(account.getId());

            // 提交事务
            return null;
        } catch (Exception e) {
            // 事务回滚
            return "删除用户过程中出现错误";
        }
    }

    private void transferMaterialsToAdmin(String email) {
        String adminEmail = "851314610@qq.com";
        // 获取所有由该用户上传的材料
        // 假设有一个方法可以获取这些材料
        List<Material> materials = this.getMaterialsByUploader(email);
        // 遍历材料并更新上传者为管理员
        for (Material material : materials) {
            this.updateUploaderByEmail(material,adminEmail);
            material.setMaterialUploader(adminEmail);
        }
    }

    private void deleteCommentsByUser(String email) {
        // 假设有一个方法可以删除用户的所有评论
        materialMapper.deleteByUserEmail(email);
    }

    //获取所有由该用户上传的材料
    private List<Material> getMaterialsByUploader(String email) {
        return materialMapper.selectMaterialsByUserEmail(email);
    }

    //更新资料上传者为管理员
    private void updateUploaderByEmail(Material material,String adminEmail) {
        if (material != null) {
            materialMapper.updateUploaderByEmail(material.getMaterialUploader(), adminEmail);
        }
    }

    @Override
    public List<Account> findTopNAccounts(int n) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("points").last("LIMIT " + n);
        return list(queryWrapper);
    }

    @Override
    public String signIn(String username) {
        // 查找用户
        Account account = this.findAccountByNameOrEmail(username);
        if (account == null) return "用户不存在";
        // 检查用户今天是否已经签到
        LocalDate lastSignIn = account.getLastSignInDate();
        LocalDate today = LocalDate.now();
        if (lastSignIn != null && lastSignIn.isEqual(today)) {
            return "签到失败，用户今日已经签到";
        }
        // 更新积分和最后签到日期
        int newPoints = account.getPoints() + 100; // 增加100积分
        account.setLastSignInDate(today);
        // 保存更新
        this.updateSignInInfo(account.getEmail(), newPoints, java.sql.Date.valueOf(today));
        return "今日签到成功";
    }


    private void updateSignInInfo(String email, int newPoints, Date newSignInDate) {
        this.lambdaUpdate()
                .eq(Account::getEmail, email)
                .set(Account::getPoints, newPoints)
                .set(Account::getLastSignInDate, newSignInDate)
                .update();
    }

    @Override
    public String updateUserPoints(String userEmail,Integer points){
        Account account = this.findAccountByNameOrEmail(userEmail);
        if(account == null) return "用户不存在";
        accountMapper.updateUserPoints(userEmail,points);
        return null;
    }

}

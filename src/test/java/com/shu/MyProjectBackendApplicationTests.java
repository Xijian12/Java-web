package com.shu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shu.entity.dto.Account;
import com.shu.mapper.AccountMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MyProjectBackendApplicationTests {

    @Test
    void contextLoads() {

    }

    @Autowired
    private AccountMapper accountMapper;
    @Test
    void testSetAccount(){
        //构造条件
        QueryWrapper<Account> wrapper=new QueryWrapper<Account>()
                .select("id","username","email","register_time","points")
                .eq("username","马英九")
                .ge("points",4);
        //查询
        List<Account> Account= accountMapper.selectList(wrapper);
        Account.forEach(System.out::println);
    }
}

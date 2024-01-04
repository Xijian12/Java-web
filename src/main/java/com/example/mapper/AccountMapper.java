package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.dto.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 85131
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}

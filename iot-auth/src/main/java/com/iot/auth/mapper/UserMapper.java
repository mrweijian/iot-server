package com.iot.auth.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iot.auth.domain.LoginUserDomain;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<LoginUserDomain> {
}

package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {


    @Select("Select * from sv_user where username=#{username} and password=#{password}")
    public User login(User user);

    @Select("Select * from sv_user where username=#{username}")
    public User register(User user);
}

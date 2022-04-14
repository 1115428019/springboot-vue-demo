package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {


    @Select("Select * from user where username=#{username} and password=#{password}")
    public User login(User user);

    @Select("Select * from user where username=#{username}")
    public User register(User user);

    @Insert("Insert into user(username,password,phone) values(#{username},#{password},#{phone}) ")
    public boolean insertPhone(String username,String password, String phone);

    @Select("Select * from user where username=#{username}")
    public boolean test(String username);

    @Select("Select * from user where username=#{username} and password=#{password}")
    public boolean phone_login(String username,String password);
}

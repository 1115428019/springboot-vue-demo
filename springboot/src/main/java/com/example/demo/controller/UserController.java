package com.example.demo.controller;


import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserMapper userMapper;

    @PostMapping
    @CrossOrigin//跨域请求
    public Result<?> save(@RequestBody User user) {
        userMapper.insert(user);
        return Result.success();
    }

    @PostMapping("/login")
    @CrossOrigin//跨域请求
    public Result<?> login(@RequestBody User user) {
        User user_check= userMapper.login(user);
        if(user_check == null){
            return Result.error("-1","用户名或者密码错误");
        }
        return Result.success(user_check);
    }

    @PostMapping("/register")
    @CrossOrigin//跨域请求
    public Result<?> register(@RequestBody User user) {
        User user_check= userMapper.register(user);
        if(user_check != null){
            return Result.error("-1","用户名重复");
        }
        userMapper.insert(user);
        return Result.success();
    }

    @PutMapping
    @CrossOrigin//跨域请求
    public Result<?> update(@RequestBody User user) {
       userMapper.updateById(user);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @CrossOrigin//跨域请求
    public Result<?> delete(@PathVariable Integer id) {
        userMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public Result<?> getById(@PathVariable Long id){
        return Result.success(userMapper.selectById(id));
    }


    //phone
    @GetMapping("/phone/register/{phone}/{password}")
    @CrossOrigin
    public Result<?> register(@PathVariable String phone,@PathVariable String password){
        if(!userMapper.test(phone))
        {
            userMapper.insertPhone(phone,password,phone);
            return Result.success();
        }
        return Result.error("1","手机号已经被注册");
    }

    @GetMapping("/phone/login/{username}/{password}")
    @CrossOrigin
    public Result<?> phoneLogin(@PathVariable String username,@PathVariable String password){

        if(!userMapper.phone_login(username,password)){
            return Result.error("1","用户名或者密码不正确");
        }
        return Result.success("登陆成功");
    }
}

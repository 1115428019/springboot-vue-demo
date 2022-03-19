package com.example.demo.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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


//    @GetMapping
//    @CrossOrigin
//    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
//                              @RequestParam(defaultValue = "10") Integer pageSize,
//                              @RequestParam(defaultValue = "") String search) {
//        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
//        if (StrUtil.isNotBlank(search)){
//            wrapper.like(User::getNickName, search);
//        }
//        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
//        return Result.success(userPage);
//    }

    @GetMapping("/{id}")
    @CrossOrigin
    public Result<?> getById(@PathVariable Long id){
        return Result.success(userMapper.selectById(id));
    }

}

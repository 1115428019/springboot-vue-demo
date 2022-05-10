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
            return Result.error("-1","Username or password is incorrect");
        }
        return Result.success(user_check);
    }

    @PostMapping("/register")
    @CrossOrigin//跨域请求
    public Result<?> register(@RequestBody User user) {
        User user_check= userMapper.register(user);
        if(user_check != null){
            return Result.error("-1","Duplicate username");
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
        User test = userMapper.test(phone);
        if(test==null)
        {
            userMapper.insertPhone(phone,password,phone);
            return Result.success();
        }
        return Result.error("1","The phone number has already been registered");
    }

    @GetMapping("/phone/login/{username}/{password}")
    @CrossOrigin
    public Result<?> phoneLogin(@PathVariable String username,@PathVariable String password){
        User test=userMapper.phone_login(username,password);
        if(test==null){
            return Result.error("1","wrong username or password");
        }
        return Result.success(userMapper.phone_get_user(username,password));
    }

    @GetMapping("/change/{username}/{password}/{newPassword}")
    @CrossOrigin
    public Result<?> phoneChange(@PathVariable String username,@PathVariable String password,@PathVariable String newPassword){
        int test=userMapper.phone_change(username,password,newPassword);
//        System.out.println(username+"|"+password+"|"+newPassword);
        if(test==0){
            return Result.error("1","The password or account number is wrong, the modification failed");
        }
        return Result.success(userMapper.phone_change(username,password,newPassword));
    }
}

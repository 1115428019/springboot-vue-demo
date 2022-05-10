package com.example.demo.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Activity;
import com.example.demo.entity.User;
import com.example.demo.entity.Userorder;
import com.example.demo.mapper.UserorderMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/Userorder")
@CrossOrigin
public class UserorderController {

    @Resource
    UserorderMapper userorderMapper;

    @GetMapping
    @CrossOrigin
    public Result<?> Userorder(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize,
                            @RequestParam(defaultValue = "0") Integer userId) {
        LambdaQueryWrapper<Userorder> wrapper = Wrappers.<Userorder>lambdaQuery();
        wrapper.eq(Userorder::getUserId,userId);
        Page<Userorder> newsPage = userorderMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(newsPage);
    }

    //added code
    @GetMapping("/order")
    @CrossOrigin
    public Result<?> ToOrder(){
//        System.out.println("a");
        return Result.success(userorderMapper.findAll());
    }


    @GetMapping("/searchInfo/{search}")
    @CrossOrigin
    public Result<?> SearchInfo(@PathVariable String search){
        System.out.println("b");
        String b = "%s%";
        if(StrUtil.isNotBlank(search)) {
            b = "%" + search + "%";
        }
        return Result.success(userorderMapper.Search(b));
    }

}

package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Feedback;
import com.example.demo.mapper.FeedbackMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Resource
    FeedbackMapper FeedbackMapper;



    @PostMapping
    @CrossOrigin
    public Result<?> save(@RequestBody Feedback Feedback){
        FeedbackMapper.insert(Feedback);
        return Result.success();
    }

    @PutMapping
    @CrossOrigin
    public Result<?> update(@RequestBody Feedback Feedback){
        FeedbackMapper.updateById(Feedback);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public Result<?> update(@PathVariable Long id){
        FeedbackMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping
    @CrossOrigin
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Feedback> wrapper = Wrappers.<Feedback>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(Feedback::getContent, search);
        }
        Page<Feedback> FeedbackPage= FeedbackMapper.selectPage( new Page<>(pageNum, pageSize),wrapper);
        return Result.success(FeedbackPage);
    }

}


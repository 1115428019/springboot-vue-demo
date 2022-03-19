package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Activity;
import com.example.demo.entity.News;
import com.example.demo.mapper.ActivityMapper;
import com.example.demo.mapper.SmallIconMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/Activity")
@CrossOrigin
public class ActivityController {

    @Resource
    ActivityMapper activityMapper;

    @Resource
    SmallIconMapper smallIconMapper;

    @GetMapping("/cover")
    @CrossOrigin
    public Result<?> getByRecommend(){
        return Result.success(activityMapper.Recommend(1));
    }

    @GetMapping("/cover/smallIcon")
    @CrossOrigin
    public Result<?> getAllSmallIcon(){
        return Result.success(smallIconMapper.findAll());
    }
//    @GetMapping("/{id}")
//    @CrossOrigin
//    public  Result<?> getAll(Integer id){
//        return  Result.success(activityMapper.selectById(id));
//    }

    @GetMapping
    @CrossOrigin
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Activity> wrapper = Wrappers.<Activity>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Activity::getVenueName, search);
        }
        Page<Activity> newsPage = activityMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(newsPage);
    }

    @GetMapping("/radio1")
    @CrossOrigin
    public Result<?> radio1(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "0") Integer radio) {
        LambdaQueryWrapper<Activity> wrapper = Wrappers.<Activity>lambdaQuery();
        if(radio != 0 ) {
            wrapper.eq(Activity::getCampus, radio);
        }
        else{
            wrapper.eq(Activity::getState,1);
        }
        Page<Activity> newsPage = activityMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(newsPage);
    }

    @GetMapping("/radio2")
    @CrossOrigin
    public Result<?> radio2(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize,
                            @RequestParam(defaultValue = "0") Integer radio) {
        LambdaQueryWrapper<Activity> wrapper = Wrappers.<Activity>lambdaQuery();
        if(radio != 0 ) {
            wrapper.eq(Activity::getId, radio);
        }
        else{
            wrapper.eq(Activity::getState,1);
        }
        Page<Activity> newsPage = activityMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(newsPage);
    }
}

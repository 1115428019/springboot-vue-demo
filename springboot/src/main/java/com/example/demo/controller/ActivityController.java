package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Activity;
import com.example.demo.entity.Feedback;
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

    @GetMapping("/detail")
    @CrossOrigin
    public Result<?> detail(@RequestParam(defaultValue = "1")Integer ac_id){
        return Result.success(activityMapper.selectById(ac_id));
    }

    @GetMapping("/phone/all")
    @CrossOrigin
    public Result<?> ToPhoneData(){
        return Result.success(activityMapper.findAll());
    }

    @GetMapping("/phone/searchInfo/{search}")
    @CrossOrigin
    public Result<?> PhoneSearchInfo(@PathVariable String search){
        String b = "%s%";
        if(StrUtil.isNotBlank(search)) {
            b = "%" + search + "%";
        }
          return Result.success(activityMapper.phoneSearch(b));
    }

    @GetMapping("/phone/changeType/{index}")
    @CrossOrigin
    public Result<?> PhoneChangeType(@PathVariable Integer index){
        if(index==0){
            return Result.success(activityMapper.phoneZero());
        }
        else if(index==1){
            return Result.success(activityMapper.phoneOne());
        }
        else if(index==2){
            return Result.success(activityMapper.phoneThird());
        }
        return Result.success();
    }

    @GetMapping("/phone/changeOpenType/{index}")
    @CrossOrigin
    public Result<?> PhoneChangeOpenType(@PathVariable Integer index){
        if(index==0){
            return Result.success(activityMapper.phoneState(index+1));
        }
        else if(index==1) {
            return Result.success(activityMapper.phoneState(index-1));
        }
        return Result.success();
    }

    @GetMapping("/phone/changeCampusType/{index}")
    @CrossOrigin
    public Result<?> PhoneChangeCampusType(@PathVariable Integer index){
        if(index==0){
            return Result.success(activityMapper.phoneCampus(index+1));
        }
        else if(index==1) {
            return Result.success(activityMapper.phoneCampus(index+1));
        }
        return Result.success();
    }
}

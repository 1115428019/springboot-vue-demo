package com.example.demo.controller;


import com.example.demo.common.Result;
import com.example.demo.mapper.AnnouncementMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/Announcement")
public class AnnouncementController {

    @Resource
    AnnouncementMapper announcementMapper;

    @GetMapping("/phone")
    @CrossOrigin
    public  Result<?> get_all_announcement(){
        return Result.success(announcementMapper.find_all());
    }
}

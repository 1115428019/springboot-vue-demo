package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.mapper.VConnectAMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mobile/vca")
@CrossOrigin
public class VConnectAController {
    @Resource
    VConnectAMapper vConnectAMapper;

    @GetMapping("/getInformation")
    @CrossOrigin
    public Result<?> getInformation(@RequestParam(defaultValue = "1",name = "venueId")Integer venueId){
        return Result.success(vConnectAMapper.getOne(venueId));
    }

}

package com.example.demo.controller;


import com.example.demo.common.Result;
import com.example.demo.entity.Activity;
import com.example.demo.entity.VenueTime;
import com.example.demo.mapper.ActivityMapper;
import com.example.demo.mapper.AppointmentMapper;
import com.example.demo.mapper.UserorderMapper;
import com.example.demo.mapper.VenueTimeMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/VenueTime")
@CrossOrigin
public class VenueTimeController {

    @Resource
    VenueTimeMapper venueTimeMapper;

    @GetMapping
    @CrossOrigin
    public Result<?> venueTime(@RequestParam(defaultValue = "1") Integer venueId){
        return Result.success(venueTimeMapper.search(venueId));
    }

    @Resource
    AppointmentMapper appointmentMapper;

    @Resource
    UserorderMapper userorderMapper;

    @Resource
    ActivityMapper activityMapper;


    @GetMapping("/operation")
    @CrossOrigin
    public Result<?> appointmentUser(@RequestParam(defaultValue = "1",name="arrayTime") Integer arrayTime,
                                     @RequestParam(defaultValue = "1",name="userId") Integer userId,
                                     @RequestParam(defaultValue = "1",name="venueId") Integer venueId,
                                     @RequestParam(defaultValue = "1",name="day") Integer day){
        appointmentMapper.insertSb(userId,venueId,arrayTime,day);
        VenueTime venueTime = venueTimeMapper.SelectSb(venueId,arrayTime,day);
        if(venueTime.getState()>0) {
            venueTimeMapper.sb(venueId, day, arrayTime);
        }
        else{
            String oo = "Insufficient reservation space";
            return Result.success(oo);
        }
        Activity sth = activityMapper.selectById(venueId);
        VenueTime second = venueTimeMapper.SelectSb(venueId,arrayTime,day);
        userorderMapper.insertSth(userId,venueId,day,arrayTime,0,sth.getVenueName(),sth.getAddress(),second.getActualTime());
        String back = "Make an appointment success";
        return Result.success(back);
    }

    @GetMapping("/phone/detail/{venueId}")
    @CrossOrigin
    public Result<?> Phone_Venue_Time(@PathVariable Integer venueId){
        return Result.success(venueTimeMapper.search(venueId));
    }
}

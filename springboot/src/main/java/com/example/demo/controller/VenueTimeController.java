package com.example.demo.controller;


import com.example.demo.common.Result;
import com.example.demo.entity.Appointment;
import com.example.demo.entity.VenueTime;
import com.example.demo.mapper.AppointmentMapper;
import com.example.demo.mapper.VenueTimeMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/VenueTime")
@CrossOrigin
public class VenueTimeController {

    @Resource
    VenueTimeMapper venueTimeMapper;

    @GetMapping
    @CrossOrigin
    public Result<?> venuetTime(@RequestParam(defaultValue = "1") Integer venueId){
        return Result.success(venueTimeMapper.search(venueId));
    }

    @Resource
    AppointmentMapper appointmentMapper;
    @GetMapping("/operation")
    @CrossOrigin
    public Result<?> appointmentUser(@RequestParam(defaultValue = "1") Integer arrayTime,
                                     @RequestParam(defaultValue = "1") Integer userId,
                                     @RequestParam(defaultValue = "1") Integer venueId,
                                     @RequestParam(defaultValue = "1") Integer day){
        appointmentMapper.insertSb(userId,venueId,arrayTime,day);
        VenueTime venueTime = venueTimeMapper.SelectSb(venueId,arrayTime,day);
        if(venueTime.getState()>0) {
            venueTimeMapper.sb(venueId, day, arrayTime);
        }
        else{
            String oo = "Insufficient reservation space";
            return Result.success(oo);
        }
        String back = "Make an appointment success";
        return Result.success(back);
    }
}

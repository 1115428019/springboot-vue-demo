package com.example.demo.controller;


import com.example.demo.common.Result;
import com.example.demo.entity.Activity;
import com.example.demo.entity.NVenueTime;
import com.example.demo.entity.User;
import com.example.demo.mapper.ActivityMapper;
import com.example.demo.mapper.NVenueTimeMapper;
import com.example.demo.mapper.NewUserOrderMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/NVenueTime")
@CrossOrigin
public class NVenueTimeController {

    @Resource
    NVenueTimeMapper nVenueTimeMapper;

    @Resource
    NewUserOrderMapper newUserOrderMapper;

    @Resource
    ActivityMapper activityMapper;

    @Resource
    UserMapper userMapper;

    @GetMapping("/get")
    @CrossOrigin
    public Result<?> getVenueTime(@RequestParam(defaultValue= "1",name="venueId")Integer venueId){
        return Result.success(nVenueTimeMapper.getAll(venueId));
    }

    @GetMapping("/ActivityTotalRemain")
    @CrossOrigin
    public  Result<?> getTotalRemain(@RequestParam(name="dayMonth")String dayMonth,@RequestParam(name = "venueId")Integer venueId,@RequestParam(name = "activityId")String activityId){
        return Result.success(nVenueTimeMapper.one(venueId,activityId,dayMonth));
    }

    @GetMapping("/dealWith")
    @CrossOrigin
    public Result<?> dealWith(@RequestParam(name = "activity")String activity, @RequestParam(name = "dayMonth")String dayMonth, @RequestParam(name = "reservableTime")String[] reservableTime,@RequestParam(name = "userId") int userId,@RequestParam(name = "venueId") int venueId){
        NVenueTime nVenueTime = nVenueTimeMapper.one(venueId,activity,dayMonth);
        Activity activity1 = activityMapper.selectById(venueId);
        User user = userMapper.selectById(userId);
       String[] ListState = null;
       String[] ListActualTime = null;
       //get week
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/DD");
        Date d =null;
        String today = "2022/"+dayMonth;
        try{
            d =f.parse(today);
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        calendar.setTime(d);
        int dayId = calendar.get(Calendar.DAY_OF_WEEK);
        if(dayId==0){
            dayId = 7;
        }
       int temp;
        ListState = nVenueTime.getState().split(",");
        ListActualTime = nVenueTime.getActualTime().split(",");
        for (Integer i=0;i<reservableTime.length;i++) {
                    if(reservableTime[i].contains("1")){
                        temp = Integer.valueOf(ListState[i]);
                            if(temp>0){
                                newUserOrderMapper.insertOne(userId,venueId,dayId,0,activity1.getVenueName(),activity1.getAddress(),activity,user.getUsername(),user.getPhone(),"0",ListActualTime[i],dayMonth);
                            }
                            else {
                                return Result.error("0","Position vacancy");
                            }
                    }
        }
    return Result.success();
    }
}

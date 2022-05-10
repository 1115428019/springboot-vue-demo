package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.NewUserOrder;
import org.apache.ibatis.annotations.Insert;

public interface NewUserOrderMapper extends BaseMapper<NewUserOrder> {

    @Insert("Insert into newuserorder(user_id,venue_id,day_id,state,venue_name,address,reserve_time,phone,charge,day_month,user_name,activity_name) values(#{userId},#{venueId},#{dayId},#{state},#{venueName},#{address},#{reserveTime},#{phone},#{charge},#{MonthDay},#{userName},#{activityName})")
    public void insertOne(Integer userId,Integer venueId, Integer dayId,Integer state,String venueName,String address,String activityName, String userName,String phone, String charge,String reserveTime,String MonthDay);
}

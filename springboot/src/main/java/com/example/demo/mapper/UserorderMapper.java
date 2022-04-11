package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Userorder;
import org.apache.ibatis.annotations.Insert;

public interface UserorderMapper  extends BaseMapper<Userorder> {

    @Insert("Insert into userorder(user_id,venue_id,day_id,hour_id,state,venue_name,address,reserve_time) values(#{userId},#{venueId},#{dayId},#{hourId},#{state},#{venueName},#{address},#{reserveTime})")
    public void insertSth(Integer userId,Integer venueId, Integer dayId, Integer hourId,Integer state,String venueName,String address,String reserveTime);
}

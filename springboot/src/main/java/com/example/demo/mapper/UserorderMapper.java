package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Userorder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserorderMapper  extends BaseMapper<Userorder> {

    //added

    @Insert("Insert into userorder(user_id,venue_id,day_id,hour_id,state,venue_name,address,reserve_time) values(#{userId},#{venueId},#{dayId},#{hourId},#{state},#{venueName},#{address},#{reserveTime})")
    public void insertSth(Integer userId,Integer venueId, Integer dayId, Integer hourId,Integer state,String venueName,String address,String reserveTime);
    @Select("select * from userorder" )
    public List<Userorder> findAll();
    @Select("Select * from userorder where venue_name like #{search}")
    public List<Userorder> Search(String search);

}

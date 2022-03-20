package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Appointment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AppointmentMapper extends BaseMapper<Appointment> {
    @Select("Select * from appointment where user_id=#{userId}")
    public List<Appointment> searchUserAppointment(Integer userId);

    @Insert("Insert into appointment(user_id,venue_id,hour_id,day_id) values(#{userid},#{venueid},#{hourid},#{dayid})")
    public void insertSb(Integer userid,Integer venueid, Integer hourid, Integer dayid);

}

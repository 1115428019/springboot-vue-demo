package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Activity;
import com.example.demo.entity.VenueTime;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface VenueTimeMapper extends BaseMapper<VenueTime> {

    @Select("Select * from venuetime where venue_id=#{VenueId}")
    public List<VenueTime> search(int VenueId);

    @Update("Update venuetime set state=state-1 where venue_id=#{venueid} and day=#{day} and reservable_time=#{reservable}")
    public  void sb(Integer venueid,Integer day,Integer reservable);

    @Select("Select * from venueTime where venue_id=#{venueid} and day=#{day} and reservable_time=#{hourid}")
    public VenueTime SelectSb(Integer venueid, Integer hourid, Integer day);
}

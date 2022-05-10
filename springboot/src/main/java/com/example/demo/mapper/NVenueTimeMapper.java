package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.NVenueTime;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NVenueTimeMapper extends BaseMapper<NVenueTime> {

    @Select("Select * from nvenuetime where venue_id=#{venueId}")
    public List<NVenueTime> getAll(int venueId);

    @Select("Select * from nvenuetime where venue_id=#{venueId} and activity_id=#{activityId} and day_month=#{dayMonth}")
    public NVenueTime one(int venueId,String activityId,String dayMonth);
}

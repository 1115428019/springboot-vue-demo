package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Activity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ActivityMapper extends BaseMapper<Activity> {

    @Select("Select * from activity where venue_name=#{venue_name} or activity_name=#{activity_name}")
    public List<Activity> search(Activity activity);

    @Select("Select * from activity where recommend=#{recommend}")
    public List<Activity> Recommend(Integer recommend);

}

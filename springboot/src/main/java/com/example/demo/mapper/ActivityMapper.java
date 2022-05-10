package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Activity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ActivityMapper extends BaseMapper<Activity> {

    @Select("Select * from activity where venue_name=#{venue_name} or activity_name=#{activity_name}")
    public List<Activity> search(Activity activity);

    @Select("Select * from activity where recommend=#{recommend}")
    public List<Activity> Recommend(Integer recommend);

    @Select("Select * from activity")
    public List<Activity> findAll();

    @Select("Select * from activity where venue_name like #{search}")
    public List<Activity> phoneSearch(String search);


    @Select("Select * from activity where id = 1 or id = 2")
    public List<Activity> phoneZero();

    @Select("Select * from activity where id = 1")
    public List<Activity> phoneOne();

    @Select("Select * from activity where id = 3")
    public List<Activity> phoneThird();

    @Select("Select * from activity where state= #{state}")
    public List<Activity> phoneState(Integer state);

    @Select("Select * from activity where campus = #{campus}")
    public List<Activity> phoneCampus(Integer campus);
//    @Select("Select * from activity where id=#{ac_id}")
//    public Activity SelectId(Integer ac_id);
}

package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Announcement;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AnnouncementMapper extends BaseMapper<Announcement> {

    @Select("Select * from announcement")
    public List<Announcement> find_all();
}

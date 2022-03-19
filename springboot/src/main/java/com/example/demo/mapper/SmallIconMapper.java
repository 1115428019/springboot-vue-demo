package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.SmallIcon;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SmallIconMapper extends BaseMapper<SmallIcon> {

    @Select("Select * from smallicon")
    public List<SmallIcon> findAll();
}

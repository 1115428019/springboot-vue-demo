package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Review;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReviewMapper extends BaseMapper<Review> {


    @Select("Select * from review")
    public List<Review> findAll();
}

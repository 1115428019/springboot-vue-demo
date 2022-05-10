package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.VConnectA;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface VConnectAMapper extends BaseMapper<VConnectA> {

    @Select("Select * from vconnecta where venue_id = #{venueId}")
    public VConnectA getOne(Integer venueId);
}

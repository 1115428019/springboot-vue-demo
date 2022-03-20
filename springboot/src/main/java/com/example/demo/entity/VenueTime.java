package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("venuetime")
@Data
public class VenueTime {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private int venueId;
    private int reservableTime;
    private int state;
    private int day;
    private String actualTime;
}

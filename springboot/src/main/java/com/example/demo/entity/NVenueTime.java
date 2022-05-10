package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("nvenuetime")
@Data
public class NVenueTime {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer venueId;
    private String reservableTime;
    private String state;
    private String actualTime;
    private String dayMonth;
    private String activityId;
}

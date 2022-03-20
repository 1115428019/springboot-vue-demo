package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("appointment")
@Data
public class Appointment {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer venueId;
    private Integer hourId;
    private Integer dayId;
}

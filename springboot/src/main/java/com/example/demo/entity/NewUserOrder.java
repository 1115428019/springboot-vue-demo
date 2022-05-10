package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("newuserorder")
@Data
public class NewUserOrder {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer venueId;
    private Integer dayId;
    private Integer state;
    private String venueName;
    private String address;
    private String reserveTime;
    private String userName;
    private String phone;
    private String charge;
    private String dayMonth;
    private String activityName;
}

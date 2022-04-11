package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("userorder")
@Data
public class Userorder {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer venueId;
    private Integer dayId;
    private Integer hourId;
    private Integer state;
    private String  venueName;
    private String address;
    private String reserveTime;
}

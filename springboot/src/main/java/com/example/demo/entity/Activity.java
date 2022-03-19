package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("activity")
@Data
public class Activity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String venueName;
    private String state;
    private String noticeForUse;
    private String address;
    private String phone;
    private String openTime;
    private String introduction;
    private String facility;
    private String activityName;
    private String photo;
    private Integer recommend;
    private Integer campus;
}

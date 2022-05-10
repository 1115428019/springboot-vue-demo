package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("vconnecta")
@Data
public class VConnectA {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer venueId;
    private String activityId;
    private String photoAll;
}

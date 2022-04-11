package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("review")
@Data
public class Review {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String content;
    private String time;
}

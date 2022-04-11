package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("feedback")
@Data
public class Feedback {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String content;
    private String state;
}

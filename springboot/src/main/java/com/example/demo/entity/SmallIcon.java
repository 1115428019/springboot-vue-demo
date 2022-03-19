package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("smallicon")
@Data
public class SmallIcon {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String photo;
    private String category;
    private String alt;
}

package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.Review;
import com.example.demo.mapper.ReviewMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/comment")
@CrossOrigin
public class ReviewController {

    @Resource
    ReviewMapper reviewMapper;

    @GetMapping
    @CrossOrigin
    public Result<?> getComment() {
        return Result.success(reviewMapper.findAll());
    }

    @PostMapping("/news")
    @CrossOrigin
    public Result<?> save(@RequestBody Review review) {
        reviewMapper.insert(review);
        return Result.success();
    }
}

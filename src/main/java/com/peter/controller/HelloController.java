package com.peter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by hbowang on 8/24/16.
 */
@RestController
@RequestMapping("/")
public class HelloController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "hello/{key}",method = RequestMethod.GET)
    public String hello(@PathVariable("key") String key){
        redisTemplate.opsForValue().set("hello"+key,new Date().toString());
        return "hello";
    }
}

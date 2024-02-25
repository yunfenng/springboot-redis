package com.lagou.sbr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Jaa
 * @Description:
 * @Date 2024/2/25
 */
@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/put")
    public String put(@RequestParam(required = true) String key, @RequestParam(required = true) String value) {
        // 设置过期时间为20秒
        redisTemplate.opsForValue().set(key, value, 20, TimeUnit.SECONDS);
        return "SUCCESS";
    }

    @GetMapping("/get")
    public String get(@RequestParam(required = true) String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }
}

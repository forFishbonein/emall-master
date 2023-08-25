package com.emall.controller;

import com.emall.common.R;
import com.emall.entity.User;
import com.emall.service.impl.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RegisterController {
    /*
    TODO
    当前controller应用场景： 电商端用户注册
    需求： 注册
     */

    @Autowired
    private LoginServiceImpl registerService;

    @Autowired
    private RedisTemplate redisTemplate;

    //验证码注册
    @PostMapping("/register")
    public R<String> register(@RequestBody User user) {
        return registerService.register(user);
    }

    //判断是否注册
    @PostMapping("/isRegistered")
    public R<String> isRegistered(@RequestBody User user) {
        return registerService.isRegistered(user);
    }
}

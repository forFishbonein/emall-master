package com.emall.controller;

import com.emall.common.R;
import com.emall.entity.User;
import com.emall.service.impl.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EmailController {
    /*
    TODO
    当前controller应用场景： 电商端用户登录，管理端管理员登录
    需求：
    1、 发送email
     */


    @Autowired
    private LoginServiceImpl emailService;

    //发送邮件
    @PostMapping("/sendEmail")
    public R<String> sendEmail(@RequestBody User user) {
        return emailService.sendEmail(user);
    }
}

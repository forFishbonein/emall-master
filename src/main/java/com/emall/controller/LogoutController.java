package com.emall.controller;

import com.emall.common.R;
import com.emall.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/logout")
public class LogoutController {
    /*
    TODO
    当前controller应用场景： 电商端、管理端登出
    需求： 登出
     */

    @Autowired
    private LoginServiceImpl logoutService;

    @GetMapping
    public R logout(@RequestHeader("Authorization") String token){
        return logoutService.logout(token);
    }
}
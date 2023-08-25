package com.emall.controller;

import com.emall.common.R;
import com.emall.service.impl.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
    /*
    TODO
    当前controller应用场景： 电商端、管理端验证
    需求： 验证码验证、密码验证
     */


    @Autowired
    private LoginServiceImpl loginService;

    //验证码校验
    @PostMapping("/codeCheck")
    public R<String> codeCheck(@RequestBody Map map) {
        log.info(map.toString());

        return loginService.codeCheck(map);

    }

    //密码校验
    @PostMapping("/passwordCheck")
    public R<String> passwordCheck(@RequestBody Map map) {
        log.info(map.toString());

        return loginService.passwordCheck(map);
    }
}

package com.emall.service;

import com.emall.common.R;
import com.emall.entity.Admin;
import com.emall.entity.User;

import java.util.Map;

public interface LoginService {

    R<String> codeCheck(Map map);

    R<String> passwordCheck(Map map);

    R<String> register(User user);

    R logout(String token);

    R<String> isRegistered(User user);

    R<String> sendEmail(User user);


    /**
     * 检验token
     * @param token
     * @return String
     */
    User checkToken(String token);

}

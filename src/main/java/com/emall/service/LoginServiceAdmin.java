package com.emall.service;

import com.emall.common.R;
import com.emall.entity.Admin;
import com.emall.entity.User;

import java.util.Map;

public interface LoginServiceAdmin {

    R<String> codeCheck(Map map);

    R<String> passwordCheck(Map map);

    R logout(String token);

    R<String> sendEmail(Admin admin);

    /**
     * 检验token
     * @param token
     * @return String
     */
    User checkToken(String token);

}

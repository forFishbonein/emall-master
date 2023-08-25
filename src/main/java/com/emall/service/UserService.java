package com.emall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.emall.common.R;
import com.emall.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    R<User> getByUserName(String userName);

    R<User> getByUserEmail(String userEmail);

    R<String> updateByUserId(User user);

    //发送邮件
    void sendEmail(String to, String subject, String context);


}

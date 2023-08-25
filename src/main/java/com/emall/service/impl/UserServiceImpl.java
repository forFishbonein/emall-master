package com.emall.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.emall.common.R;
import com.emall.entity.User;
import com.emall.mapper.UserMapper;
import com.emall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    //可能有问题
    @Override
    public R<User> getByUserName(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,userName);
        User user = userMapper.selectOne(queryWrapper);
        return R.success(user);
    }

    @Override
    public R<User> getByUserEmail(String userEmail) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserEmail,userEmail);
        User user = userMapper.selectOne(queryWrapper);
        return R.success(user);
    }

    @Override
    public R<String> updateByUserId(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserId,user.getUserId());
        int i = userMapper.update(user,queryWrapper);
        if (i > 0) {
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }


    // 邮件发送人
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String context) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        //发送人
        mailMessage.setFrom(from);
        //接收人
        mailMessage.setTo(to);
        //标题
        mailMessage.setSubject(subject);
        //内容
        mailMessage.setText(context);

        //发送邮件操作，从 from到 to
        mailSender.send(mailMessage);
    }


}

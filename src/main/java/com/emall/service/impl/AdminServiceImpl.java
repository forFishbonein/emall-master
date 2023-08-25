package com.emall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.emall.common.R;
import com.emall.entity.Admin;
import com.emall.mapper.AdminMapper;
import com.emall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

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

    @Override
    public R<Admin> getByAdminId(Long adminId) {
        Admin admin = adminMapper.selectById(adminId)  ;
        return R.success(admin);
    }

    @Override
    public R<Admin> getByAdminEmail(String adminEmail) {
        Admin admin = adminMapper.selectById(adminEmail);
        return R.success(admin);
    }

    @Override
    public R<Admin> getByAdminName(String adminName) {
        Admin admin = adminMapper.selectById(adminName);
        return R.success(admin);
    }

    @Override
    public R<String> insertAdmin(Admin admin) {
        int i = adminMapper.insert(admin);
        if (i > 0) {
            return R.success("操作成功");
        }
        return R.error("操作失败");    }

    @Override
    public R<String> updateAdmin(Admin admin) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getAdminId,admin.getAdminId());
        int i = adminMapper.update(admin,queryWrapper);
        if (i > 0) {
            return R.success("操作成功");
        }
        return R.error("操作失败");    }

    @Override
    public R<String> deleteByAdminId(Long adminId) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getAdminId,adminId);
        int i = adminMapper.delete(queryWrapper);
        if (i > 0) {
            return R.success("操作成功");
        }
        return R.error("操作失败");    }

    @Override
    public R<List<Admin>> getAllAdmin() {
        List<Admin> admins = adminMapper.selectList(null);
        return R.success(admins);
    }


}


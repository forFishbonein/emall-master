package com.emall.service;

import com.emall.common.R;
import com.emall.entity.Admin;

import java.util.List;

public interface AdminService {
    R<Admin> getByAdminId(Long adminId);

    R<Admin> getByAdminEmail(String adminEmail);

    R<Admin> getByAdminName(String adminName);

    R<String> insertAdmin(Admin admin);

    R<String> updateAdmin(Admin admin);

    R<String> deleteByAdminId(Long adminId);

    //查询所有管理员用户
    R<List<Admin>> getAllAdmin();

    //发送邮件
    void sendEmail(String to, String subject, String context);

}

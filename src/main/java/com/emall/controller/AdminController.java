package com.emall.controller;

import com.emall.common.R;
import com.emall.entity.Admin;
import com.emall.service.AdminService;
import com.emall.service.impl.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admins")
public class AdminController {
    /*
    TODO
    当前controller应用场景：管理端，对Admin（管理员）实例的数据库操作
    需求：
    1、 列出所有的管理员用户
    2、 新增管理员用户
    3、 删除管理员用户
    4、 更改管理员用户信息（邮箱、密码、昵称）
     */

    @Autowired
    private AdminService adminService;

    @Autowired
    private LoginServiceImpl loginService;


    @Cacheable(value = "adminall", key = "adminall")
    @GetMapping
    public R<List<Admin>> selectAllAdmin() {
        R<List<Admin>> admins = adminService.getAllAdmin();
        return admins;
    }

    @CachePut(value = "admin" , key = "#admin.adminId")
    @PostMapping
    public R<String> insertAdmin(@RequestBody Admin admin) {
        return adminService.insertAdmin(admin);
    }

    @CacheEvict(value = "admin" , key = "#adminId")
    @DeleteMapping("/{id}")
    public R<String> deleteAdmin(@PathVariable("id") Long adminId) {
        return adminService.deleteByAdminId(adminId);
    }

    @CachePut(value = "admin" , key = "#admin.adminId")
    @PutMapping
    public R<String> updateAdmin(@RequestBody Admin admin) {
        return adminService.updateAdmin(admin);
    }
}

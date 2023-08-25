package com.emall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.emall.common.R;
import com.emall.entity.User;
import com.emall.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    /*
    TODO
    当前controller应用场景： 电商端，查询、修改用户；管理端，查询、新增、修改、删除用户
    需求：
    1、 电商端，用户查询个人信息 1
    2、 电商端，用户修改个人信息 1
    3、 管理端，查询所有用户 1
              根据注册时间查询用户信息
    4、 管理端，新增、修改、删除用户 1
     */

    @Autowired
    private UserService userService;

    @Cacheable(value = "user", key = "#userId")
    @GetMapping("/id/{id}")
    public R<User> getByUserId(@PathVariable("id") Long userId){
        User user = userService.getById(userId);
        return R.success(user);
    }

    //解决中文参数乱码问题（尽量用get）
    @GetMapping("/name/{username}")
    public R<User> getByUsername(@PathVariable("username") String username) {
        R<User> user = userService.getByUserName(username);
        return user;
    }

    @GetMapping("/email/{useremail}")
    public R<User> getByUserEmail(@PathVariable("useremail") String useremail) {
        R<User> user = userService.getByUserEmail(useremail);
        return user;
    }

    //电商端，管理端修改用户信息
    @PutMapping
    public R<String> updateByUserId(@RequestBody User user) {
        return userService.updateByUserId(user);
    }

    @GetMapping("/all")
    public R<List<User>> getUsers() {
        List<User> users = userService.list();
        return R.success(users);
    }

    //根据用户注册时间查询新用户信息
    @GetMapping("/{regdate}")
    public R<List<User>> getByDate(@PathVariable("regdate") String regdate) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.lambda().lt(User::getUserRegdate, regdate);
        List<User> users = userService.list(queryWrapper);
        return R.success(users);
    }


    //管理端新增用户信息
    //新增用户---加入缓存
    @PostMapping
    //有问题
    public R<String> insertUser(@RequestBody User user) {
        boolean result = userService.save(user);
        if (result) {
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }

    @DeleteMapping("/{id}")
    public R<String> deleteById(@PathVariable("id") Long id) {
        boolean result = userService.removeById(id);
        if (result) {
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }












}

package com.emall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.emall.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

//    /***
//     *
//     * @param userId
//     * @return
//     */
//
//    //根据user_id查询user信息
//    User getByUserId (@Param("userId") Long userId);
//
//    //根据user_email查询user信息
//    User getByUserEmail (@Param("userEmail") String userEmail);
//
//    //根据user_name查询user信息
//    User getByUserName (@Param("userName") String userName);
//
    String getNameById (@Param("userId") Long userId);
//
//    List<User> getAllUser();

}

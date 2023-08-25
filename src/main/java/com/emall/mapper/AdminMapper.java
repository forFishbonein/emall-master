package com.emall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.emall.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    Admin[] getAllAdmin();

//    //根据admin_id查询user信息
//    Admin getByAdminId (@Param("adminId") Long adminId);
//
//    //根据admin_email查询user信息
//    Admin getByAdminEmail (@Param("adminEmail") String adminEmail);
//
//    //根据admin_name查询user信息
//    Admin getByAdminName (@Param("adminName") String adminName);
//
//    //查询所有的admin信息
//    List<Admin> getAllAdmin ();
//
//    //新增admin信息
//    int insertAdmin (@Param("admin") Admin admin);
//
//    //修改admin信息
//    int updateAdmin (@Param("admin") Admin admin);
//
//    //删除admin信息
//    int deleteByAdminId (@Param("adminId") Long adminId);


}

package com.emall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;

    //使用MP的id生成策略
    @TableId(type = IdType.ASSIGN_ID)
    private Long adminId;

    private String adminEmail;

    private String adminPass;

    private String adminName;

    private String adminRegdate;

    @TableLogic
    private String deleted;
}

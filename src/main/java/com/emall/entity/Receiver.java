package com.emall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public class Receiver implements Serializable {
    private static final long serialVersionUID = 1L;

    //使用MP的id生成策略
    @TableId(type = IdType.ASSIGN_ID)
    private Long receiverId;

    private Long userId;

    private String receiverName;

    private String receiverTele;

    private String receiverProvince;

    private String receiverCity;

    private String receiverDistrict;

    private String receiverDetials;

    @TableLogic
    private String deleted;
}

package com.emall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.emall.common.R;
import com.emall.entity.Receiver;

import java.util.List;


public interface ReceiverService{

    R<Receiver> getByReceiverId(Long receiverId);

    R<List<Receiver>> getByUserId(Long userId);

    R<String> insertReceiver(Receiver receiver);

    R<String> updateReceiver(Receiver receiver);

    R<String> deleteByReceiverId(Long receiverId);



}

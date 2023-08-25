package com.emall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.emall.common.R;
import com.emall.entity.Cart;
import com.emall.entity.Evaluate;
import com.emall.entity.Order;
import com.emall.entity.Receiver;
import com.emall.mapper.EvaluateMapper;
import com.emall.mapper.ReceiverMapper;
import com.emall.service.EvaluateService;
import com.emall.service.ReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiverServiceImpl implements ReceiverService {

    @Autowired
    private ReceiverMapper receiverMapper;


    @Override
    public R<Receiver> getByReceiverId(Long receiverId) {
        Receiver receiver = receiverMapper.selectById(receiverId);
        return R.success(receiver);
    }

    @Override
    public R<List<Receiver>> getByUserId(Long userId) {
        LambdaQueryWrapper<Receiver> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Receiver::getUserId, userId);
        List<Receiver> receivers =receiverMapper.selectList(queryWrapper);
        return R.success(receivers);
    }


    @Override
    public R<String> insertReceiver(Receiver receiver) {
        int i = receiverMapper.insert(receiver);
        if (i > 0) {
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }

    @Override
    public R<String> updateReceiver(Receiver receiver) {
        LambdaQueryWrapper<Receiver> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Receiver::getReceiverId,receiver.getReceiverId());
        int i = receiverMapper.update(receiver,queryWrapper);
        if (i > 0) {
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }

    @Override
    public R<String> deleteByReceiverId(Long receiverId) {
        LambdaQueryWrapper<Receiver> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Receiver::getReceiverId,receiverId);
        int i = receiverMapper.delete(queryWrapper);
        if (i > 0) {
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }
}

package com.emall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.emall.common.R;
import com.emall.entity.Evaluate;
import com.emall.entity.Order;
import com.emall.mapper.EvaluateMapper;
import com.emall.mapper.OrderMapper;
import com.emall.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private EvaluateMapper evaluateMapper;

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public R<Evaluate> getByEvaluateId(Long evaluateId) {
        Evaluate evaluate = evaluateMapper.selectById(evaluateId);
        return R.success(evaluate);

    }

    @Override
    public R<List<Evaluate>> getByUserId(Long userId) {
        LambdaQueryWrapper<Evaluate> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Evaluate::getUserId,userId);
        List<Evaluate> evaluates = evaluateMapper.selectList(queryWrapper);
        return R.success(evaluates);
    }

    @Override
    public R<List<Evaluate>> getByProductId(Long productId) {
        LambdaQueryWrapper<Evaluate> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Evaluate::getProductId,productId);
        List<Evaluate> evaluates = evaluateMapper.selectList(queryWrapper);
        return R.success(evaluates);
    }


    @Override
    public R<String> insertEvaluate(Evaluate evaluate) {
        int i = evaluateMapper.insert(evaluate);
        if(i > 0){
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }

    @Override
    public R<String> updateByEvaluateId(Evaluate evaluate) {
        LambdaQueryWrapper<Evaluate> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Evaluate::getEvaluateId,evaluate.getEvaluateId());
        int i = evaluateMapper.update(evaluate,queryWrapper);
        if(i > 0){
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }

    @Override
    public R<String> deleteByEvaluateId(Long evaluateId) {
        LambdaQueryWrapper<Evaluate> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Evaluate::getEvaluateId,evaluateId);
        int i = evaluateMapper.delete(queryWrapper);
        if(i > 0){
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }

    @Override
    public R<String> deleteByUserId(Long userId) {
        LambdaQueryWrapper<Evaluate> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Evaluate::getEvaluateId,userId);
        int i = evaluateMapper.delete(queryWrapper);
        if(i > 0){
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }


    @Override
    public R<Double> getAvgLevel(Long productId){
        Double avglevel=evaluateMapper.getAvgLevel(productId);
        return R.success(avglevel);
    }



}

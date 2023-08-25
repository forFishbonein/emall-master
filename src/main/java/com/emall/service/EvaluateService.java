package com.emall.service;

import com.emall.common.R;
import com.emall.entity.Evaluate;

import java.util.List;

public interface EvaluateService {
    R<Evaluate> getByEvaluateId(Long evaluateId);

    R<List<Evaluate>> getByUserId(Long userId);

    R<List<Evaluate>> getByProductId(Long productId);

    R<String> insertEvaluate(Evaluate evaluate);

    R<String> updateByEvaluateId(Evaluate evaluate);

    R<String> deleteByEvaluateId(Long evaluateId);

    R<String> deleteByUserId(Long userId);

    R<Double> getAvgLevel(Long productId);

}

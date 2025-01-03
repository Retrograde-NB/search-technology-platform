package com.xin.service;

import com.xin.commons.pojo.CommonEntity;

import java.util.Map;

/**
 * 分析服务接口
 */
public interface AnalysisService {

    //指标聚合
    public Map<Object, Object> metricAgg(CommonEntity commonEntity) throws Exception;

    //获取搜索热词
    public Map<String, Long> hotWords(CommonEntity commonEntity) throws Exception;

}
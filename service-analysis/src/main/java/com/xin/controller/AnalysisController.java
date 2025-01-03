package com.xin.controller;

import com.xin.commons.enums.ResultEnum;
import com.xin.commons.enums.TipsEnum;
import com.xin.commons.pojo.CommonEntity;
import com.xin.commons.result.ResponseData;
import com.xin.service.AnalysisService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 分析服务控制器
 */
@RestController
@RequestMapping("v1/analysis")
public class AnalysisController {
    private static final Logger logger = LoggerFactory.getLogger(AnalysisController.class);
    @Autowired
    AnalysisService analysisService;

    @GetMapping(value = "/metric/agg")
    public ResponseData metricAgg(@RequestBody CommonEntity commonEntity) {
        //构造返回数据
        ResponseData responseData = new ResponseData();
        if (StringUtils.isEmpty(commonEntity.getIndexName())) {
            responseData.setResultEnum(ResultEnum.param_isnull);
            return responseData;
        }
        //定义查询返回结果
        Map<Object, Object> result = null;
        try {
            result = analysisService.metricAgg(commonEntity);
            //通过类型推断自动装箱
            responseData.setResultEnum(result, ResultEnum.success, null);
            //日志记录
            logger.info(TipsEnum.metricagg_get_doc_success.getMessage());

        } catch (Exception e) {
            //打印到控制台
            e.printStackTrace();
            //日志记录
            logger.error(TipsEnum.metricagg_get_doc_fail.getMessage());
            //构建错误信息
            responseData.setResultEnum(ResultEnum.error);
        }
        return responseData;
    }

    @GetMapping(value = "/hotwords")
    public ResponseData hotWords(@RequestBody CommonEntity commonEntity) {
        //构造返回数据
        ResponseData responseData = new ResponseData();
        if (StringUtils.isEmpty(commonEntity.getIndexName())) {
            responseData.setResultEnum(ResultEnum.param_isnull);
            return responseData;
        }
        //定义查询返回结果
        Map<String, Long> result = null;
        try {
            result = analysisService.hotWords(commonEntity);
            //通过类型推断自动装箱
            responseData.setResultEnum(result, ResultEnum.success, null);
            //日志记录
            logger.info(TipsEnum.hotwords_get_doc_success.getMessage());

        } catch (Exception e) {
            //打印到控制台
            e.printStackTrace();
            //日志记录
            logger.error(TipsEnum.hotwords_get_doc_fail.getMessage());
            //构建错误信息
            responseData.setResultEnum(ResultEnum.error);
        }
        return responseData;
    }

}
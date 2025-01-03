package com.xin.controller;

import com.xin.commons.enums.ResultEnum;
import com.xin.commons.enums.TipsEnum;
import com.xin.commons.pojo.CommonEntity;
import com.xin.commons.result.ResponseData;
import com.xin.service.ElasticsearchDocumentService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.rest.RestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 终搜搜索控制器
 */
@RestController
@RequestMapping("v1/docs")
public class ElasticsearchDocController {
    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchDocController.class);
    @Autowired
    ElasticsearchDocumentService elasticsearchDocumentService;

    @GetMapping(value = "/mquery")
    public ResponseData matchQuery(@RequestBody CommonEntity commonEntity) {
        //构造返回下游业务数据
        ResponseData rData = new ResponseData();
        //定义全文检索返回对象
        SearchResponse result = null;
        try {
            //通过接口调用远程全文检索方法
            result = elasticsearchDocumentService.matchQuery(commonEntity);
            //获取本次查询数据量
            long aSize = result.getHits().getTotalHits().value;
            //通过类型推断自动装箱（多个参数取交集）
            rData.setResultEnum(result.getHits().getHits(), ResultEnum.success, Integer.valueOf(String.valueOf(aSize)));
            //日志记录
            logger.info(TipsEnum.batch_get_doc_success.getMessage());
        } catch (Exception e) {
            //打印到控制台
            e.printStackTrace();
            //日志记录
            logger.error(TipsEnum.batch_get_doc_fail.getMessage());
            //构建错误返回信息
            rData.setResultEnum(ResultEnum.error);
        }
        //返回
        return rData;

    }

    @GetMapping(value = "/tquery")
    public ResponseData termQuery(@RequestBody CommonEntity commonEntity) {
        //构造返回下游业务数据
        ResponseData rData = new ResponseData();
        //定义结构化搜索返回对象
        SearchResponse result = null;
        try {
            //通过接口调用远程结构化查询方法
            result = elasticsearchDocumentService.termQuery(commonEntity);
            //获取本次查询数据量
            long aSize = result.getHits().getTotalHits().value;
            //通过类型推断自动装箱（多个参数取交集）
            rData.setResultEnum(result.getHits().getHits(), ResultEnum.success, Integer.valueOf(String.valueOf(aSize)));
            //日志记录
            logger.info(TipsEnum.batch_get_doc_success.getMessage());
        } catch (Exception e) {
            //打印到控制台
            e.printStackTrace();
            //日志记录
            logger.error(TipsEnum.batch_get_doc_fail.getMessage());
            //构建错误返回信息
            rData.setResultEnum(ResultEnum.error);
        }
        //返回
        return rData;

    }


    @PostMapping(value = "/batch")
    public ResponseData bulkAndDoc(@RequestBody CommonEntity commonEntity) {
        //构造返回下游业务数据
        ResponseData rData = new ResponseData();
        if (StringUtils.isEmpty(commonEntity.getIndexName()) || CollectionUtils.isEmpty(commonEntity.getList())) {
            rData.setResultEnum(ResultEnum.param_isnull);
            return rData;
        }
        //定义批量返回结果
        RestStatus result = null;
        try {
            //通过接口调用批量新增方法
            result = elasticsearchDocumentService.bulkAndDoc(commonEntity);
            //通过类型推断自动装箱（多个参数取交集）
            rData.setResultEnum(result, ResultEnum.success, null);
            //日志记录
            logger.info(TipsEnum.batch_create_doc_success.getMessage());
        } catch (Exception e) {
            //打印到控制台
            e.printStackTrace();
            //日志记录
            logger.error(TipsEnum.batch_create_doc_fail.getMessage());
            //构建错误返回信息
            rData.setResultEnum(ResultEnum.error);
        }
        //返回
        return rData;

    }


    @GetMapping(value = "/csuggest")
    public ResponseData cSuggest(@RequestBody CommonEntity commonEntity) {
        //构造返回下游业务数据
        ResponseData rData = new ResponseData();
        if (StringUtils.isEmpty(commonEntity.getIndexName()) || StringUtils.isEmpty(commonEntity.getSuggestFileld()) || StringUtils.isEmpty(commonEntity.getSuggestValue())) {
            rData.setResultEnum(ResultEnum.param_isnull);
            return rData;
        }
        //定义建议返回结果
        List<String> result = null;
        try {
            //通过接口调用批量新增方法
            result = elasticsearchDocumentService.cSuggest(commonEntity);
            //通过类型推断自动装箱（多个参数取交集）
            rData.setResultEnum(result, ResultEnum.success, result.size());
            //日志记录
            logger.info(TipsEnum.csuggest_get_doc_success.getMessage());
        } catch (Exception e) {
            //打印到控制台
            e.printStackTrace();
            //日志记录
            logger.error(TipsEnum.csuggest_get_doc_fail.getMessage());
            //构建错误返回信息
            rData.setResultEnum(ResultEnum.error);
        }
        //返回
        return rData;

    }


    @GetMapping(value = "/psuggest")
    public ResponseData pSuggest(@RequestBody CommonEntity commonEntity) {
        //构造返回下游业务数据
        ResponseData rData = new ResponseData();
        if (StringUtils.isEmpty(commonEntity.getIndexName()) || StringUtils.isEmpty(commonEntity.getSuggestFileld()) || StringUtils.isEmpty(commonEntity.getSuggestValue())) {
            rData.setResultEnum(ResultEnum.param_isnull);
            return rData;
        }
        //定义纠错返回结果
        String result = null;
        try {
            //通过接口调用批量新增方法
            result = elasticsearchDocumentService.pSuggest(commonEntity);
            //通过类型推断自动装箱（多个参数取交集）
            rData.setResultEnum(result, ResultEnum.success, null);
            //日志记录
            logger.info(TipsEnum.psuggest_get_doc_success.getMessage());
        } catch (Exception e) {
            //打印到控制台
            e.printStackTrace();
            //日志记录
            logger.error(TipsEnum.psuggest_get_doc_fail.getMessage());
            //构建错误返回信息
            rData.setResultEnum(ResultEnum.error);
        }
        //返回
        return rData;

    }


    @GetMapping(value = "/tsuggest")
    public ResponseData tSuggest(@RequestBody CommonEntity commonEntity) {
        //构造返回下游业务数据
        ResponseData rData = new ResponseData();
        if (StringUtils.isEmpty(commonEntity.getIndexName()) || StringUtils.isEmpty(commonEntity.getSuggestFileld()) || StringUtils.isEmpty(commonEntity.getSuggestValue())) {
            rData.setResultEnum(ResultEnum.param_isnull);
            return rData;
        }
        //定义搜索推荐返回结果
        String result = null;
        try {
            //通过接口调用批量新增方法
            result = elasticsearchDocumentService.tSuggest(commonEntity);
            //通过类型推断自动装箱（多个参数取交集）
            rData.setResultEnum(result, ResultEnum.success, null);
            //日志记录
            logger.info(TipsEnum.tsuggest_get_doc_success.getMessage());
        } catch (Exception e) {
            //打印到控制台
            e.printStackTrace();
            //日志记录
            logger.error(TipsEnum.tsuggest_get_doc_fail.getMessage());
            //构建错误返回信息
            rData.setResultEnum(ResultEnum.error);
        }
        //返回
        return rData;

    }
}

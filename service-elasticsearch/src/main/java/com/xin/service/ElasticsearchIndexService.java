package com.xin.service;

import com.xin.commons.pojo.CommonEntity;

/**
 * 索引操作接口
 */
public interface ElasticsearchIndexService {
    //新增索引+映射
    public boolean addIndexAndMapping(CommonEntity commonEntity) throws Exception;
}

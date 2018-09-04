package com.wangxin.spring.boot.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Author:jzwx
 * @Desicription: DynamicDataSource
 * @Date:Created in 2018-09-03 17:04
 * @Modified By:
 */
public class DynamicDataSource extends AbstractRoutingDataSource{

    /**
     * 日志打印器
     */
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    /**
     * 动态数据源的切换
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        logger.info("数据源为:====,{}", DynamicDataSourceHolder.getDataSource());
        return DynamicDataSourceHolder.getDataSource();
    }
}
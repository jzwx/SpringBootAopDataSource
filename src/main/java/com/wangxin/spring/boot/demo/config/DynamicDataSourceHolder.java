package com.wangxin.spring.boot.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author:jzwx
 * @Desicription: 当前线程数据源
 * @Date:Created in 2018-09-03 17:06
 * @Modified By:
 */
public class DynamicDataSourceHolder {
    /**
     * 日志打印器
     */
    public static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceHolder.class);

    /**
     * 本地线程共享对象
     */
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 设置数据源名
     * @param name
     */
    public static void putDataSource(String name) {
        THREAD_LOCAL.set(name);
    }

    /**
     * 获取数据源名
     * @return
     */
    public static String getDataSource() {
        return THREAD_LOCAL.get();
    }

    /**
     * 清除数据源名
     */
    public static void removeDataSource() {
        THREAD_LOCAL.remove();
    }
}

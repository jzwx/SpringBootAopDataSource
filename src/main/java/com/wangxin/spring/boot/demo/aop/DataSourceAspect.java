package com.wangxin.spring.boot.demo.aop;

import java.lang.reflect.Method;

import com.wangxin.spring.boot.demo.config.DynamicDataSourceHolder;
import com.wangxin.spring.boot.demo.util.TargetDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 数据源访问拦截AOP切面定义
 */
@Component
@Aspect
@Slf4j
public class DataSourceAspect {
    /**
     * 日志打印器
     */
    private static final Logger log = LoggerFactory.getLogger(DataSourceAspect.class);

    /**
     * 定义切点,所有的mapper接口的访问都进行拦截 AOP切面的切入点配置
     */
    @Pointcut("execution( * com.wangxin.spring.boot.demo.mapper.*.*(..))")
    public void dataSourcePointCut() {
    }

    /**
     * AOP执行数据源切换
     * @param joinPoint
     */
    @Before("dataSourcePointCut()")
    public void before(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        String method = joinPoint.getSignature().getName();
        Class<?>[] clazz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod()
            .getParameterTypes();
        try {
            Method m = clazz[0].getMethod(method, parameterTypes);
            //如果方法上存在切换数据源的注解，则根据注解内容进行数据源切换
            if (m != null && m.isAnnotationPresent(TargetDataSource.class)) {
                TargetDataSource data = m.getAnnotation(TargetDataSource.class);
                String dataSourceName = data.value();
                DynamicDataSourceHolder.putDataSource(dataSourceName);
                log.debug("current thread " + Thread.currentThread().getName() + " add "
                          + dataSourceName + " to ThreadLocal");
            } else {
                log.debug("switch datasource fail,use default");
            }
        } catch (Exception e) {
            log.error("current thread " + Thread.currentThread().getName()
                      + " add data to ThreadLocal error",
                e);
        }
    }

    /**
     * 执行完切面后，将线程共享中的数据源名称清空
     * @param joinPoint
     */
    @After("dataSourcePointCut()")
    public void after(JoinPoint joinPoint) {
        DynamicDataSourceHolder.removeDataSource();
    }
}

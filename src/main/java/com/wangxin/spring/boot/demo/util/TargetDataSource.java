package com.wangxin.spring.boot.demo.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在
@Target(ElementType.METHOD)
public @interface TargetDataSource {
	String value() default "testOneDataSource";
}

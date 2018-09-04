package com.wangxin.spring.boot.demo.mapper;

import com.wangxin.spring.boot.demo.domain.Person;
import com.wangxin.spring.boot.demo.util.TargetDataSource;

import java.util.List;

/**
 * @Author:jzwx
 * @Desicription: IPersonMapper
 * @Date:Created in 2018-09-04 11:35
 * @Modified By:
 */
public interface IPersonMapper {
    /**
     * 从test1数据源中获取用户信息
     */
    @TargetDataSource("testOneDataSource")
    Person getPersonById();

    /**
     * 从test1数据源中获取用户信息
     */
    @TargetDataSource("testOneDataSource")
    Person selectByOddUserId(int id);

    /**
     * 从test2数据源中获取用户信息
     */
    @TargetDataSource("testTwoDataSource")
    Person selectByEvenUserId(int id);

    /** 获取数据库中所有数据 */
    @TargetDataSource("testOneDataSource")
    List<Person> queryPersonList();
}

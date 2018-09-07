package com.wangxin.spring.boot.demo.repository;

import com.github.pagehelper.PageInfo;
import com.wangxin.spring.boot.demo.domain.Person;
import com.wangxin.spring.boot.demo.util.TargetDataSource;

import java.util.List;

/**
 * @Author:jzwx
 * @Desicription: IPersonRepository
 * @Date:Created in 2018-09-04 11:42
 * @Modified By:
 */
public interface IPersonRepository {
    /**
     * 从test1数据源中获取用户信息
     * @return
     */
    Person getPersonById();

    /**
     * 从test1数据源中获取用户信息
     */
    Person selectByOddUserId(int id);

    /**
     * 从test2数据源中获取用户信息
     */
    Person selectByEvenUserId(int id);

    PageInfo<Person> queryPersonList(int pageNum, int pageSize);

    PageInfo<Person> queryPersonList2(int pageNum, int pageSize);
}

package com.wangxin.spring.boot.demo.dao;

import com.wangxin.spring.boot.demo.domain.Person;
import com.wangxin.spring.boot.demo.mapper.IPersonMapper;
import com.wangxin.spring.boot.demo.util.TargetDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:jzwx
 * @Desicription: IPersonDao
 * @Date:Created in 2018-09-03 17:32
 * @Modified By:
 */
@Repository
public class IPersonDao {
    @Autowired
    private IPersonMapper iPersonMapper;

    public Person getPersonById(){
        return iPersonMapper.getPersonById();
    };

    /**
     * 从test1数据源中获取用户信息
     */
    public Person selectByOddUserId(int id){
        return iPersonMapper.selectByOddUserId(id);
    };

    /**
     * 从test2数据源中获取用户信息
     */
    public Person selectByEvenUserId(int id){
        return iPersonMapper.selectByEvenUserId(id);
    };

    public List<Person> queryPersonList(){
        return iPersonMapper.queryPersonList();
    }
}

package com.wangxin.spring.boot.demo.repository.impl;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangxin.spring.boot.demo.dao.IPersonDao;
import com.wangxin.spring.boot.demo.domain.Person;
import com.wangxin.spring.boot.demo.repository.IPersonRepository;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:jzwx
 * @Desicription: IPersonRepositoryImpl
 * @Date:Created in 2018-09-04 11:42
 * @Modified By:
 */
@Service
public class IPersonRepositoryImpl implements IPersonRepository {

    @Autowired
    private IPersonDao iPersonDao;

    public Person getPersonById() {
        return iPersonDao.getPersonById();
    };

    /**
     * 从test1数据源中获取用户信息
     */
    public Person selectByOddUserId(int id) {
        return iPersonDao.selectByOddUserId(id);
    };

    /**
     * 从test2数据源中获取用户信息
     */
    public Person selectByEvenUserId(int id) {
        return iPersonDao.selectByEvenUserId(id);
    }

    @Override
    public PageInfo<Person> queryPersonList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy("id asc");
        List<Person> personList = iPersonDao.queryPersonList();
        if (personList != null && personList.size() > 0) {
            PageInfo<Person> pageInfo = new PageInfo<Person>(personList);
            return pageInfo;
        }
        return new PageInfo<>(new ArrayList<>());
    }

    @Override
    public PageInfo<Person> queryPersonList2(int pageNum, int pageSize) {
        PageInfo<Person> pageInfo = PageHelper.startPage(pageNum,pageSize).setOrderBy("id desc").doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                iPersonDao.queryPersonList();
            }
        });
        return pageInfo;
    }
}

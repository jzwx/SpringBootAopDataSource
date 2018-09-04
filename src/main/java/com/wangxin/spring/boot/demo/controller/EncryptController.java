package com.wangxin.spring.boot.demo.controller;

import com.github.pagehelper.PageInfo;
import com.wangxin.spring.boot.demo.domain.Person;
import com.wangxin.spring.boot.demo.repository.IPersonRepository;
import com.wangxin.spring.boot.demo.result.JsonResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EncryptController {

    /**
     * 日志打印器
     */
    private static final Logger log = LoggerFactory.getLogger(EncryptController.class);

    /**
     * 用户仓库接口
     */
    @Autowired
    private IPersonRepository   iPersonRepository;

    @RequestMapping("/test")
    public @ResponseBody String test() {
        Person person = iPersonRepository.getPersonById();
        log.info(" == " + person.getFirst_name());
        Person person1 = iPersonRepository.selectByEvenUserId(2);
        log.info(" == " + person1.getFirst_name());
        return person + "                           + " + person1.toString();
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public @ResponseBody JsonResponseData<? extends Object> query(@RequestParam(value = "pageNum", required = true) int pageNum,
                                                                  @RequestParam(value = "pageSize", required = true) int pageSize) {
        PageInfo<Person> personList = iPersonRepository.queryPersonList(pageNum, pageSize);
        return new JsonResponseData<PageInfo<Person>>(Boolean.TRUE, "操作成功", 0, "", personList);
    }
}

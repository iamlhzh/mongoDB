package com.lhzh.mongodb.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private MongoTemplate mongoTemplate;
    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String test(){
        return "success";
    }
}

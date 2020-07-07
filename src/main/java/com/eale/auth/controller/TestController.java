package com.eale.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Admin
 * @Date 2020/7/7
 * @Description
 * @Version 1.0
 **/
@RestController
@RequestMapping("/test")
public class TestController {


    @RequestMapping("/hello")
    public String hello(){
        return "hello";

    }



}

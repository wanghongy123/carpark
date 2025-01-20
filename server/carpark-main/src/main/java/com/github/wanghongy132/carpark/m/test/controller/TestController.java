package com.github.wanghongy132.carpark.m.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanruxiuer
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping(value = {"/hello/{name}", "/hello"})
    public String hello(@PathVariable(value = "name", required = false) String name) {
        if (name == null) return "Hello World!!!";
        else return "Hello " + name;
    }
}

package com.github.wanghongy132.carpark.m;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableKnife4j
public class CarparkMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarparkMainApplication.class, args);
    }

}

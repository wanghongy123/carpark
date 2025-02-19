package com.github.wanghongy132.carpark.m;

import com.github.wanghongy132.carpark.m.infrastructure.persistence.ResponseMessageRepository;
import com.github.wanghongy132.carpark.m.infrastructure.persistence.entity.ResponseMessageEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest(classes = {CarparkMainApplication.class})
class CarparkMainApplicationTests {
    @Autowired
    private ResponseMessageRepository repository;
    @Test
    void contextLoads() {
        repository.findById(10000).ifPresent(System.out::println);
        repository.findByMessageKey("200").ifPresent(System.out::println);

    }

}

package com.example.hiringtaskexchangerate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HiringtaskexchangerateApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiringtaskexchangerateApplication.class, args);
    }

}

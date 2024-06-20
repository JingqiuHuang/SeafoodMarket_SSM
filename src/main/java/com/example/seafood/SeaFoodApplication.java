package com.example.seafood;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.seafood.mappers")
public class SeaFoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeaFoodApplication.class, args);
    }

}

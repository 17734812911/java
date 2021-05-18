package com.ct.springsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages={"com.ct"})
public class SpringsecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringsecurityApplication.class, args);
    }
}

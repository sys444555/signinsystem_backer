package com.hc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hc.modules.*.mapper")
public class SigninsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SigninsystemApplication.class, args);
    }

}

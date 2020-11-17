package com.pp.educational_management_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.pp.educational_management_system.dao")
public class EducationalManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationalManagementSystemApplication.class, args);
    }

}

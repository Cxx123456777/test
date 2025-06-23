package com.example.examsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 在线考试系统主启动类
 * 
 * @author exam-system
 * @version 1.0.0
 */
@SpringBootApplication
@MapperScan("com.example.examsystem.mapper")
@EnableTransactionManagement
public class ExamSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamSystemApplication.class, args);
        System.out.println("=================================");
        System.out.println("考试系统启动成功！");
        System.out.println("访问地址: http://localhost:8080/exam-system");
        System.out.println("API文档: http://localhost:8080/exam-system/swagger-ui.html");
        System.out.println("=================================");
    }
}

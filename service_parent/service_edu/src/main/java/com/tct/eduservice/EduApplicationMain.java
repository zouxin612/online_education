package com.tct.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tct"})
public class EduApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(EduApplicationMain.class,args);
    }
}

package com.springAndAWSprac.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//프로젝트의 메인클래스임을 명시
@SpringBootApplication
@EnableJpaAuditing
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}

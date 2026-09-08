package com.example.bai3_it214_ss03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class Bai3It214Ss03Application {

    public static void main(String[] args) {
        SpringApplication.run(Bai3It214Ss03Application.class, args);
    }

}

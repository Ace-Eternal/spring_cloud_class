package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceProvider16000 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceProvider16000.class,args);
    }
}

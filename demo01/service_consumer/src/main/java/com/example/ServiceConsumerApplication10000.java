package com.example;

import com.example.ruler.Config;
import com.example.ruler.ThreeTimesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//调provider的时候 进行Config.class这一类的负载均衡策略
//也就是说不同种类的微服务可以使用不同种类的策略 @LoadBalancerClients
//对于要调用的微服务使用这个负载均衡策略
@LoadBalancerClient(name = "provider",configuration = ThreeTimesConfig.class)
@EnableFeignClients
public class ServiceConsumerApplication10000 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumerApplication10000.class,args);
    }
//    @Bean
//    @LoadBalanced
//    //在通过使用 RestTemplate 请求时有负载均衡
//    //使用RestTemplate发送http请求调用微服务
//    RestTemplate restTemplate(){
//        return new RestTemplate();
//    }



}

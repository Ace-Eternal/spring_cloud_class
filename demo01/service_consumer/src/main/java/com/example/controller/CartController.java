package com.example.controller;

import com.example.entity.CommonResult;
import com.example.entity.User;
import com.example.feignClient.ProviderService;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    //需要在启动类中手动添加bean
//    @Resource
//    private RestTemplate restTemplate;
//
//    @Resource
//    private DiscoveryClient discoveryClient;

    @Resource
    private ProviderService providerService;

    @GetMapping("/getCartById")
    public CommonResult<User> getCartById(@RequestParam("userId") Integer userId) {

        //通过服务名称获取服务
        //List<ServiceInstance>list=discoveryClient.getInstances("provider");
        //默认使用第0个使用，所以要改用负载均衡
        //服务端负载均衡(Nginx)
        //客户端负载均衡(Ribbon LoadBlance) 轮询...
        //ServiceInstance instance=list.get(0);

        //加权的loadBlance
        //zoneBase

        //这里的host名称直接改成服务名称即可
        // spring:
        //  application:
        //    name: "provider"

        //User o = restTemplate.getForObject("http://provider/user/getUserById?userId=" + userId.toString(), User.class);
        //直接调用改为从注册中心调用
        //缓存

        User o = providerService.getUserById(userId);
        return new CommonResult<User>(200, "正常", o);
    }
}

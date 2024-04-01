package com.example.feignClient;

import com.example.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//这个接口是provider的接口
//value是微服务名称
@FeignClient(value = "provider")
public interface ProviderService {
    //称之为方法存根
    @GetMapping("/user/getUserById")
    public User getUserById(@RequestParam("userId") Integer userId);
}

package com.example.ruler;

import com.example.balancer.ThreeTimesLoaderBlancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

//每个provider提供3次方法
public class ThreeTimesConfig {
    @Bean
    ReactorLoadBalancer<ServiceInstance> threeTimesLoaderBlancer(Environment e,
                                                LoadBalancerClientFactory factory){
        //PROPERTY_NAME===>@LoadBalancerClient(name = "provider"
        //需要从eureka得到叫这个名字的服务有几个
        String name = e.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new ThreeTimesLoaderBlancer(
                //懒加载
                //因为在动态环境中需要实时判断
                factory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
    };
}

package com.example.balancer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.util.List;


public class ThreeTimesLoaderBlancer implements ReactorServiceInstanceLoadBalancer {
    //已经被调用的次数
    private int cnt;
    //当前提供服务的实例的编号
    private int idx;
    private final String serviceId;
    private ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

    public ThreeTimesLoaderBlancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider, String serviceId) {
        this.serviceId = serviceId;
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        //实现choose
        ServiceInstanceListSupplier supplier=this.serviceInstanceListSupplierProvider.getIfAvailable();
        return supplier.get().next().map(this::getServiceInstanceResponse);
    }

    public Response<ServiceInstance> getServiceInstanceResponse(List<ServiceInstance>list){
        if(list.size()==0){
            //这是cloud爆无实例错误
            return new EmptyResponse();
        }
        int size=list.size();
        ServiceInstance serviceInstance=null;
        while (serviceInstance==null){
            if (cnt < 3){
                serviceInstance=list.get(idx);
                cnt++;
            }else{
                idx++;
                cnt=0;
                //0 1 2
                //3
                if (idx>=size)idx %= size;
            }
        }

        return new DefaultResponse(serviceInstance);
    }
}

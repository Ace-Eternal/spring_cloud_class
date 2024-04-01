package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
//封装result
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult <T> implements Serializable{
    private Integer code;
    private String info;
    private T result;
}

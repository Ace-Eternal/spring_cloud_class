package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
//数据契约
//json
public class User implements Serializable {
    private Integer userId;
    private String userName;
    private String password;
}

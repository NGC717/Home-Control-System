package com.lq.housesystem.bean;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {

    private Integer id;
    private String username;
    private String password;
    private String tel;
    private Timestamp createTime;

    public User(Integer id, String username, String password, String tel,Timestamp time) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.createTime = time;
    }
}

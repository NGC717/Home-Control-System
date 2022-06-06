package com.lq.housesystem.bean;

import lombok.Data;

@Data
public class Equipment {
    private Integer id;
    private String name;
    private String ip;
    private String location;
    private String remark;
    private Integer type;
    private String state;
    private String turn;

    public Equipment(String ip, String state) {
        this.ip = ip;
        this.state = state;
    }

    public Equipment(Integer id, String name, String location, String remark) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.remark = remark;
    }

    public Equipment(Integer id, String name, String ip, String location, String remark, Integer type, String state) {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.location = location;
        this.remark = remark;
        this.type = type;
        this.state = state;
    }

    public Equipment(String equipmentIp, Integer type, String state) {
        this.ip = equipmentIp;
        this.type = type;
        this.state = state;
    }

    public Equipment(Integer id, String name, String ip, String location, String remark, Integer type, String state, String turn) {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.location = location;
        this.remark = remark;
        this.type = type;
        this.state = state;
        this.turn = turn;
    }
}

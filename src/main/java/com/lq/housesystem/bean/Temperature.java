package com.lq.housesystem.bean;

import lombok.Data;

@Data
public class Temperature {

    private Integer id;
    private Integer temperature;
    private String ip;
    private String time;

    public Temperature(Integer id, Integer temperature, String ip, long currentTimeMillis) {
        this.id = id;
        this.temperature = temperature;
        this.ip = ip;
        this.time = String.valueOf(currentTimeMillis);
    }
}
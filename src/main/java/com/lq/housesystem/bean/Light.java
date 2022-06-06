package com.lq.housesystem.bean;

import lombok.Data;

@Data
public class Light {
    private Integer id;
    private Integer light;
    private String ip;
    private String time;

    public Light(Integer id, Integer light, String ip, long currentTimeMillis) {
        this.id = id;
        this.light = light;
        this.ip = ip;
        this.time = String.valueOf(currentTimeMillis);
    }
}

package com.lq.housesystem.bean;

import lombok.Data;

@Data
public class Humidity {
    private Integer id;
    private Integer humidity;
    private String ip;
    private String time;

    public Humidity(Integer id, Integer humidity, String ip, long currentTimeMillis) {
        this.id = id;
        this.humidity = humidity;
        this.ip = ip;
        this.time = String.valueOf(currentTimeMillis);
    }

}

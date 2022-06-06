package com.lq.housesystem.bean;

import lombok.Data;

@Data
public class Gas {

    private Integer id;
    private Integer gas;
    private String ip;
    private String time;

    public Gas(Integer id, Integer gas, String ip, long currentTimeMillis) {
        this.id = id;
        this.gas = gas;
        this.ip = ip;
        this.time = String.valueOf(currentTimeMillis);
    }
}

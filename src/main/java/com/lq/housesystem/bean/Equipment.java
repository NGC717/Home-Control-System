package com.lq.housesystem.bean;

import lombok.Data;
import lombok.Setter;

@Data
public class Equipment {
    private Integer id;
    private String name;
    private String ip;
    private Integer port;
    private String location;
    private String remark;
    private String type;
    private Integer uId;

    private Integer state;

    public Equipment(Integer id, String name, String ip, Integer port, String location, String remark, String type, Integer uId) {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.location = location;
        this.remark = remark;
        this.type = type;
        this.uId = uId;
    }
}

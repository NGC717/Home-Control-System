package com.lq.housesystem.bean;

import com.lq.housesystem.bean.dataBean.Humidity;
import com.lq.housesystem.bean.dataBean.Light;
import com.lq.housesystem.bean.dataBean.Temperature;

import java.sql.Timestamp;

@lombok.Data
public class Data {
    private Integer id;

    private Light light;
    private Temperature temperature;
    private Humidity humidity;

    private Timestamp time;
}

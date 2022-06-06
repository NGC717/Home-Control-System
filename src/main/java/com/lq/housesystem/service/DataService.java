package com.lq.housesystem.service;

import com.lq.housesystem.bean.Gas;
import com.lq.housesystem.bean.Humidity;
import com.lq.housesystem.bean.Light;
import com.lq.housesystem.bean.Temperature;

import java.util.List;

public interface DataService {
    void insertGas(Gas gas);

    void insertHum(Humidity humidity);

    void insertLight(Light light);

    void insertTemp(Temperature temperature);

    List<Gas> getGasData(int num);

    List<Light> getLightData(int num);

    List<Temperature> getTempData(int num);

    List<Humidity> getHumidityData(int num);

    void deleteLightById(int parseInt);

    void deleteHumidityById(int parseInt);

    void deleteTempById(int parseInt);

    void deleteGasById(int parseInt);
}

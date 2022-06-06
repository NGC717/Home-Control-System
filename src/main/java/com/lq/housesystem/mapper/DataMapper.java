package com.lq.housesystem.mapper;

import com.lq.housesystem.bean.Gas;
import com.lq.housesystem.bean.Humidity;
import com.lq.housesystem.bean.Light;
import com.lq.housesystem.bean.Temperature;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataMapper {

    void insertGas(Gas gas);

    void insertHum(Humidity humidity);

    void insertLight(Light light);

    void insertTemp(Temperature temperature);

    List<Gas> getGasData(int num);

    List<Light> getLightData(int num);

    List<Temperature> getTempData(int num);

    List<Humidity> getHumidityData(int num);

    void deleteGasById(int parseInt);

    void deleteTempById(int parseInt);

    void deleteHumidityById(int parseInt);

    void deleteLightById(int parseInt);
}

package com.lq.housesystem.service.impl;

import com.lq.housesystem.bean.Gas;
import com.lq.housesystem.bean.Humidity;
import com.lq.housesystem.bean.Light;
import com.lq.housesystem.bean.Temperature;
import com.lq.housesystem.mapper.DataMapper;
import com.lq.housesystem.mapper.UserMapper;
import com.lq.housesystem.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataMapper dataMapper;

    @Override
    public void insertGas(Gas gas) {
        dataMapper.insertGas(gas);
    }

    @Override
    public void insertHum(Humidity humidity) {
        dataMapper.insertHum(humidity);
    }

    @Override
    public void insertLight(Light light) {
        dataMapper.insertLight(light);
    }

    @Override
    public void insertTemp(Temperature temperature) {
        dataMapper.insertTemp(temperature);
    }

    @Override
    public List<Gas> getGasData(int num) {
        return dataMapper.getGasData(num);
    }

    @Override
    public List<Light> getLightData(int num) {
        return dataMapper.getLightData(num);
    }

    @Override
    public List<Temperature> getTempData(int num) {
        return dataMapper.getTempData(num);
    }

    @Override
    public List<Humidity> getHumidityData(int num) {
        return dataMapper.getHumidityData(num);
    }

    @Override
    public void deleteLightById(int parseInt) {
        dataMapper.deleteLightById(parseInt);
    }

    @Override
    public void deleteHumidityById(int parseInt) {
        dataMapper.deleteHumidityById(parseInt);
    }

    @Override
    public void deleteTempById(int parseInt) {
        dataMapper.deleteTempById(parseInt);
    }

    @Override
    public void deleteGasById(int parseInt) {
        dataMapper.deleteGasById(parseInt);
    }
}

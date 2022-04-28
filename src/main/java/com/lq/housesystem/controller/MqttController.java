package com.lq.housesystem.controller;

import com.lq.housesystem.mqtt.MqttGateway;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MqttController {

    @Resource
    private MqttGateway mqttGateway;

    @RequestMapping("/mqttSend")
    public String mqttSend(){

        mqttGateway.sendMessage("DATA","31415");

        return "data";
    }
}

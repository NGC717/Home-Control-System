package com.lq.housesystem.bean;

import lombok.Data;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

@Data
public class Client {

    private MqttConnectOptions options;
    private MqttClient mqttClient;

    public Client(MqttConnectOptions options, MqttClient mqttClient) {
        this.options = options;
        this.mqttClient = mqttClient;
    }
}

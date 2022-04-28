package com.lq.housesystem.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "outputChannel")
public interface MqttGateway {
    void sendMessage(@Header(MqttHeaders.TOPIC)String topic,String payload);
}

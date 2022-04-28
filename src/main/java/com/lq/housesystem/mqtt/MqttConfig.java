package com.lq.housesystem.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttConfig {
    @Bean
    public MqttPahoClientFactory mqttClientFactory(){
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();

        options.setServerURIs(new String[]{"tcp://127.0.0.1:1883"});

        options.setUserName("admin");

        String pwd = "super";
        options.setPassword(pwd.toCharArray());

        factory.setConnectionOptions(options);

        return factory;
    }

    @Bean
    public MessageChannel inputChannel(){
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inBound(){
       MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
               "CLIENT-JAVA",mqttClientFactory(),"DATA-TH","DATA-LIGHT","DATA-GAS"
       );

       adapter.setCompletionTimeout(5000);

       DefaultPahoMessageConverter converter = new DefaultPahoMessageConverter();

       adapter.setConverter(converter);

       adapter.setQos(0);

       adapter.setOutputChannel(inputChannel());

       return adapter;
   }

   //接受MQTT服务器发送的消息
    @Bean
    @ServiceActivator(inputChannel = "inputChannel")
    public MessageHandler inputHandler(){
       return message -> {
           String payload = message.getPayload().toString();

           Object o = message.getHeaders().get("mqtt_receivedTopic");

           if (o != null)
               System.out.println(o);
               System.out.println(payload);
               System.out.println(System.currentTimeMillis()/1000);

       };
    }

    @Bean
    public MessageChannel outputChannel(){
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "outputChannel")
    public MessageHandler outputHandler(){
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("DATA-TH",mqttClientFactory());

        messageHandler.setAsync(true);

        messageHandler.setDefaultTopic("SKS");
        messageHandler.setDefaultQos(1);

        DefaultPahoMessageConverter messageConverter = new DefaultPahoMessageConverter();

        messageHandler.setConverter(messageConverter);

        return messageHandler;
    }
}

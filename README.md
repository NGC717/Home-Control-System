# Home-Control-and-Environment-Data-Monitoring-System

Abstract:
In terms of hardware, the system adopts ESP8266 WiFi module, MQ4 gas sensor module, DHT11 temperature and humidity sensor module, photosensitive resistance module and relay module. After each sensor module is connected with ESP8266, it establishes connection with home router through WiFi.Java server, mysql database service, EMQX MQTT server service have been established in software.Relay control and sensor data reading management are completed by sending and receiving information at hardware level and software level.Java server program based on Spring Boot framework.

Working principle:
MQTT client is running on ESP8266. If the sensor is connected, it will actively connect the set WIFI SSID after the power supply is started. After the connection is successful,Publish messages to the MQTT Server (actually to "data-th "," data-light "," data-MQ4 "," data-switch" topics, depending on the device type,Relay/mq4 / dht11 / photosensitive,You can find an array of strings that set these themes in line 57 of the src/main/java/com/lq/housesystem/mqtt/MqttConfig.java file) its own IP address and the connected device type, because in the Java server code sets the subscribe to these topics, so will receive the information, whether these IP has appeared in the database, if there are no will first insert the device information in the database table (IP) and type,Insert data directly into the table if it already exists.Displaying data and sending control signals on the web page are completed by ajax request to the Java server.

what software i installed:
mysql 5.7.0
emqx mqtt server,https://github.com/emqx/emqx
frp client and server(For Intranet penetration)

IDE:
Arduino IDE
Intellij Idea

![image](https://github.com/NGC717/Home-Control-and-Environment-Data-Monitoring-System/blob/master/ProjectImg/actual%20picture.png)
![image](https://github.com/NGC717/Home-Control-and-Environment-Data-Monitoring-System/blob/master/ProjectImg/data%20managment.png)
![image](https://github.com/NGC717/Home-Control-and-Environment-Data-Monitoring-System/blob/master/ProjectImg/device%20add.png)
![image](https://github.com/NGC717/Home-Control-and-Environment-Data-Monitoring-System/blob/master/ProjectImg/device%20control.png)
![image](https://github.com/NGC717/Home-Control-and-Environment-Data-Monitoring-System/blob/master/ProjectImg/sensor%20data%20display.png)

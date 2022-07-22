#include <ESP8266WiFi.h>
#include <PubSubClient.h>

const char* ssid = "H9";
const char* password = "lq123456";
const char* mqtt_server = "192.168.43.2";

WiFiClient espClient;
PubSubClient client(espClient);
unsigned long lastMsg = 0;
#define MSG_BUFFER_SIZE	(50)
char msg[MSG_BUFFER_SIZE];

String ip = "";

bool isChoose = true;

bool isConn = false;

void setup_wifi() {

  delay(10);
  
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);

  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());

  ip = WiFi.localIP().toString();
}

void callback(char* topic, byte* payload, unsigned int length) {
  Serial.print("Message arrived [");
  Serial.print(topic);
  Serial.print("] ");
  
  if((char)payload[0] == '1'){
    digitalWrite(5,HIGH);

    //java服务器接收到开关IP，停止发送ip
  }else if((char)payload[0] == '2'){
    isConn = true;
  }else{
    digitalWrite(5,LOW);
  }
}

void reconnect() {
  
  while (!client.connected()) {
    Serial.print("Attempting MQTT connection...");

    String clientId = "SWITCH-";
    clientId += ip;
  
    if (client.connect(clientId.c_str(),"admin","super")) {
      Serial.println("connected");
      client.publish(ip.c_str(), "STATUS:CONN");
      client.subscribe(ip.c_str());
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println("try again in 5 seconds");
      
      delay(5000);
    }
  }
}

void setup() {
  pinMode(5, OUTPUT);
  Serial.begin(115200);
  setup_wifi();
  client.setServer(mqtt_server, 1883);
  client.setCallback(callback);
}

void loop() {

  if (!client.connected()) {
    reconnect();
  }
  client.loop();

  if(!isConn){
    unsigned long now = millis();
    if (now - lastMsg >= 10000) {
      lastMsg = now;
      String m = "SWITCH-";
      m += WiFi.localIP().toString();
      client.publish("DATA-SWITCH",m.c_str());
    }
  }
}

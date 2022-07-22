#include <ESP8266WiFi.h>
#include <PubSubClient.h>

const char* ssid = "H9";
const char* password = "lq123456";
const char* mqtt_server = "192.168.43.2";

WiFiClient espClient;
PubSubClient client(espClient);
unsigned long lastMsg = 0;
unsigned long updatePer = 10000;
#define MSG_BUFFER_SIZE	(50)
char msg[MSG_BUFFER_SIZE];

String ip = "";

void setup_wifi() {

  delay(10);
  Serial.println();
  Serial.print("Connecting to");
  Serial.println(ssid);

  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  randomSeed(micros());

  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());

  ip = WiFi.localIP().toString();
}

void callback(char* topic, byte* payload, unsigned int length) {
  Serial.print("Msg arrived [");
  Serial.print(topic);
  Serial.print("]:");
  Serial.print((char)payload[0]);
  
  switch(payload[0]){
    case 65:
      updatePer = 10000;//10s,A
      break;
      
    case 66:
      updatePer = 30000;//30s,B
      break;
      
    case 67:
      updatePer = 60000;//1min,C
      break;
      
    case 68:
      updatePer = 600000;//10min,D
      break;
      
    case 69:
      updatePer = 1800000;//30min,E
      break;

    case 70:
      updatePer = 3600000;//60min,F
      break;
      
    default:
      updatePer = 10000;//10s
  }
}

void reconnect() {

  while (!client.connected()) {
    Serial.print("MQTT connection...");
    
    String clientId = "LIGHT-";
    clientId += WiFi.localIP().toString();

    if (client.connect(clientId.c_str(),"admin","super")) {
      Serial.println("connected");

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

  unsigned long now = millis();
  if (now - lastMsg >= updatePer) {
    lastMsg = now;
    int j = analogRead(A0);
    String m = String(j);

    m += '-';
    m += WiFi.localIP().toString();
    
    client.publish("DATA-LIGHT",m.c_str());
  }
}

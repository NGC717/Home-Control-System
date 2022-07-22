#include <MQUnifiedsensor.h>
#include <ESP8266WiFi.h>
#include <PubSubClient.h>
/************************Hardware Related Macros************************************/
#define         Board                   ("ESP8266")
#define         Pin                     (A0)  //Analog input 4 of your arduino
/***********************Software Related Macros************************************/
#define         Type                    ("MQ-4") //MQ4
#define         Voltage_Resolution      (5)
#define         ADC_Bit_Resolution      (10)
#define         RatioMQ4CleanAir        (4.4) //RS / R0 = 60 ppm 
/*****************************Globals***********************************************/
//Declare Sensor
MQUnifiedsensor MQ4(Board, Voltage_Resolution, ADC_Bit_Resolution, Pin, Type);

const char* ssid = "H9";
const char* password = "lq123456";
const char* mqtt_server = "192.168.43.2";

WiFiClient espClient;
PubSubClient client(espClient);
unsigned long lastMsg = 0;
unsigned long updatePer = 10000;
#define MSG_BUFFER_SIZE  (50)
char msg[MSG_BUFFER_SIZE];

String ip = "";

void setup() {

  Serial.begin(9600);

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

  client.setServer(mqtt_server, 1883);
  client.setCallback(callback);
 
  MQ4.setRegressionMethod(1); //_PPM =  a*ratio^b
  MQ4.setA(1012.7); MQ4.setB(-2.786); // Configure the equation to to calculate CH4 concentration
  /*
  Exponential regression:
  Gas    | a      | b
  LPG    | 3811.9 | -3.113
  CH4    | 1012.7 | -2.786
  CO     | 200000000000000 | -19.05
  Alcohol| 60000000000 | -14.01
  smoke  | 30000000 | -8.308
  */

  
  /*****************************  MQ Init ********************************************/ 
  //Remarks: Configure the pin of arduino as input.
  /************************************************************************************/ 
  MQ4.init(); 
  Serial.print("Calibrating please wait.");
  float calcR0 = 0;
  for(int i = 1; i<=10; i ++)
  {
    MQ4.update(); // Update data, the arduino will read the voltage from the analog pin
    calcR0 += MQ4.calibrate(RatioMQ4CleanAir);
    Serial.print(".");
  }
  MQ4.setR0(calcR0/10);
  Serial.println("  done!.");
  
  if(isinf(calcR0)) {Serial.println("Warning: Conection issue, R0 is infinite (Open circuit detected) please check your wiring and supply"); while(1);}
  if(calcR0 == 0){Serial.println("Warning: Conection issue found, R0 is zero (Analog pin shorts to ground) please check your wiring and supply"); while(1);}
  /*****************************  MQ CAlibration ********************************************/ 
  MQ4.serialDebug(true);
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
    Serial.print("Attempting MQTT connection...");

    String clientId = "MQ4-";
    clientId += WiFi.localIP().toString();
  
    if (client.connect(clientId.c_str(),"admin","super")) {
      Serial.println("connected");
    
      client.publish(ip.c_str(), "STATUS:CONN");
   
      client.subscribe(ip.c_str());
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      
      delay(5000);
    }
  }
}

void loop() {

  if (!client.connected()) {
    reconnect();
  }
  
  client.loop();
  
  MQ4.update(); 

  unsigned long now = millis();
  if (now - lastMsg >= updatePer) {
    lastMsg = now;
    
    float ppm = MQ4.readSensor(); 
    
    String m = String(ppm);

    m += '-';

    m += WiFi.localIP().toString();

    client.publish("DATA-MQ4",m.c_str());
  }
}

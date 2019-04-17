//引入DTH库
#include <dht11.h>

//定义引脚
#define DHT11PIN 2      //温湿度传感器引脚
dht11 DHT11;           //实例化对象

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(DHT11PIN,OUTPUT); 
}

void loop() {
  
  // put your main code here, to run repeatedly:
  int chk = DHT11.read(DHT11PIN);          //将读取到的值赋给chk
  int tem=(float)DHT11.temperature;        //将温度值赋值给tem
  int hum=(float)DHT11.humidity;                   //将湿度值赋给hum
  String str=String(tem)+" "+String(hum);
  Serial.println(str);
  
  //Serial.println("*"+String(tem)+" "+String(hum)+" "+String(tem)+" "+String(tem)+" "+String(tem)+" "+String(tem));
  
  delay(1000);                                          //延时一段时间
}

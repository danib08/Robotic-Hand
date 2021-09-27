 
// Incluímos la librería para poder controlar el servo
#include <Servo.h>
 
// Declaramos la variable para controlar el servo
Servo servoMotor1;
Servo servoMotor2;
Servo servoMotor3;
Servo servoMotor4;
Servo servoMotor5;

String input;
void setup() {
  Serial.begin(9600);
   
  servoMotor1.attach(12);
  servoMotor2.attach(2);
  servoMotor3.attach(3);
  servoMotor4.attach(4);
  servoMotor5.attach(7);


  servoMotor1.write(170);
  servoMotor2.write(0);
  servoMotor3.write(0);
  servoMotor4.write(0);
  servoMotor5.write(0);
}
 
void loop() {
     input = Serial.readString();
     moveServo(input);
     
}
void moveServo(String fingerAction){
  switch(fingerAction[0]) {
          case 'P':
            if (fingerAction[1] == 'S'){
              Serial.println("sube P");
              servoMotor1.write(170);
              
            }else{
                Serial.println("baja P");
                servoMotor1.write(0);
                
            }
            break;
          case 'I':
            if (fingerAction[1] == 'S'){
              Serial.println("sube I");
              servoMotor2.write(0);
            }else{
                Serial.println("baja I");
                servoMotor2.write(170);
            }
            break;
          case 'M':
            if (fingerAction[1] == 'S'){
              Serial.println("sube M");
              servoMotor3.write(0);
            }else{
                Serial.println("baja M");
                servoMotor3.write(170);
            }
            break;
          case 'A':
            if (fingerAction[1] == 'S'){
              Serial.println("sube A");
              servoMotor4.write(0);
            }else{
                Serial.println("baja A");
                servoMotor4.write(170);
            }
            break;
          case 'Q':
            if (fingerAction[1] == 'S'){
              Serial.println("sube Q");
              servoMotor5.write(0);
            }else{
                Serial.println("baja Q");
                servoMotor5.write(170);
            }
            break;
          case 'T':
            if (fingerAction[1] == 'S'){
              Serial.println("sube T");
              servoMotor1.write(170);
              servoMotor2.write(0);
              servoMotor3.write(0);
              servoMotor4.write(0);
              servoMotor5.write(0);
            }else{
                Serial.println("baja T");
                servoMotor1.write(0);
                servoMotor2.write(170);
                servoMotor3.write(170);
                servoMotor4.write(170);
                servoMotor5.write(170);
            }
            break;
          default:
            break;
   }
  
  }

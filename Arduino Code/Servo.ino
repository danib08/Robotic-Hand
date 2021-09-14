 
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
   
  servoMotor2.attach(1);
  servoMotor3.attach(2);
  servoMotor4.attach(3);
  servoMotor5.attach(4);
  servoMotor1.attach(5);
}
 
void loop() {
    if (Serial.available() > 0) {
      input = Serial.readString();
      switch(input[0]) {
          case 'P':
            if (input[1] == 'S'){
              Serial.println("sube P");
              servoMotor1.write(170);
            }else{
                Serial.println("baja P");
                servoMotor1.write(0);
            }
            break;
          case 'I':
            if (input[1] == 'S'){
              Serial.println("sube I");
              servoMotor2.write(180);
            }else{
                Serial.println("baja I");
                servoMotor2.write(0);
            }
            break;
          case 'M':
            if (input[1] == 'S'){
              Serial.println("sube M");
              servoMotor3.write(180);
            }else{
                Serial.println("baja M");
                servoMotor3.write(0);
            }
            break;
          case 'A':
            if (input[1] == 'S'){
              Serial.println("sube A");
              servoMotor4.write(180);
            }else{
                Serial.println("baja A");
                servoMotor4.write(0);
            }
            break;
          case 'Q':
            if (input[1] == 'S'){
              Serial.println("sube Q");
              servoMotor5.write(180);
            }else{
                Serial.println("baja Q");
                servoMotor5.write(0);
            }
            break;
          case 'T':
            if (input[1] == 'S'){
              Serial.println("sube T");
              servoMotor1.write(180);
              servoMotor2.write(180);
              servoMotor3.write(180);
              servoMotor4.write(180);
              servoMotor5.write(180);
            }else{
                Serial.println("baja T");
                servoMotor1.write(0);
                servoMotor2.write(0);
                servoMotor3.write(0);
                servoMotor4.write(0);
                servoMotor5.write(0);
            }
            break;
          default:
            break;
        }
      }

}
  
  // Desplazamos a la posición 90º
 // servoMotor.write(90);
  // Esperamos 1 segundo
  //delay(1000);
  
  // Desplazamos a la posición 180º
 // servoMotor.write(180);
  // Esperamos 1 segundo
 // delay(1000);

//ARDUINO VOOR DE GRIJPER EN HOOGTE HIERMEE GEBRUIKEN
int MOTOR_GRAB = 4;
int MOTOR_GRAB_SPEED = 5;
int MOTOR_LIFT = 7;
int MOTOR_LIFT_SPEED = 6;
int grabHeight = 200;

void setup() {
   for(int i = 4; i < 8; i++){
    pinMode(i, OUTPUT); 
  }
}

void pak(){
  //Grijper naar voren
  digitalWrite(MOTOR_GRAB, HIGH);
  analogWrite(MOTOR_GRAB_SPEED, 125);
  delay(1200);
  digitalWrite(MOTOR_GRAB_SPEED, 0);
  delay(700);
  
  //Grijper naar boven
  //omhoog(grabHeight);
  //delay(200);
  
  //Grijper naar achteren
  digitalWrite(MOTOR_GRAB, LOW);
  analogWrite(MOTOR_GRAB_SPEED, 125);
  delay(1200);
  analogWrite(MOTOR_GRAB_SPEED, 0);
  delay(200);

  //Grijper naar oude positie
  //omlaag(grabHeight);
  delay(200);
}

void loop() {
  //delay(500);
  pak();
  //delay(1000);
}

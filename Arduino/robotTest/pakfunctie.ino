//ARDUINO VOOR DE GRIJPER EN HOOGTE HIERMEE GEBRUIKEN
int MOTOR_GRAB = 11;
int MOTOR_GRAB_SPEED = 10;
int grabHeight = 200;

void pak() {
  //iets omhoog voor het pakken
  digitalWrite(MOTOR_LIFT, HIGH);
  digitalWrite(MOTOR_LIFT_SPEED, LOW);
  delay(200);
 digitalWrite(MOTOR_LIFT, LOW);
  digitalWrite(MOTOR_LIFT_SPEED, LOW);
    return;
  //Grijper naar voren
  digitalWrite(MOTOR_GRAB, HIGH);
  analogWrite(MOTOR_GRAB_SPEED, 125);
  delay(1200);
  digitalWrite(MOTOR_GRAB_SPEED, 0);
  delay(700);

  //grijper naar boven
  digitalWrite(MOTOR_LIFT, HIGH);
  analogWrite(MOTOR_LIFT_SPEED, 255);
  delay(grabHeight);
  analogWrite(MOTOR_LIFT_SPEED, 0);
  
  //Grijper naar achteren
  digitalWrite(MOTOR_GRAB, LOW);
  analogWrite(MOTOR_GRAB_SPEED, 125);
  delay(1200);
  analogWrite(MOTOR_GRAB_SPEED, 0);
  delay(200);

  moveToDestY(5);
  moveToDestX(6);
  moveToDestY(4);

  //grijper naar voren boven bpp
  digitalWrite(MOTOR_GRAB, HIGH);
  analogWrite(MOTOR_GRAB_SPEED, 125);
  delay(1200);
  digitalWrite(MOTOR_GRAB_SPEED, 0);

  //omlaag om te laten vallen
  digitalWrite(MOTOR_LIFT, LOW);
  analogWrite(MOTOR_LIFT_SPEED, 255);
  delay(200);
  analogWrite(MOTOR_LIFT_SPEED, 0);

  //Grijper naar achteren om te laten vallen
  digitalWrite(MOTOR_GRAB, LOW);
  analogWrite(MOTOR_GRAB_SPEED, 125);
  delay(1200);
  analogWrite(MOTOR_GRAB_SPEED, 0);
}


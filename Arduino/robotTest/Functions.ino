
void rechts() {
  while(reading sensor)
   beweeg naar rechts
  if(sensorread is black) {
    posY++;
    stoprobot
  }
  digitalWrite(MOTOR_GROUND, HIGH);
  analogWrite(MOTOR_GROUND_SPEED, 175);
}

void moveToDest(x, y) {
  while(currentX, currentY ! = x, y) {
    if(currentX < x && currentY < y) {
      omhoog();
      rechts();
      if(currentX == x && currentY == y)
    }
  }
  
}

void links(int tijd) {
  digitalWrite(MOTOR_GROUND, LOW);
  analogWrite(MOTOR_GROUND_SPEED, 175);
  omlaag(15000);     
  delay(tijd);
  digitalWrite(MOTOR_GROUND, HIGH);
  delay(20);
}

void omhoog(int tijd) {
  for(int i = 255; i > 0; i--) {
    delay(75);
    digitalWrite(MOTOR_LIFT, LOW);
    analogWrite(MOTOR_LIFT_SPEED, i);
    //delay(10);
  }

}

void omlaag(int tijd) {
    for(int i = 255; i > 0; i--) {
    delay(75);
    digitalWrite(MOTOR_LIFT, HIGH);
    analogWrite(MOTOR_LIFT_SPEED, i);
    //delay(10);
  }
}

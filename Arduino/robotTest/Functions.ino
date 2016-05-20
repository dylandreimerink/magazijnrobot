void moveToDest(int x, int y) {
  readingSensor = true;
  while(currentX != x && currentY != y) {
    if(currentX < x && currentY < y) {
      omhoog();
      rechts();
      if(currentX == x && currentY == y) {
        hold();
      }
    }
    else if(currentX > x && currentY > y) {
      omlaag();
      links();
      if(currentX == x && currentY == y) {
        hold();
        readingSensor = false; 
       } 
     }
  } 
}

void hold() {
      analogWrite(MOTOR_GROUND_SPEED, 0);
      analogWrite(MOTOR_LIFT_SPEED, 0);
}

void rechts() {
  while(readingSensor) {
    digitalWrite(MOTOR_GROUND, HIGH);
    analogWrite(MOTOR_GROUND_SPEED, 175);
    readSensor_X();
    if(readSensor_X()) {
      currentX++;
    }
  }
}

void links() {
  while(readingSensor) {
    digitalWrite(MOTOR_GROUND, LOW);
    analogWrite(MOTOR_GROUND_SPEED, 175);
    if(readSensor_X()) {
      currentX--;
    }
  }
}

void omhoog() {
  while(readingSensor) {
    digitalWrite(MOTOR_LIFT, HIGH);
    analogWrite(MOTOR_LIFT_SPEED, 175);
    readSensor_Y();
    if(readSensor_Y()) {
      currentY++;
    }
  }
}

void omlaag() {
  while(readingSensor) {
    digitalWrite(MOTOR_LIFT, HIGH);
    analogWrite(MOTOR_LIFT_SPEED, 175);
    readSensor_Y();
    if(readSensor_Y()) {
      currentY--;
    }
  }
}

boolean readSensor_X(){
  Serial.println(analogRead(sensorX));
  if(analogRead(sensorX) < sensorValBlack){
    return(true);
  }
  else{
    return(false);
  }
}

boolean readSensor_Y(){
  Serial.println(analogRead(sensorY));
  if(analogRead(sensorY) < sensorValBlack){
     return(true);
   }
  else{
    return(false);
  }
}

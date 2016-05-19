void moveToDest(x, y) {
  readingSensor = true;
  while(currentX ! = x && currentY != y) {
    if(currentX < x && currentY < y) {
      omhoog();
      rechts();
      if(currentX == x && currentY == y) {
        stopRobot();
      }
    }
    else if(currentX > x && currentY > y) {
      omlaag();
      links();
      if(currentX == x && currentY == y) {
        stopRobot();
        readingSensor = false; 
       } 
     }
  } 
}

void rechts() {
  while(readingSensor) {
    digitalWrite(MOTOR_GROUND, HIGH);
    analogWrite(MOTOR_GROUND_SPEED, 175);
    readSensor_X();
    if(readSensor()) {
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
      posY++;
    }
  }
}

void omlaag() {
  while(readingSensor) {
    digitalWrite(MOTOR_LIFT, HIGH);
    analogWrite(MOTOR_LIFT_SPEED, 175);
    readSensor_Y();
    if(readSensor_Y()) {
      posY--;
    }
  }
}

boolean readSensor_X(){
  Serial.println(analogRead(sensorX));
  if(analogRead(sensorX) < SensorIsZwart){
    return(true);
    Serial.println(sensorX);
  }
  else{
    return(false);
    Serial.println(sensorX);
  }
}

boolean readSensor_Y(){
  Serial.println(analogRead(sensorY));
  if(analogRead(sensorY) < SensorIsZwart){
     return(true);
     Serial.println(sensorY);
   }
  else{
    return(false);
    Serial.println(sensorY);
  }
}

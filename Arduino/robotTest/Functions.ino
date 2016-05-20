//void moveToDest(int x, int y) {
//  while(currentX != x || currentY != y) {
//    //Serial.print("X: "); Serial.println(currentX);
//    
//    //Serial.print("Y: "); Serial.println(currentY);
//    if(currentX <= x){
//      rechts();
//    }else if( currentY < y) {
//      omhoog();
//    }else if(currentX > x) {
//     links(); 
//    }else if( currentY > y) {
//      omlaag();
//    }else{
//        hold();
//      }
//   }
//}

void moveToDestX(int x){
   while(currentX != x){
      Serial.print("X: "); Serial.println(currentX);
      if(currentX <= x){
        rechts();
      }else if(currentX >= x){
        links();
      }
   }
   hold();
}


void moveToDestY(int y){
   while(currentY != y){
      Serial.print("Y: "); Serial.println(currentY);
      if(currentY <= y){
        omlaag();
      }else if(currentY >= y){
        omhoog();
      }
   }
   hold();
}

void hold() {
    analogWrite(MOTOR_GROUND_SPEED, 0);
    analogWrite(MOTOR_LIFT_SPEED, 0);
    Serial.println("HOLD");
}

void rechts() {
    digitalWrite(MOTOR_GROUND, LOW);
    analogWrite(MOTOR_GROUND_SPEED, motorSpeed);
    delay(1000);
    while(readSensor_X() != true){
      Serial.println("wacht op volgende zwarte blokje..:)");
    }
    currentX++;    
}

void links() {
    digitalWrite(MOTOR_GROUND, HIGH);
    analogWrite(MOTOR_GROUND_SPEED, motorSpeed);
    delay(1000);
    while(readSensor_X() != true){
      Serial.println("wacht op volgende zwarte blokje..:)");
    }
    currentX--;
}

void omhoog() {
    digitalWrite(MOTOR_LIFT, HIGH);
    analogWrite(MOTOR_LIFT_SPEED, motorSpeed);
    delay(1000);
      while(readSensor_Y() != true){
      Serial.println("wacht op volgende zwarte blokje..:)");
    }
    currentY--;
}

void omlaag() {
    digitalWrite(MOTOR_LIFT, LOW);
    analogWrite(MOTOR_LIFT_SPEED, motorSpeed);
    Serial.println("delay");
    delay(1000);

    while(readSensor_Y() != true){
      Serial.println("wacht op volgende zwarte blokje..:)");
    }
    currentY++;
}

boolean readSensor_X(){
  Serial.println(analogRead(sensorX));
  if(analogRead(sensorX) > sensorValBlack){
    Serial.println("zwart");
    return(true);
  }
  else{
    return(false);
  }
}

boolean readSensor_Y(){
  Serial.println(analogRead(sensorY));
  if(analogRead(sensorY) > sensorValBlack){
    Serial.println("zwart");
    return(true);
  }
  else{
    return(false);
  }
}

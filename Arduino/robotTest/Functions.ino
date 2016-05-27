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

void moveToLocation(int x, int y) {
  moveToDestX(x);
  moveToDestY(y);
}
//go right && left


//void moveToLocation(int x, int y){
//  if(currentX < x || currentY > y) {
//    digitalWrite(MOTOR_GROUND, LOW);
//    analogWrite(MOTOR_GROUND_SPEED, motorSpeed);
//    analogWrite(MOTOR_LIFT, HIGH);
//    analogWrite(MOTOR_LIFT_SPEED, motorSpeed);
//    delay(300);
//  }
//  if(currentX > x || currentY < y) {
//    digitalWrite(MOTOR_GROUND, HIGH);
//    analogWrite(MOTOR_GROUND_SPEED, 255);
//    digitalWrite(MOTOR_LIFT, LOW);
//    analogWrite(MOTOR_LIFT_SPEED, 255);
//    delay(300);
//  }
//
//  boolean checkX = true;
//  boolean checkY = true;
//  while(currentX != x || currentY != y) {
//    if(currentX < x || currentY > y) {
//      if(readSensor_X() == true && currentX != x && checkX) {;
//        currentX++;
//        checkX = false;
//      }
//      if(readSensor_Y() == true && currentY != y && checkY){
//        currentY--;
//        checkY = false;
//      }
//      if(readSensor_X() == false){
//        checkX = true;
//      }
//      if(readSensor_Y() == false){
//        checkY = true;
//      }
//     if(currentX == x) {
//        analogWrite(MOTOR_GROUND_SPEED, 0);
//     }
//     if(currentY == y) {
//        analogWrite(MOTOR_LIFT_SPEED, 0);    
//     }
//    }
//  if(currentX > x || currentY < y) {
//      if(readSensor_X() == true && currentX != x && checkX) {;
//        currentX--;
//        checkX = false;
//      }
//      if(readSensor_Y() == true && currentY != y && checkY){
//        currentY++;
//        checkY = false;
//      }
//      if(readSensor_X() == false){
//        checkX = true;
//      }
//      if(readSensor_Y() == false){
//        checkY = true;
//      }
//     if(currentX == x) {
//        analogWrite(MOTOR_GROUND_SPEED, 0);
//     }
//     if(currentY == y) {
//        analogWrite(MOTOR_LIFT_SPEED, 0);    
//     }
//    }
//  }
//  Serial.print("x:" );
//  Serial.println(currentX);
//  
//  Serial.print("y:" );
//  Serial.println(currentY);
//}


void pak(){
   digitalWrite(MOTOR_LIFT, HIGH);
  analogWrite(MOTOR_LIFT_SPEED, 255);
  delay(160); //kleinstukje omhoog voor het pakken
  analogWrite(MOTOR_LIFT_SPEED, 0);
  
  digitalWrite(MOTOR_PAK0, HIGH);
  digitalWrite(MOTOR_PAK1, LOW);
  analogWrite(MOTOR_PAK_SPEED, 255);
  delay(1800);
  analogWrite(MOTOR_PAK_SPEED, 0);
  
  digitalWrite(MOTOR_LIFT, HIGH);
  analogWrite(MOTOR_LIFT_SPEED, 255);
  delay(400); //delay to lift the product
 analogWrite(MOTOR_LIFT_SPEED, 0);
  
  digitalWrite(MOTOR_PAK0, LOW);
  digitalWrite(MOTOR_PAK1, HIGH);
  analogWrite(MOTOR_PAK_SPEED, 255);
  delay(1800);
  analogWrite(MOTOR_PAK_SPEED, 0);

  digitalWrite(MOTOR_LIFT, LOW);
  analogWrite(MOTOR_LIFT_SPEED, 255);
  delay(800);
  analogWrite(MOTOR_LIFT_SPEED, 0);
    
  //moveToLocation(x,5);
  moveToLocation(6,3); //boven de bpp
  
  digitalWrite(MOTOR_PAK0, HIGH);
  digitalWrite(MOTOR_PAK1, LOW);
  analogWrite(MOTOR_PAK_SPEED, 255);
  delay(1500);
  analogWrite(MOTOR_PAK_SPEED, 0);
  
  moveToLocation(6,4);

  digitalWrite(MOTOR_PAK0, LOW);
  digitalWrite(MOTOR_PAK1, HIGH);
  analogWrite(MOTOR_PAK_SPEED, 255);
  delay(1500);
  analogWrite(MOTOR_PAK_SPEED, 0);

  moveToLocation(6,3);
}

void moveToStart(){
  moveToLocation(1, 5);
}
void moveToDestX(int x){
   while(currentX != x){
      if(currentX <= x){
        rechts();
      }else if(currentX >= x){
        links();
      }
   }
   hold();
}

//BIJ Y AS ALLES ANDERSOM OMDAT Y-AS VAN BOVEN NAAR BENEDEN WORDT GETELD
void moveToDestY(int y){
   while(currentY != y){
      if(currentY > y){
        omhoog();
      }else if(currentY < y){
        omlaag();
      }
   }
   hold();
}

void hold() {
    analogWrite(MOTOR_GROUND_SPEED, 0);
    analogWrite(MOTOR_LIFT_SPEED, 0);
}

void rechts() {
    digitalWrite(MOTOR_GROUND, LOW);
    analogWrite(MOTOR_GROUND_SPEED, motorSpeed);
    delay(300);
    while(readSensor_X() != true){
     // Serial.println("wacht op volgende zwarte blokje..:)");
    }
    currentX++;    
}

void links() {
    digitalWrite(MOTOR_GROUND, HIGH);
    analogWrite(MOTOR_GROUND_SPEED, motorSpeed);
    delay(300);
    while(readSensor_X() != true){
     // Serial.println("wacht op volgende zwarte blokje..:)");
    }
    currentX--;
}

void omhoog() {
    digitalWrite(MOTOR_LIFT, HIGH);
    analogWrite(MOTOR_LIFT_SPEED, motorSpeed);
    delay(1000);
    while(readSensor_Y() != true){
      //Serial.println("wacht op volgende zwarte blokje..:)");
    }
    currentY--;
}

void omlaag() {
    digitalWrite(MOTOR_LIFT, LOW);
    analogWrite(MOTOR_LIFT_SPEED, motorSpeed);
    //Serial.println("delay");
    delay(1000);

    while(readSensor_Y() != true){
     // Serial.println("wacht op volgende zwarte blokje..:)");
    }
    currentY++;
}

boolean readSensor_X(){
 // Serial.println(analogRead(sensorX));
  if(analogRead(sensorX) > sensorXBlack){
   // Serial.println("zwart");
    return(true);
  }
  else{
    return(false);
  }
}

boolean readSensor_Y(){
 // Serial.println(analogRead(sensorY));
  if(analogRead(sensorY) > sensorYBlack){
    //Serial.println("zwart");
    delay(100);
    return(true);
  }
  else{
    return(false);
  }
}

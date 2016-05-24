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
   while(currentX != x || currentY != y){
      Serial.print("X: "); Serial.println(currentX);
      Serial.print("Y: "); Serial.println(currentY);
      if(currentX < x){
        //gaat naar rechts
         digitalWrite(MOTOR_GROUND, LOW);
         analogWrite(MOTOR_GROUND_SPEED, motorSpeed);
      }else if(currentX > x){
        //gaat naar links
         digitalWrite(MOTOR_GROUND, HIGH);
         analogWrite(MOTOR_GROUND_SPEED, motorSpeed);
      }else {
        analogWrite(MOTOR_GROUND_SPEED, 0);
      }

      if(currentX < x){
         if(readSensor_X() != true){
            currentX++;
          }   
      }else if(currentX > x){
          if(readSensor_X() != true){
            currentX--;
          }   
      }
      
      if(currentY > y){
        //gaat omhoog
        digitalWrite(MOTOR_LIFT, HIGH);
        analogWrite(MOTOR_LIFT_SPEED, motorSpeed);
      }else if(currentY < y){
        // gaat omlaag
        digitalWrite(MOTOR_LIFT, LOW);
        analogWrite(MOTOR_LIFT_SPEED, motorSpeed);
      } else {
        analogWrite(MOTOR_LIFT_SPEED, 0);
      }

      if(currentY > y){
        while(readSensor_Y() != true){
            currentY--;
           }
      }else if(currentY < y){
          while(readSensor_Y() != true){
            currentY++;
           }
      } 
      
   }
   hold(); 
  delay(2000);
}

void moveToStart(){
  moveToLocation(1, 5);
}
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

//BIJ Y AS ALLES ANDERSOM OMDAT Y-AS VAN BOVEN NAAR BENEDEN WORDT GETELD
void moveToDestY(int y){
   while(currentY != y){
      Serial.print("Y: "); Serial.println(currentY);
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
    Serial.println("HOLD");
}

void rechts() {
    digitalWrite(MOTOR_GROUND, LOW);
    analogWrite(MOTOR_GROUND_SPEED, motorSpeed);
    delay(300);
    while(readSensor_X() != true){
      Serial.println("wacht op volgende zwarte blokje..:)");
    }
    currentX++;    
}

void links() {
    digitalWrite(MOTOR_GROUND, HIGH);
    analogWrite(MOTOR_GROUND_SPEED, motorSpeed);
    delay(300);
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
  if(analogRead(sensorX) > sensorXBlack){
    Serial.println("zwart");
    return(true);
  }
  else{
    return(false);
  }
}

boolean readSensor_Y(){
  Serial.println(analogRead(sensorY));
  if(analogRead(sensorY) > sensorYBlack){
    Serial.println("zwart");
    delay(100);
    return(true);
  }
  else{
    return(false);
  }
}










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
    if(sensorread is black) {
      currentY++;
    }
  }
}

void links() {
  while(readingSensor) {
    digitalWrite(MOTOR_GROUND, LOW);
    analogWrite(MOTOR_GROUND_SPEED, 175);
    if(sensorread is black) {
      currentY--;
    }
  }
}

void omhoog() {
  while(readingSensor) {
    digitalWrite(MOTOR_LIFT, HIGH);
    analogWrite(MOTOR_LIFT_SPEED, 175);
    if(sensorread is black) {
      posX++;
    }
  }
}

void omlaag() {
  while(readingSensor) {
    digitalWrite(MOTOR_LIFT, HIGH);
    analogWrite(MOTOR_LIFT_SPEED, 175);
    if(sensorread is black) {
      posX--;
    }
  }
}

  int MOTOR_GROUND = 4;
  int MOTOR_GROUND_SPEED = 5;
  int MOTOR_LIFT = 7;
  int MOTOR_LIFT_SPEED = 6;

void setup() {
  // put your setup code here, to run once:
  for(int i = 4; i < 8; i++){
    pinMode(i, OUTPUT); 
  }

}

void loop() {
  delay(500);
  rechts(15000);
  delay(500);
  links(15000);
  delay(500);
}

void rechts(int tijd) {
  digitalWrite(MOTOR_GROUND, HIGH);
  analogWrite(MOTOR_GROUND_SPEED, 175);
  omhoog(15000);
  delay(tijd);
  digitalWrite(MOTOR_GROUND, LOW);
  delay(20);
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

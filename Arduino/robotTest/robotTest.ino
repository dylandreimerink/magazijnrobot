  
 /*
  * Pins defined for moters 
  */
  int MOTOR_GROUND = 4;
  int MOTOR_GROUND_SPEED = 5;
  int MOTOR_LIFT = 7;
  int MOTOR_LIFT_SPEED = 6;

/*
 * X & Y to determine the current pos
 */
  int CURRENT_X = 0;
  int CURRENT_Y = 0;
  

void setup() {
  // put your setup code here, to run once:
  for(int i = 4; i < 8; i++){
    pinMode(i, OUTPUT); 
  }

}

void loop() {

}


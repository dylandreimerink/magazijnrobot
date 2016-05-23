/*
 * Analog ports of sensors
 */
  const int sensorX = A0;
  const int sensorY = A2;

 /*
  * When sensor reads black
  */
  const int sensorYBlack = 200; 
  const int sensorXBlack = 200; 
  const int motorSpeed = 255; 
  
 /*
  * Pins defined for moters 
  */
  int MOTOR_GROUND = 7;
  int MOTOR_GROUND_SPEED = 6;
  int MOTOR_LIFT = 4;
  int MOTOR_LIFT_SPEED = 5;

 /*
  * X & Y to determine the current pos
  */
  int currentX = 1;
  int currentY = 5;

 /*
  * x, y pos from asrs 
  */
  int x, y;
  /*
   * boolean read sensors.  
   * While the robot is moving, readingSensor is TRUE
   */
  String readString="";
  boolean readingSensor = false;
  //boolean readSensor;
  

void setup() {
  //LANGS ALLE VAKJES 1 VOOR 1 MET EEN HALVE SECONDE DELAY
//  for(int i = 4; i < 12; i++){
//    pinMode(i, OUTPUT); 
//  }
//  Serial.begin(9600);
//  for(int i=5; i>0; i--){
//    moveToDestY(i);
//    for(int ii=1; ii<3;ii++){
//      moveToDestX(ii);
//      delay(700);
//    }
//  }
  
  //NAAR EINDE
//  moveToDestX(1);
//  moveToDestY(5);
//  pak();
}

void loop() {
  moveToLocation(1,9);
//  moveToLocation(5,1);
//  moveToLocation(1,2);
//  moveToLocation(3,3);
//  moveToStart();
  delay(5000);
}


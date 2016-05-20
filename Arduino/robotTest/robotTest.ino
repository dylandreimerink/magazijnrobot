/*
 * Analog ports of sensors
 */
  const int sensorX = A0;
  const int sensorY = A2;

 /*
  * 
  */
  const int sensorValBlack = 800;  
  
 /*
  * Pins defined for moters 
  */
  int MOTOR_GROUND = 6;
  int MOTOR_GROUND_SPEED = 7;
  int MOTOR_LIFT = 7;
  int MOTOR_LIFT_SPEED = 6;

 /*
  * X & Y to determine the current pos
  */
  int currentX = 0;
  int currentY = 0;

 /*
  * x, y pos from asrs 
  */
  int x, y;
  /*
   * boolean read sensors.  
   * While the robot is moving, readingSensor is TRUE
   */
  boolean readingSensor = false;
  //boolean readSensor;
  

void setup() {
  // put your setup code here, to run once:
  for(int i = 4; i < 8; i++){
    pinMode(i, OUTPUT); 
  }
  Serial.begin(9600);
   moveToDest(1,0);
}

void loop() {
 
}


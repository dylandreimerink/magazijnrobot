/*
 * Analog ports of sensors
 */
  const int sensorX = A0;
  const int sensorY = A2;
  const int randSeed = A5;

 /*
  * When sensor reads black
  */
  const int sensorYBlack = 200; 
  const int sensorXBlack = 200; 
  const int motorSpeed = 180; 
  
 /*
  * Pins defined for moters 
  */
  int MOTOR_GROUND = 7;
  int MOTOR_GROUND_SPEED = 6;
  int MOTOR_LIFT = 4;
  int MOTOR_LIFT_SPEED = 5;
  int MOTOR_PAK0 = 52;
  int MOTOR_PAK1 = 53; 
  int MOTOR_PAK_SPEED = 11; //pwm

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
  pinMode(randSeed, INPUT);
  digitalWrite(8, LOW);
  digitalWrite(9, LOW);
  randomSeed(analogRead(randSeed));
}

void loop() {
  //moveToLocation(4,4);
  //moveToLocation(5,1);
  //moveToLocation(1,2);
  int x = random(1,6);
  int y = random(1,6);

  moveToLocation(3,1);
  //moveToStart();
  //delay(5000);
  digitalWrite(MOTOR_LIFT, HIGH);
  analogWrite(MOTOR_LIFT_SPEED, 255);
  delay(300);
  analogWrite(MOTOR_LIFT_SPEED, 0);
  
  digitalWrite(MOTOR_PAK0, HIGH);
  digitalWrite(MOTOR_PAK1, LOW);
  analogWrite(MOTOR_PAK_SPEED, 255);
  delay(1500);
  analogWrite(MOTOR_PAK_SPEED, 0);
  
  digitalWrite(MOTOR_LIFT, HIGH);
  analogWrite(MOTOR_LIFT_SPEED, 255);
  delay(900); //delay to lift the product
 analogWrite(MOTOR_LIFT_SPEED, 0);
  
  digitalWrite(MOTOR_PAK0, LOW);
  digitalWrite(MOTOR_PAK1, HIGH);
  analogWrite(MOTOR_PAK_SPEED, 255);
  delay(1500);
  analogWrite(MOTOR_PAK_SPEED, 0);

  digitalWrite(MOTOR_LIFT, LOW);
  analogWrite(MOTOR_LIFT_SPEED, 255);
  delay(1400);
  analogWrite(MOTOR_LIFT_SPEED, 0);
    
  delay(1000);
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
  //delay(1000);
  //moveToStart();
  //delay(5000);
}


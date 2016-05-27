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
  const int motorSpeed = 200; 
  
 /*
  * Pins defined for moters 
  */
  int MOTOR_GROUND = 7;
  int MOTOR_GROUND_SPEED = 6;
  int MOTOR_LIFT = 4;
  int MOTOR_LIFT_SPEED = 5;
  int MOTOR_PAK0 = 12;
  int MOTOR_PAK1 = 13; 
  int MOTOR_PAK_SPEED = 11; //pwm

 /*
  * X & Y to determine the current pos
  */
  int currentX = 1;
  int currentY = 5;


const int bppEnablePin = 8;
const int bppDirPin = 9;

int getx = 0;
int gety = 0;
bool hasx = false;
bool hasy = false;
bool start = false;
char curVal = '0';


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
  pinMode(bppEnablePin, OUTPUT);
  pinMode(bppDirPin, OUTPUT);
  pinMode(MOTOR_PAK0, OUTPUT);
   pinMode(MOTOR_PAK1, OUTPUT);
   pinMode(MOTOR_PAK_SPEED, OUTPUT);
  digitalWrite(bppEnablePin, LOW);
  digitalWrite(bppDirPin, LOW);
  randomSeed(analogRead(randSeed));
  Serial.begin(9600);
}

void loop() {
if (Serial.available() > 0){
     delay(20);
     int data;
     while(Serial.available() > 0){
        data = Serial.read();
        if(!start){
          if(data == 'I'){
            Serial.println('0'); 
          }
          if(data == 'S'){
            start = true;
            Serial.println('O');
          }else if(data == 'C'){
            digitalWrite(bppEnablePin, LOW);
            moveToLocation(1, 5);
            cancel();
            break;
          }else if(data == 'B'){
            Serial.println('O');
            digitalWrite(bppEnablePin, LOW);
            cancel();  
          }
        }else{
          if(data == 'L'){
           digitalWrite(bppDirPin, LOW);
           digitalWrite(bppEnablePin, HIGH);
           Serial.println('O');
           cancel();
          }
          if(data == 'R'){
           digitalWrite(bppDirPin, HIGH);
            digitalWrite(bppEnablePin, HIGH);
            Serial.println('O');
            cancel();
          }
          if(data == 'X' || data == 'Y'){
            curVal = data;
          }else if(data == 'P'){
            Serial.println('O');
            pak();
            cancel();
            break;
          }else if(curVal != '0'){
            if(data == ';'){
              curVal = '0';
            }else{
               if(curVal == 'X'){
                 getx = data - 48;
                 if(getx >= 0 && getx <= 9){
                  hasx = true;
                   Serial.print("O ");
                   Serial.println(getx);
                 } 
               }
               if(curVal == 'Y'){
                 gety = data - 48;
                 if(gety >= 0 && gety <= 9){
                  hasy = true;
                  Serial.print("O ");
                  Serial.println(gety);
                 }
               }
               if(hasx && hasy){
                moveToLocation(getx, gety);
                cancel();
                break;
               }
             }
           }
         }
       }
     
   }
}

void cancel(){
  x = 0;
  y = 0;
  hasx = false;
  hasy = false;
  start = false; 
  Serial.println('C');
  while(Serial.available() > 0){
   Serial.read();
  } 
}

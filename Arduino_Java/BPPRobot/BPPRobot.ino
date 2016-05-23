boolean left = 0;
boolean start = 0;
const int MotorDir = 4;
const int MotorSpeed = 5;


void setup(){
  pinMode(MotorDir, OUTPUT);
  pinMode(MotorSpeed, OUTPUT);
  Serial.begin(9600);
}

void loop() {
  if (Serial.available() > 0){
     delay(20);
     int data;
     while(Serial.available() > 0){
        data = Serial.read();
        if(!start){
          if(data == 'S'){
            start = true;
            Serial.println('O');
          }else if(data == 'C'){
             Serial.println('C');
             analogWrite(MotorSpeed, 0);
             cancel();
          }
        }else{
          if(data == 'L'){
            Serial.println('O');
            moveLeft();
            cancel();
          }else if(data == 'R'){
            Serial.println('O');
            moveRight();
            cancel();
          }           
         }
       }
     
   }
}

void moveLeft(){
  digitalWrite(MotorDir, HIGH);
  analogWrite(MotorSpeed, 255);
}

void moveRight(){
  digitalWrite(MotorDir, LOW);
  analogWrite(MotorSpeed, 255);
}

void cancel(){
  start = false; 
  Serial.println('C');
  while(Serial.available() > 0){
   Serial.read();
  } 
}

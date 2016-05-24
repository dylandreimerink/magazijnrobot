boolean left = 0;
boolean start = 0;
const int MotorDir = 4;
const int MotorSpeed = 5;
const int EnablePin = 8;
const int DirPin = 9;

void setup(){
  pinMode(MotorDir, OUTPUT);
  pinMode(MotorSpeed, OUTPUT);
  pinMode(EnablePin, INPUT);
  pinMode(DirPin, INPUT);
}

void loop() {
  if(digitalRead(EnablePin) == HIGH){
    if(start == 0){
      analogWrite(MotorSpeed, 255);
      start = 1;
    }
  }else{
    if(start == 1){
      analogWrite(MotorSpeed, 0);
      start = 0;
    }
  }  
  if(digitalRead(DirPin) == HIGH){
    if(left == 0){
      digitalWrite(MotorDir, HIGH);
      left = 1;
    }
  }else{
    if(left == 1){
      digitalWrite(MotorDir, LOW);
      left = 0;
    }
  }  
}

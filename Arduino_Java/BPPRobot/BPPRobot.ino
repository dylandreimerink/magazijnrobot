boolean left = 0;


void setup(){
  
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
          }
        }else{
          if(data == 'L'){
            Serial.println('O');
            //TODO moveLeft();
            cancel();
          }else if(data == 'R'){
            Serial.println('O');
            //TODO moveRight()
            cancel();
          }           
         }
       }
     
   }
}

void moveLeft(){
  
}

void moveRight(){
  
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

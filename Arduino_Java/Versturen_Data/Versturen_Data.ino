//Rij y=1
int led1 = 1;
int led2 = 2;
int led3 = 3;

//Rij y=2
int led8 = 8;
int led9 = 9;
int led10 = 10; 


void setup() {
  pinMode(led1, OUTPUT);
  pinMode(led2, OUTPUT);
  pinMode(led3, OUTPUT);
  Serial.begin(9600);  
}

void loop() {
//  digitalWrite(led1, HIGH);
  int x = 0;
  int y = 0;
  int z = 0;
  bool hasx = false;
  bool hasy = false;
  bool hasz = false;
  char curVal = '0';
  if (Serial.available() > 0){
    delay(20);
     int data;
     while(Serial.available() > 0){
        data = Serial.read();
        if(data < 128){
          Serial.print("I received: ");
          Serial.println(data, DEC);
          if(data == 'X' || data == 'Y' || data == 'Z'){
            curVal = data;
          }else if(curVal != '0'){
            if(data == ';'){
              curVal = '0';
            }else{
              if(curVal == 'X'){
                x = data;
                hasx = true;
              }
              if(curVal == 'Y'){
                y = data;
                hasy = true;
              }
              if(curVal == 'Z'){
                z = data;
                hasz = true;
              }
              if(hasx && hasy && hasz){
                break;
              }
            }
          }
        }
     }
     Serial.println("OK");

    while(1<x){
       
    }
    if(x == '1' && y == '1'){
      digitalWrite(led1, HIGH);           
    }else{
      digitalWrite(led1, LOW);
    }

    for(int i=0; i<=x; i++){
      if(i == 1){
        digitalWrite(led1, HIGH);
        digitalWrite(led2, LOW);
        digitalWrite(led3, LOW);
        digitalWrite(led8, LOW);
        digitalWrite(led9, LOW);
        digitalWrite(led10, LOW);
        delay(500);        
      }
      if(i == 2){
        digitalWrite(led1, LOW);
        digitalWrite(led2, HIGH);
        digitalWrite(led3, LOW);
        digitalWrite(led8, LOW);
        digitalWrite(led9, LOW);
        digitalWrite(led10, LOW);
        delay(500);         
      }
      if(i == 3){
        digitalWrite(led1, LOW);
        digitalWrite(led2, LOW);
        digitalWrite(led3, HIGH);
        digitalWrite(led8, LOW);
        digitalWrite(led9, LOW);
        digitalWrite(led10, LOW);
        delay(500);         
      }      
    }

    for(int i=0; i<=y; i++){
      if(i == 1){
        digitalWrite(led1, LOW);
        digitalWrite(led2, LOW);
        digitalWrite(led3, LOW);
        digitalWrite(led8, HIGH);
        digitalWrite(led9, LOW);
        digitalWrite(led10, LOW);
        delay(500);        
      }
      if(i == 2){
        digitalWrite(led1, LOW);
        digitalWrite(led2, LOW);
        digitalWrite(led3, LOW);
        digitalWrite(led8, LOW);
        digitalWrite(led9, HIGH);
        digitalWrite(led10, LOW);
        delay(500);         
      }
      if(i == 3){
        digitalWrite(led1, LOW);
        digitalWrite(led2, LOW);
        digitalWrite(led3, LOW);
        digitalWrite(led8, LOW);
        digitalWrite(led9, LOW);
        digitalWrite(led10, HIGH);
        delay(500);         
      }      
    }    
    
//
//    if(z=='1'){
//     // digitalWrite(led1, LOW);
//    //  digitalWrite(led2, LOW);
//      digitalWrite(led3, HIGH);      
//    }else{
//      digitalWrite(led3, LOW);
//    }
  delay(200);
  while(Serial.available() > 0)
   Serial.read();
  }
}

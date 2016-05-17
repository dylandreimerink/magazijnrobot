int led1 = 12;
int led2 = 11;
int led3 = 10;


void setup() {
  pinMode(led1, OUTPUT);
  pinMode(led2, OUTPUT);
  pinMode(led3, OUTPUT);
  Serial.begin(9600);  
}

void loop() {
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
    if(x=='1'){
      digitalWrite(led1, HIGH);
      //digitalWrite(led2, LOW);
     //digitalWrite(led3, LOW);            
    }else{
      digitalWrite(led1, LOW);
    }

    if(y=='1'){
      //digitalWrite(led1, LOW);
      digitalWrite(led2, HIGH);
     // digitalWrite(led3, LOW);      
    }else{
      digitalWrite(led2, LOW);
    }

    if(z=='1'){
     // digitalWrite(led1, LOW);
    //  digitalWrite(led2, LOW);
      digitalWrite(led3, HIGH);      
    }else{
      digitalWrite(led3, LOW);
    }
  delay(200);
  while(Serial.available() > 0)
   Serial.read();
  }
}

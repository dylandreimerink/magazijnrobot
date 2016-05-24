const int bppEnablePin = 8;
const int bppDirPin = 9;

int x = 0;
int y = 0;
bool hasx = false;
bool hasy = false;
bool start = false;
char curVal = '0';

void setup() {
  pinMode(bppEnablePin, OUTPUT);
  pinMode(bppDirPin, OUTPUT);
  digitalWrite(bppEnablePin, LOW);
  digitalWrite(bppDirPin, LOW);
  
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
            //TODO cancel code
            cancel();
            break;
          }else if(data == 'B'){
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
            //TODO: pick
            cancel();
            break;
          }else if(curVal != '0'){
            if(data == ';'){
              curVal = '0';
            }else{
               if(curVal == 'X'){
                 x = data;
                 hasx = true;
                 Serial.println('O');
               }
               if(curVal == 'Y'){
                 y = data;
                 hasy = true;
                 Serial.println('O');
               }
               if(hasx && hasy){
                //TODO: move
                delay(2000);
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

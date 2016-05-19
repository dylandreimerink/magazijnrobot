
int X = 0;
int Y = 0;


void setup() {
Serial.begin(9600);

//Motoren
pinMode(1, OUTPUT); //Motor X
pinMode(2, OUTPUT); //Motor Y

//Sensoren
pinMode(SENSOR_X, INPUT);
pinMode(SENSOR_Y, INPUT);

}

void loop() {
  // put your main code here, to run repeatedly:

}

boolean ReadSensor_X(){
  Serial.println(analogRead(SENSOR_X));
  if(analogRead(SENSOR_X) < SensorIsZwart){
    return(true);
  }
  else{
    return(false);
  }
}

boolean ReadSensor_Y(){
  Serial.println(analogRead(SENSOR_Y));
  if(analogRead(SENSOR_Y) < SensorIsZwart){
     return(true);
  }
  else{
    return(false);
  }
}

void DropZone(int positie_X, int positie_Y){ //DropZone voor paketten is X=0, Y=0
  X = positie_X; //Robot krijgt de huidige positie X mee
  Y = positie_Y; //Robot krijgt de huidige positie Y mee
  while(X > 0){ // Robot rijdt naar links totdat de positie op X=1 staat
     RijLinks(positie_X);  
  }
  while(Y > 0){ // Robot rijdt naar beneden totdat de positie op Y=1 staat
    NaarBeneden();
  }
}

void RijLinks(int positie_X){
  digitalWrite(1, LOW); //Robot moet naar links rijden
  boolean Positie_X_Gevonden = false; //Robot zoekt naar de nieuwe X positie
  while(Positie_X_Gevonden != true){
    Serial.println("Ik zoek de volgende X positie");
    if(ReadSensor_X()){ //Leest sensor X
      Serial.println("Positie X gevonden");
      X = positie_X - 1; // Als positie is gevonden wordt de huidige X positie gewijzigd
    }
    else{
      Serial.println("Positie X zoeken...");
    }
  }
}

void NaarBeneden(int positie_Y){
  digitalWrite(2, LOW); //Robot moet naar beneden rijden
  boolean Positie_Y_Gevonden = false; //Robot zoekt naar de nieuwe Y positie
  while(positie_Y_Gevonden !=){
    Serial.println("Ik zoek de volgende Y positie");
    if(ReadSensor_Y()){ //Leest sensor Y
      Serial.println("Positie Y gevonden");
      Y = positie_Y - 1; // Als positie is gevonden wordt de huidige Y positie gewijzigd
    }
    else{
      Serial.println("Positie Y zoeken...");
    }
  }
}


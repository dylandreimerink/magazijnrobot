int x;
int y;

void setup() {
  Serial.begin(9600);
}

void loop() {
    if (Serial.available() > 0){
    char inputData = Serial.read();
    x = inputData;
    Serial.write("Arduino data X: " + x);
  }
}

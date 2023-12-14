const int trigPin = 2;
const int echoPin = 3;
const int buz = 10;
const int c = 4;

void setup() {
  pinMode(c, INPUT_PULLUP);
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);
  pinMode(5, OUTPUT);
  Serial.begin(9500);
}

void loop() {
  int c_in = digitalRead(c);
  Serial.println(c_in);

  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
  
  int distance = pulseIn(echoPin, HIGH) * 17 / 1000;
  
  Serial.println(distance);
  
  // if (distance > 60 && distance < 100) {
  //   tone(buz, 2093, 800);
  //   delay(1000);
  // }
  // else if (distance <= 60 && distance > 30) {
  //   tone(buz, 2093, 800);
  //   delay(700);
  // }
  // else if (distance < 30 && distance > 10) {
  //   tone (buz, 2093, 800);
  //   delay(300);
  // }
  
  if (digitalRead(4) == HIGH) {
    if (distance > 5 && distance < 20) {
    tone(buz, 262, 1000);
    delay(400);

    tone(buz, 330, 1000);
    delay(400);

    tone(buz, 392, 1000);
    delay(400);

    tone(buz, 523, 1000);
    delay(400);

    // noTone(10);
    }
  
  // else {
  //   tone(buz, 2093, 800);
  //   delay(1500);
  //  }
  }
  

}
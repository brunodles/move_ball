#include <LiquidCrystal.h>

// initialize the library with the numbers of the interface pins
LiquidCrystal lcd(12, 11, 5, 4, 3, 2);
  
  int pinX = A0;
  int pinY = A1;
  int pinSw = 13;

  int x = 0;
  int y = 0;
  int sw = 0;
  
void setup() {
  lcd.begin(16, 2);
  Serial.begin(57600);
  


}

void loop() {
  lcd.clear();
  Principal(); 
  transicao();
  msgAlerta("fimDeJogo");
  
//    x = analogRead(pinX);
//    y = analogRead(pinY);
//    sw = digitalRead(pinSw);
//    Serial.println(sw);

  
} //Fim do loop

  void Principal(){
  lcd.setCursor(1,0);
  lcd.print("Score");
  lcd.setCursor(10,0);
  lcd.print("Timer");
  

      Serial.println(x);
  
   for(int i=1; i<=60;i++){    
    setScore(i*52);
    setTimer(i);
    x = analogRead(pinX);
    delay(x); 
  }
     
  }
 
  void setScore(int scoreAndroid){
     int Score = scoreAndroid;
  if(Score>=0 && Score <10){
    lcd.setCursor(1,1);
    lcd.print("0000");
    lcd.setCursor(5,1);
    lcd.print(Score);
  }else
    if(Score>=10 && Score<100){
    lcd.setCursor(1,1);
    lcd.print("000");
    lcd.setCursor(4,1);
    lcd.print(Score);
  }else
    if(Score>=100 && Score<1000){
    lcd.setCursor(1,1);
    lcd.print("00");
    lcd.setCursor(3,1);
    lcd.print(Score);
  }else
    if(Score>=1000 && Score<10000){
    lcd.setCursor(1,1);
    lcd.print("0");
    lcd.setCursor(2,1);
    lcd.print(Score);
  }else
    if(Score>=10000 && Score<=99999){
    lcd.setCursor(1,1);
    lcd.print(Score);
    }  
  } //Fim setScore
  
  void setTimer(int timerAndroid){
    int Timer = timerAndroid;
  if(Timer<10){
    lcd.setCursor(11,1);
    lcd.print(0);
    lcd.setCursor(12,1);
    lcd.print(Timer); 
    }else{
      lcd.setCursor(11,1);
      lcd.print(Timer);
      }
  
  lcd.setCursor(13,1);
  lcd.print("s");
  }// Fim setTime
  
  void transicao(){  
   for (int i=0; i<=16; i++){
	for (int j=0; j<=1;j++){
	lcd.setCursor(i,j);
	lcd.print("*"); 
        delay(75);
	}
 delay(5);
    }
    lcd.clear();
   
  } //fim transicao
  
  
  void msgAlerta(String msgAndroid){
    if (msgAndroid=="levelUp"){      
       efeitoDuasLinhas("Passou de Nivel!");
        lcd.clear(); 
    }else
     if (msgAndroid=="fimDeJogo"){
      efeitoDuasLinhas("Fim de Jogo!"); 
       }
       
    delay(1500);
    lcd.clear();
   }
 
  void efeitoDuasLinhas(String valor){
  for (int i=0; i<=4; i++){
	for (int j=0; j<=1;j++){
	lcd.setCursor(0,j);
	lcd.print(valor); 
        delay(400);
        lcd.clear(); 
	}
     }
  } //fim efeitoDuasLinhas
  
  
  
#include <LiquidCrystal.h>

// initialize the library with the numbers of the interface pins
LiquidCrystal lcd(12, 11, 5, 4, 3, 2);

void setup() {
  // set up the LCD's number of columns and rows: 
  lcd.begin(16, 2);
  //int linha1 = 0;
  int linha2 = 0;

}

void loop() {
  
  Principal();
  
  for(int i=1; i<60;i++){
  setScore(i*52);
  setTimer(i);
  delay(200); 
  }
  
  lcd.clear();
  
}

  void Principal(){
  lcd.setCursor(1,0);
  lcd.print("Score");
  lcd.setCursor(10,0);
  lcd.print("Timer");
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

  }
  
  void setTimer(int timerAndroid){
  int Timer = timerAndroid;
  
  if(Timer<10){
    lcd.setCursor(11,1);
    lcd.print(0);
    lcd.setCursor(12,1);
    lcd.print(Timer); 
    } else{
      lcd.setCursor(11,1);
      lcd.print(Timer);
      }
  
  lcd.setCursor(13,1);
  lcd.print("s");
  }

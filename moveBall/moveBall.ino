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
  msgAlerta("Msg Android!");
  
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
     String score = String(scoreAndroid);
     lcd.setCursor(1,1);
     int tamanho = score.length();
     
     String texto = "";     
     while (tamanho < 5 ){
       texto+="0";
       tamanho++;
     }       
   
    texto+=score;           
    lcd.print(texto);
       
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
    int tamanho = msgAndroid.length();
    String texto = "";
    int spaco = (16-tamanho)/2;
    
    for (int i=0; i<spaco;i++){
    texto += " ";
      
    Serial.println(spaco);
    }
    
    efeitoDuasLinhas(texto+=msgAndroid);
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
  
  
  

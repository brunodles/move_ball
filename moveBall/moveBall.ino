#include <LiquidCrystal.h>
#include <MeetAndroid.h>

MeetAndroid meetAndroid;
// initialize the library with the numbers of the interface pins
LiquidCrystal lcd(12, 11, 5, 4, 3, 2);
  
  int pinX = A0;
  int pinY = A1;
  
//  int pinSw = 13;

  int x = 0;
  int y = 0;
  
  int r= 0;
//  int sw = 0;
  
  
void setup() {
  lcd.begin(16, 2);
  Serial.begin(9600);
  
  meetAndroid.registerFunction(getMsgAndroid, 'G'); // a string
  meetAndroid.registerFunction(recebeScore, 'S'); // a string
  meetAndroid.registerFunction(recebeTime, 'T'); // a string
  
}

void loop() {
//  lcd.clear();
 
  Principal(); 
  meetAndroid.receive();
  mandarXY();
  delay(5);
//    sw = digitalRead(pinSw);
  
} //Fim do loop

void mandarXY(){
    x = analogRead(pinX);
    y = analogRead(pinY);
//  String p = String(x);
//  meetAndroid.send(p);


  char buffer[15];
  sprintf(buffer,"%d,%d",x,y);
  meetAndroid.send(buffer);



//  int length = meetAndroid.stringLength();
  
  // define an array with the appropriate size which will store the string
//  char data[length];
  
  // tell MeetAndroid to put the string into your prepared array
//  meetAndroid.getString(data);
  
  // go and do something with the string, here we simply send it back to Android
//  meetAndroid.send(data);
  
//  for (int i=0; i<length-1; i++)
//  {
//        meetAndroid.send(data[i]);
//  }
}

  void getMsgAndroid(byte flag, byte numOfValues)
  {
    int length = meetAndroid.stringLength();
    char data[length];
    meetAndroid.getString(data);
    String messagem = "";  
  
    for (int i=0; i<length-1; i++){
        messagem+=data[i];     
    }
    msgAlerta(messagem);
  }

  void recebeScore(byte flag, byte numOfValues){
  int score = meetAndroid.getInt();
  setScore(score);
  }

  void recebeTime(byte flag, byte numOfValues){
  int time = meetAndroid.getInt();
  setTimer(time);
  }

  void Principal(){
  lcd.setCursor(1,0);
  lcd.print("Score");
  lcd.setCursor(10,0);
  lcd.print("Timer");          
  } //Fim Principal
 
  void setScore(int scoreAndroid){
     String score = String(scoreAndroid);
     lcd.setCursor(1,1);
     int tamanho = score.length();
     
     String texto = "";     
     while (tamanho < 5 ){
       texto+=" ";
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
    lcd.clear();
    int tamanho = msgAndroid.length();
    String texto = "";
    int spaco = (16-tamanho)/2;
    
    for (int i=0; i<spaco;i++){
    texto += " ";
      
    Serial.println(spaco);
    }
    
    efeitoDuasLinhas(texto+=msgAndroid);
    delay(200);
    lcd.clear();
   } //fim msgAlenta
 
  void efeitoDuasLinhas(String valor){
  for (int i=0; i<=2; i++){
        for (int j=0; j<=1;j++){
        lcd.setCursor(0,j);
        lcd.print(valor); 
        delay(300);
        lcd.clear(); 
        }
     }
  } //fim efeitoDuasLinhas
  

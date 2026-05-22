
class einarmigerbandit extends Actor {
   int baseluckGlocke;
   int baseluckKirsche;
   int baseluckDiamant;
   int baseluckSieben;
   int baseluckC18H24O2;
   int baseluckC60;
   boolean amlaufen = false;
   int anzahl3;
   float geld = 69;
   int BuffVonWeinflasche = 30;
   int BuffVonGewonnen = 100;
   int Spins = 0;
   int jackpotGewinnFurKirsche = 15;
   int jackpotGewinnFurGlocke = 28;
   int jackpotGewinnFurDiamant = 42;
   int jackpotGewinnFurSieben = 77;
   int Krater_random;
   int Krater_verloren_random;
   int rundenGeld;
   boolean startsound = true;
   boolean jackbotErreicht = false;
   String kontostandtext = "Kontostand: " + "£" + geld;
   boolean UpdateText = false;
   boolean UpdateSpins = false;
   boolean kraterEingeschlagen = false;
   boolean kraterEingeschlagen2 = false;
   boolean kraterEingeschlagen3 = false;
   int zeroKelvinSlot = 0;
   boolean ZeroKelvinVerwendet = false;
   boolean ZeroKelvinVerwendet2 = false;
   String bereich;
   int anzahlDerGewinne = 0;
   boolean DyatlowCarried = false;
   boolean ZeroKelvinBereit = false;

   ArrayList<String> glucksbringer = new ArrayList<>();
   String glucksbringerZurVerfugung[] = {
      "Weinfalsche",
      "Gewonnen!",
      "Dyatlow",
      "0 Kelvin"
   };

   void glucksbringerAdden(int i) {
      if (i < 0 || glucksbringerZurVerfugung.length < i) { logString("Falscher Index bei Array", 1); return; }
      glucksbringer.add(glucksbringerZurVerfugung[i]);
   }

   boolean glucksbringerChecker() {
      if (glucksbringer.isEmpty()) {
         // logString("Keine Glücksbringer zur Verfügung", 1);
         return false;
      }

      return true;
   }

  /*  void mover(int Case) {
      if (amlaufen) return;
   }

   void onKeyDown(String key) {
      if (key.equals("ArrowLeft"))mover(1); ;
      if (key.equals("ArrowUp")) mover(2);
   } */

   einarmigerbandit() {
      baseluckGlocke = 25;
      baseluckKirsche = 35;
      baseluckDiamant = 20;
      baseluckSieben = 15;
      geld = 69;
      bereich = "Automat";
   }

   void glucksbringerunddannspielen() {
      if (amlaufen) return;
      if (!glucksbringerChecker()) {
         spielen(0, 0, 0, 0, false);
         return;
      } else {
         int WertFürGlocke = 0;
         int WertFürKirsche = 0;
         int WertFürDiamant = 0;
         int WertFürSieben = 0;

         boolean kelvindrinnen = false;

         for (int i = 0; i < glucksbringer.size(); i++) {
            String current = glucksbringer.get(i);
            if (current.equals(glucksbringerZurVerfugung[0])) {
               WertFürKirsche = WertFürKirsche + BuffVonWeinflasche;
            } else if (current.equals(glucksbringerZurVerfugung[1])) {
               
               Krater_random = (int)(Math.random() * 6);
               Krater_verloren_random = (int)(Math.random() * 2);
               
               
               if (!kraterEingeschlagen2) {
                  if (kraterEingeschlagen) {
                  
                     if (Krater_verloren_random == 1) {
                        geld = geld - rundenGeld;
                        rundenGeld = 0;
                        kraterEingeschlagen2 = true;
                        UpdateText = true;
                        println("[DEBUG] Krater hat doppelt eingeschlagen");
                        Spins = 0;
                     } else {
                        println("[DEBUG] Krater nicht nochmal eing");
                        kraterEingeschlagen2 = false;
                     }
                     
                  }else {

                     if (Spins > 1) {
                        if (Krater_random >= 4) {
                           WertFürSieben = WertFürSieben + BuffVonGewonnen;
                           kraterEingeschlagen = true;
                           kraterEingeschlagen3 = true;
                        }
                     }else {
                        WertFürSieben = WertFürSieben + BuffVonGewonnen;
                     }

                  }
               }
            } else if (current.equals(glucksbringerZurVerfugung[2])) {
               if (Spins <= 1 && (anzahlDerGewinne <= 2 || kraterEingeschlagen2)) {
                  DyatlowCarried = true;
               }
            } else if (current.equals(glucksbringerZurVerfugung[3])) {
               kelvindrinnen = true;
            }
         }
         spielen(WertFürGlocke, WertFürKirsche, WertFürDiamant, WertFürSieben, kelvindrinnen);
      }
   }


   void LostSound() {
      Sound.playSound(Sound.pong_f); Thread.sleep(150);
      Sound.playSound(Sound.pong_d); Thread.sleep(250);
      Sound.playSound(Sound.pong_d); Thread.sleep(400);
   }

   void WonSound() {
      Sound.playSound(Sound.pong_d); Thread.sleep(150);
      Sound.playSound(Sound.pong_d); Thread.sleep(250);
      Sound.playSound(Sound.pong_f); Thread.sleep(400);
   }

   String letztesSymbol1 = "";
   String letztesSymbol2 = "";
   String letztesSymbol3 = "";
   void spielen(int glucksbringerWertGlocke, int glucksbringerWertKirsche, int glucksbringerWertDiamant, int glucksbringerWertSieben, boolean kelvindrinnen) { 
      if (amlaufen) return;
      int r = (int)(Math.random() * 4);
      int r2 = (int)(Math.random() * 100);
      Thread.sleep(250);
      ArrayList<Integer> slot = new ArrayList<>();  // 1 = Glocke; 2 = Kirsche; 3 = Diamant; 4 = Sieben; 5 = int Overflow
      ArrayList<String> symbole = new ArrayList<>();
      amlaufen = true;

      // Glücksbringer

      int Lsb = 0; // Luck für symbol: Sieben  || 4
      int Lgl = 0; // Luck für symbol: Glocke  || 1
      int Lkr = 0; // Luck für symbol: Kirsche || 2
      int Ldi = 0; // Luck für symbol: Diamant || 3

      Lsb = baseluckSieben + glucksbringerWertSieben;
      Lgl = baseluckGlocke + glucksbringerWertGlocke;
      Lkr = baseluckKirsche + glucksbringerWertKirsche;
      Ldi = baseluckDiamant + glucksbringerWertDiamant;
      
      println(Lsb + " " + Lgl + " " + Lkr + " " + Ldi);

      // Wenn eine Sache hundert ist = alles andere nicht möglich
      if (Lgl >= 100) {
         Lsb = 0; // Luck für symbol: Sieben  || 4
         Lgl = 100; // Luck für symbol: Glocke|| 1
         Lkr = 0; // Luck für symbol: Kirsche || 2
         Ldi = 0; // Luck für symbol: Diamant || 3
      } else if (Lkr >= 100) {
         Lsb = 0; // Luck für symbol: Sieben  || 4
         Lgl = 0; // Luck für symbol: Glocke  || 1
         Lkr = 100; //Luck für symbol: Kirsche|| 2
         Ldi = 0; // Luck für symbol: Diamant || 3
      } else if (Ldi >= 100) {
         Lsb = 0; // Luck für symbol: Sieben  || 4
         Lgl = 0; // Luck für symbol: Glocke  || 1
         Lkr = 0; // Luck für symbol: Kirsche || 2
         Ldi = 100; // Luck für symbol:Diamant|| 3
      } else if (Lsb >= 100) {
         Lsb = 100; // Luck für symbol: Sieben|| 4
         Lgl = 0; // Luck für symbol: Glocke  || 1
         Lkr = 0; // Luck für symbol: Kirsche || 2
         Ldi = 0; // Luck für symbol:Diamant  || 3
      }
      

      // Nach Glücksbringer auswerten
   
      int anzahl = 0;
      int random;

      // Glocke Pushen //
      for (int i = 1; i <= Lgl; i++) {
         slot.add(1);
         // logInt(i, 4);
         anzahl++;
      }
      // Kirsche Pushen
      for (int i = 1; i <= Lkr; i++) {
         slot.add(2);
         // logInt(i, 4);
         anzahl++;
      }
      // Diamanten Pushen
      for (int i = 1; i <= Ldi; i++) {
         slot.add(3);
         // logInt(i, 4);
         anzahl++;
      }
      // Sieben Pushen
      for (int i = 1; i <= Lsb; i++) {
         slot.add(4);
         // logInt(i, 4);
         anzahl++;
      }
      
      // System.out.println(slot);
      
      String symbol1 = "";
      String symbol2 = "";
      String symbol3 = "";

      for (int i = 1; i <= 3; i++) {
         
         random = (int)(Math.random() * anzahl); // Nach Size des Arrays + 1 rollen
         int sl = slot.get(random);
         if (sl == 1) {
            // logString("Symbol: Glocke", 3);
            symbole.add("Glocke");
         } else if (sl == 2) {
            // logString("Symbol: Kirsche", 3);
            symbole.add("Kirsche");
         } else if (sl == 3) {
            // logString("Symbol: Diamant", 3);
            symbole.add("Diamant");
         }
         else if (sl == 4) {
            // logString("Symbol: Sieben", 3);
            symbole.add("Sieben");
         } else {
            logString("Array Fehler, sl ist nicht 1,2,3 oder 4! sl wird in kürze geloggt", 1);
            logInt(sl, 1);
            amlaufen = false;
            return;
         }

         anzahl3++;
         // println(anzahl3);

         if (anzahl3 == 3) {
            
            for (int i = 1; i <= 3; i++) {
               if (i == 1) {
                  symbol1 = symbole.get(0);
               } else if (i == 2) {
                  symbol2 = symbole.get(1);
               } else if (i == 3) {
                  symbol3 = symbole.get(2);
               }
               
            }

            if (kelvindrinnen && Spins > 1 && Spins < 7) {
               if (ZeroKelvinBereit) { 
                  if (r == 1 && letztesSymbol1.length() > 0) {
                     symbol1 = letztesSymbol1;
                     zeroKelvinSlot = 1;
                  } else if (r == 2 && letztesSymbol2.length() > 0) {
                     symbol2 = letztesSymbol2;
                     zeroKelvinSlot = 2;
                  } else if (r == 3 && letztesSymbol3.length() > 0) {
                     symbol3 = letztesSymbol3;
                     zeroKelvinSlot = 3;
                  }
                  ZeroKelvinBereit = false;
                  ZeroKelvinVerwendet = true;
                  println(symbol1 + symbol2 + symbol3 + letztesSymbol1 + letztesSymbol2 + letztesSymbol3);
               } else {
                  println(ZeroKelvinVerwendet + " " + r2 + " " + r);
                  if (r2 <= 15 && !ZeroKelvinVerwendet) { 
                     ZeroKelvinBereit = true;
                     println(symbol1 + symbol2 + symbol3 + letztesSymbol1 + letztesSymbol2 + letztesSymbol3);
                  }
               }
            }
            

            if (symbol1 == symbol2 && symbol1 == symbol3) {
               logString("Gewonnen!", 3);
               println(symbol1 + symbol2 + symbol3);
               if (symbol1 == "Kirsche") {
                  JackpotAdden(2);
               } else if (symbol1 == "Glocke") {
                  JackpotAdden(1);
               } else if (symbol1 == "Diamant") {
                  JackpotAdden(3);
               } else if (symbol1 == "Sieben") {
                  JackpotAdden(4);
               }

            } else {
               logString("Verloren!", 3);
               println(symbol1 + symbol2 + symbol3);
               LostSound();
            }
         }
      }
      Spins--;
      letztesSymbol1 = symbol1;
      letztesSymbol2 = symbol2;
      letztesSymbol3 = symbol3;
      UpdateSpins = true;
      Thread.sleep(250);
      amlaufen = false;
      anzahl3 = 0;
   }

   LocalDateTime datum() {
      return LocalDateTime.now();
   }

   void JackpotAdden(int s) {
      if (s == 1) {
         geld = geld + jackpotGewinnFurGlocke;
         rundenGeld = rundenGeld + jackpotGewinnFurGlocke;
         WonSound();
      } else if (s == 2) {
         geld = geld + jackpotGewinnFurKirsche;
         rundenGeld = rundenGeld + jackpotGewinnFurKirsche;
         WonSound();
      } else if (s == 3) {
         geld = geld + jackpotGewinnFurDiamant;
         rundenGeld = rundenGeld + jackpotGewinnFurDiamant;
         jackbotErreicht = true;
      } else if (s == 4) {
         geld = geld + jackpotGewinnFurSieben;
         rundenGeld = rundenGeld + jackpotGewinnFurSieben;
         jackbotErreicht = true;
      }
      anzahlDerGewinne++;
      UpdateText = true;
   }

   void rundenReset() {
      kraterEingeschlagen = false;
      kraterEingeschlagen2 = false;
      rundenGeld = 0;
      Thread.sleep(100);
   }


   void logString(String msg, int type) {
      if (type == 1) {
         System.out.println(datum() + " [ERROR]: " + msg);
      } else if (type == 2) {
         System.out.println(datum() + " [WARNUNG]: " + msg);
      } else if (type == 3) {
         System.out.println(datum() + " [INFO]: " + msg);
      } else {
         System.out.println(datum() + " [DEBUG]: " + msg);
      }
   }


   void logInt(int msg, int type) {
      if (type == 1) {
         System.out.println(datum() + " [ERROR]: " + msg);
      } else if (type == 2) {
         System.out.println(datum() + " [WARNUNG]: " + msg);
      } else if (type == 3) {
         System.out.println(datum() + " [INFO]: " + msg);
      } else {
         System.out.println(datum() + " [DEBUG]: " + msg);
      }
   }

   void logIntArray(int[] msg, int type) {
      if (type == 1) {
         System.out.println(datum() + " [ERROR]: " + msg);
      } else if (type == 2) {
         System.out.println(datum() + " [WARNUNG]: " + msg);
      } else if (type == 3) {
         System.out.println(datum() + " [INFO]: " + msg);
      } else {
         System.out.println(datum() + " [DEBUG]: " + msg);
      }
   }

   void logStringArray(String[] msg, int type) {
      if (type == 1) {
         System.out.println(datum() + " [ERROR]: " + msg);
      } else if (type == 2) {
         System.out.println(datum() + " [WARNUNG]: " + msg);
      } else if (type == 3) {
         System.out.println(datum() + " [INFO]: " + msg);
      } else {
         System.out.println(datum() + " [DEBUG]: " + msg);
      }
   }

   boolean cooldwn2;
   void Spinskaufen() {
      if (!cooldwn2) {
         cooldwn2 = true;
         if (geld >= 7) {
            for (int i = 0; i <= 6; i++) {
               Spins++;
               geld--;
            }
         } else { logString("Game Over fah", 1); }
   
      


         Thread.sleep(1000);
         cooldwn2 = false;
      }
   }



}

// Background vom der Maschiene
RoundedRectangle background = new RoundedRectangle();
background.setWidth(500);
background.setHeight(300);
background.setX(400);// um so größer desto weiter links
background.setY(250);// um so größer desto weiter unten
background.setFillColor(0x272525); //background color chooser

//Slot 1
Rectangle Slot1 = new Rectangle();
Slot1.setBorderColor("black");
Slot1.setFillColor(0xffffff);
Slot1.setHeight(170);
Slot1.setWidth(120);
Slot1.setX(250);
Slot1.setY(250);

//Slot 2
Rectangle Slot2 = new Rectangle();
Slot2.setBorderColor("black");
Slot2.setFillColor(0xffffff);
Slot2.setHeight(170);
Slot2.setWidth(120);
Slot2.setX(400);
Slot2.setY(250);

//Slot 3
Rectangle Slot3 = new Rectangle();
Slot3.setBorderColor("black");
Slot3.setFillColor(0xffffff);
Slot3.setHeight(170);
Slot3.setWidth(120);
Slot3.setX(550);
Slot3.setY(250); 

//Text: SLOT
Text Headbar = new Text();
Headbar.setText("SLOT");
Headbar.setScale(2);
Headbar.setY(140);
Headbar.setX(400);
Headbar.setStyle(true, false);
Headbar.setFillColor(0xe02525);

//Text: Kontostand
Text k = new Text();
// Text: Spins verbleibend
Text s = new Text();
s.setText("Verbleibende Spins: 0");
s.setScale(1);
s.setY(535);
s.setX(400);
s.setStyle(true, false);
s.setFillColor(0xffffff);
// Text: INFO
Text z = new Text();
z.setText("INFO: Es werden automatisch Spins gekauft, wenn man auf Spin drückt und keine Spins mehr hat");
z.setScale(0.5);
z.setY(565);
z.setX(400);
z.setStyle(true, false);
z.setFillColor(0xffffff);

// Button
Button spin = new Button(355.0, 340.0, 30.0, "SPIN");
spin.setFillColor(0xffe23e);
spin.setBorderColor(0xffe23e);
spin.setTextColor(0x000000);

einarmigerbandit b = new einarmigerbandit();

void StartSound() {
   if (!b.startsound) return;

   Sound.playSound(Sound.pong_d); Thread.sleep(150);
   Sound.playSound(Sound.pong_d); Thread.sleep(150);

   Sound.playSound(Sound.pong_f); Thread.sleep(300);
   Sound.playSound(Sound.pong_d); Thread.sleep(200);
   Sound.playSound(Sound.pong_f); Thread.sleep(300);
   Sound.playSound(Sound.pong_f); Thread.sleep(500); 

   Sound.playSound(Sound.pong_d); Thread.sleep(200);
   Sound.playSound(Sound.pong_f); Thread.sleep(600); 

   b.startsound = false;
}

void BuySound() {
    // Kurzes positives Pling
   Sound.playSound(Sound.pong_d); Thread.sleep(150);
   Sound.playSound(Sound.pong_f); Thread.sleep(250);
   Sound.playSound(Sound.pong_f); Thread.sleep(400);
}

boolean cooldwn3;

void WonSound2() {
   int i = 0;
   while (i <= 50) {
      i++;
      Sound.playSound(Sound.pong_f);
      Thread.sleep(1);
      Sound.playSound(Sound.digging);
   }
   Sound.playSound(Sound.flamethrower);
}

void RGBehre() {
   if (cooldwn3) { return; }
   int i = 0;
   Headbar.setText("JACKPOT!");
   Headbar.setScale(2.5);
   Headbar.setY(130);
   Headbar.setX(400);
   while (i <= 50) {
      background.setFillColor(0x1eff00);
      Headbar.setFillColor(0x3300ff);
      Thread.sleep(1);
      background.setFillColor(0xff0000);
      Headbar.setFillColor(0x1eff00);
      Thread.sleep(1);
      background.setFillColor(0x3300ff);
      Headbar.setFillColor(0xff0000);
      Sound.playSound(Sound.pong_f);
      Sound.playSound(Sound.digging);
      i++;
   }
   Sound.playSound(Sound.flamethrower);
   background.setFillColor(0x1eff00);
   Headbar.setFillColor(0x1eff00);
   Thread.sleep(50);
   background.setFillColor(0x272525);
   Headbar.setFillColor(0xe02525);
   Headbar.setText("SLOT");
   Headbar.setScale(2);
   Headbar.setY(140);
   Headbar.setX(400);
   Thread.sleep(550);
   b.jackbotErreicht = false;
   cooldwn3 = false;
}


k.setText(b.kontostandtext);
k.setScale(1);
k.setY(500);
k.setX(400);
k.setStyle(true, false);
k.setFillColor(0xffffff);


boolean cooldwn4 = false; 
void updateText() {
   if (cooldwn4) { return; }
   cooldwn4 = true;
   b.kontostandtext = "Kontostand: " + "£" + b.geld;
   k.setText(b.kontostandtext);
   k.setScale(1);
   k.setY(500);
   k.setX(400);
   Thread.sleep(10);
   b.UpdateText = false;
   cooldwn4 = false;
}

boolean cooldwn5 = false;
void updateSpins() {
   if (cooldwn5) { return; }
   cooldwn5 = true;
   s.setText("Verbleibende Spins: " + b.Spins);
   s.setScale(1);
   s.setY(535);
   s.setX(400);
   Thread.sleep(10);
   b.UpdateSpins = false;
   cooldwn5 = false;
}



b.glucksbringerAdden(3);


void SagenSieEsNichtDochIchSageTrier_Sound() {
   int i = 0;
   while (i <= 10) {
      i++;
      Sound.playSound(Sound.pong_f);
      Thread.sleep(500);
      Sound.playSound(Sound.pong_d);
      Thread.sleep(500);
   }
    
}
boolean cooldwn6 = false;
void SagenSieEsNichtDochIchSageTrier_Animation() {
   if (cooldwn6) { return; }
   cooldwn6 = true;
   Headbar.setText("SO! DER RAUS, KRIEGT KEIN GELD");
   Headbar.setScale(1);
   Headbar.setY(140);
   Headbar.setX(400);
   Headbar.setFillColor(0xe02525);
   int i = 0;
   while (i <= 10) {
      i++;
      Sound.playSound(Sound.pong_f);
      background.setFillColor(0xe02525);
      Headbar.setFillColor(0x272525);
      Thread.sleep(400);
      Sound.playSound(Sound.pong_d);
      Headbar.setFillColor(0xe02525);
      background.setFillColor(0x272525);
      Thread.sleep(400);
   }
   background.setFillColor(0xe02525);
   Headbar.setFillColor(0x272525);
   Thread.sleep(1000);
   Headbar.setText("SLOT");
   Headbar.setScale(2);
   Headbar.setY(140);
   Headbar.setX(400);
   Headbar.setFillColor(0xe02525);
   background.setFillColor(0x272525);
   Thread.sleep(250);
   cooldwn6 = false;
}

// Sound für Glücksbringer eingesetzt:
/* 

   Sound.playSound(Sound.short_shoot);
   Sound.playSound(Sound.pong_f);
   int i = 0;
   while (i <= 3) { 
      i++;
      Thread.sleep(1); 
      Sound.playSound(Sound.digging);
   }

*/

void glucksbringerEingesetztAN(String g) {
   Headbar.setText(g + " wurde eingesetzt");
   Headbar.setScale(1);
   Headbar.setY(140);
   Headbar.setX(400);
   Headbar.setFillColor(0x272525);
   background.setFillColor(0xffb300);
   Sound.playSound(Sound.short_shoot);
   Sound.playSound(Sound.pong_f);
   int i = 0;
   while (i <= 3) { 
      i++;
      Thread.sleep(1);
      Sound.playSound(Sound.digging);
   }
   Thread.sleep(1500);
   Headbar.setFillColor(0xe02525);
   background.setFillColor(0x272525);
   Headbar.setText("SLOT");
   Headbar.setScale(2);
   Headbar.setY(140);
   Headbar.setX(400);
}

int letzterSpin = 0;

void zeroKelvinAnimation(int slot) {
   if (slot == 1) {
      Slot1.setBorderColor(0x1b6eff);
      Slot1.setFillColor(0x47e3ff);
   }
   else if (slot == 2) {
      Slot2.setBorderColor(0x1b6eff);
      Slot2.setFillColor(0x47e3ff);
   }
   else if (slot == 3) {
      Slot3.setBorderColor(0x1b6eff);
      Slot3.setFillColor(0x47e3ff);
   }
   int i = 0;
   while (i <= 2) {
      i++;
      Sound.playSound(Sound.laser_shoot);
      Thread.sleep(1);
      Sound.playSound(Sound.pong_f);
   }
   Thread.sleep(250);
   glucksbringerEingesetztAN("0 Kelvin");
   letzterSpin = b.Spins;
   Thread.sleep(10);
}

while (true) {
   
   if (spin.isDown()) { 
      if (b.Spins <= 0) { 
         BuySound(); 
         b.Spinskaufen(); 
         println("Spins gekauft!");
         b.startsound = true;
         b.kontostandtext = "Kontostand: " + "£" + b.geld;
         b.UpdateText = true;
         b.UpdateSpins = true;
         b.rundenReset();
         StartSound(); 
      } else { 
         println("Spins gemacht gamba"); 
         b.glucksbringerunddannspielen();
      }
   } 
   if (b.jackbotErreicht) {
      RGBehre();
   }
   if (b.UpdateText) {
      updateText();
   }
   if (b.UpdateSpins) {
      updateSpins();
   }
   if (b.kraterEingeschlagen3) {
      b.kraterEingeschlagen3 = false;
      glucksbringerEingesetztAN("Gewonnen!");
   }
   if (b.kraterEingeschlagen2) {
      b.Spins = 0;
      b.UpdateSpins = true;
      b.rundenReset();
      SagenSieEsNichtDochIchSageTrier_Animation();
   }
   if (b.DyatlowCarried == true) {
      b.DyatlowCarried = false;
      b.geld = b.geld + 36;
      b.UpdateText = true;
      glucksbringerEingesetztAN("Dyatlow");
      b.anzahlDerGewinne = 0;
   }
   if (b.zeroKelvinSlot > 0) {
      zeroKelvinAnimation(b.zeroKelvinSlot);
      b.zeroKelvinSlot = 0;
   }
   if (b.ZeroKelvinVerwendet) {
      if ((letzterSpin) != b.Spins) {
         b.ZeroKelvinVerwendet = false;
         Slot1.setBorderColor(0x000000);
         Slot1.setFillColor(0xffffff);
         Slot2.setBorderColor(0x000000);
         Slot2.setFillColor(0xffffff);
         Slot3.setBorderColor(0x000000);
         Slot3.setFillColor(0xffffff);
      }
   }
}

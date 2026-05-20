

class einarmigerbandit extends Actor {
   int baseluckGlocke;
   int baseluckKirsche;
   int baseluckDiamant;
   int baseluckSieben;
   int baseluckC18H24O2;
   int baseluckC60;
   boolean amlaufen;
   int anzahl3;
   int geld;
   int BuffVonWeinflasche = 30;
   int BuffVonGewonnen = 100;
   int Spins = 0;
   int jackpotGewinnFurKirsche = 15;
   int jackpotGewinnFurGlocke = 28;
   int jackpotGewinnFurDiamant = 42;
   int jackpotGewinnFurSieben = 77;

   String bereich;

   ArrayList<String> glucksbringer = new ArrayList<>();
   String glucksbringerZurVerfugung[] = {
      "Weinfalsche",
      "Gewonnen!"
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
      baseluckGlocke = 15;
      baseluckKirsche = 20;
      baseluckDiamant = 12;
      baseluckSieben = 8;
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

         boolean gewonnendrinnen = false;

         for (int i = 0; i < glucksbringer.size(); i++) {
            String current = glucksbringer.get(i);
            if (current.equals(glucksbringerZurVerfugung[0])) {
               WertFürKirsche = WertFürKirsche + BuffVonWeinflasche;
            } else if (current.equals(glucksbringerZurVerfugung[1])) {
               WertFürSieben = WertFürSieben + BuffVonGewonnen;
               gewonnendrinnen = true;
            } 
         }
         spielen(WertFürGlocke, WertFürKirsche, WertFürDiamant, WertFürSieben, gewonnendrinnen);
      }
   }


   void spielen(int glucksbringerWertGlocke, int glucksbringerWertKirsche, int glucksbringerWertDiamant, int glucksbringerWertSieben, boolean gewonnendrinne) { 
      if (amlaufen) return;
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
         }

         anzahl3++;
         // println(anzahl3);

         if (anzahl3 == 3) {
            String symbol1 = "";
            String symbol2 = "";
            String symbol3 = "";
            
            for (int i = 1; i <= 3; i++) {
               if (i == 1) {
                  symbol1 = symbole.get(0);
               } else if (i == 2) {
                  symbol2 = symbole.get(1);
               } else if (i == 3) {
                  symbol3 = symbole.get(2);
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
            }
         }
      }
      Thread.sleep(1000);
      Spins--;
      amlaufen = false;
      anzahl3 = 0;
   }

   LocalDateTime datum() {
      return LocalDateTime.now();
   }

   void JackpotAdden(int s) {
      if (s == 1) {
         geld = geld + jackpotGewinnFurGlocke;
      } else if (s == 2) {
         geld = geld + jackpotGewinnFurKirsche;
      } else if (s == 3) {
         geld = geld + jackpotGewinnFurDiamant;
      } else if (s == 4) {
         geld = geld + jackpotGewinnFurSieben;
      }
      println("Geld durch Gewinn: ", geld);
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
   
      

         logInt(Spins, 3);
         logInt(geld, 3);
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

/*unterer Reiter mit Basisinformationen
RoundedRectangle statistics = new RoundedRectangle();
statistics.setWidth(500);
statistics.setHeight(150);
statistics.setY(300);
statistics.setX(400);
statistics.setFillColor(0xffd000);
*/


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

// Button
Button spin = new Button(355.0, 340.0, 30.0, "SPIN");
spin.setFillColor(0xffe23e);
spin.setBorderColor(0xffe23e);
spin.setTextColor(0x000000);

einarmigerbandit b = new einarmigerbandit();
b.glucksbringerAdden(0);

while (true) {
   if (spin.isDown()) { if (b.Spins <= 0) { b.Spinskaufen(); println("Spins gekauft!"); } else { println("Spins gemacht gamba"); b.glucksbringerunddannspielen(); } } 
}



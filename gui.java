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

//Kirsche

/*
Rectangle halter = new Rectangle();
halter.setFillColor(0x774a06);
halter.rotate(-75);
halter.setHeight(15);
halter.setWidth(5);
Circle Kreis2 = new Circle();
Circle Kreis1 = new Circle();
Kreis1.setScale(0.4);
Kreis2.setScale(0.4);
Kreis2.setX(89);
Kreis1.setFillColor("red");
Kreis2.setFillColor("red");

*/


// Button
Button spin = new Button(355.0, 340.0, 30.0, "SPIN");
spin.setFillColor(0xffe23e);
spin.setBorderColor(0xffe23e);
spin.setTextColor(0x000000);

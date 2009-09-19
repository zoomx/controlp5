import controlP5.*;

ControlP5 controlP5;
ControlFont font;
int myColorBackground = color(0,0,0);
controlP5.Button b;

int buttonValue = 1;

void setup() {
  size(640,480);
  smooth();
  frameRate(30);
  controlP5 = new ControlP5(this);
  controlP5.addButton("button",10,0,0,80,20).setId(1);
  b = controlP5.addButton("buttonValue",4,100,190,80,20);
  b.setId(2);
  b.setWidth(200);
  b.setHeight(200);
  font = new ControlFont(createFont("Times",20),20);
  font.setSmooth(true);
  controlP5.controller("button").captionLabel().setControlFont(font);
  controlP5.controller("button").captionLabel().setControlFontSize(10);
  b.captionLabel().setControlFont(font);
  b.captionLabel().setControlFontSize(80);
  
  b.captionLabel().toUpperCase(false);
  b.captionLabel().set("hello");
  b.captionLabel().style().marginLeft = 4;
  b.captionLabel().style().marginTop = 36;
  
}

void draw() {
  background(buttonValue*10);
  b.position().x += ((isOpen==true ? 0:-200) - b.position().x) * 0.2;
}

void controlEvent(ControlEvent theEvent) {
  println(theEvent.controller().id());
}

boolean isOpen;

void button(float theValue) {
  println("a button event. "+theValue);
  isOpen = !isOpen;
  controlP5.controller("button").setCaptionLabel((isOpen==true) ? "close":"open");
}


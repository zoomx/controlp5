import controlP5.*;


ControlP5 controlP5;

int myColorBackground = color(0,0,0);

int sliderValue = 100;

void setup() {
  size(400,400);
  frameRate(30);
  controlP5 = new ControlP5(this);
  PFont p = createFont("Georgia",20); 
  controlP5.setControlFont(p,20);
  controlP5.setColorLabel(color(255,128));
  Slider s = controlP5.addSlider("slider",100,167,128,100,160,10,100);
  s = controlP5.addSlider("sliderValue",0,255,128,200,200,64,100);
  
  controlP5.Label label = s.valueLabel();
  label.setColor(color(255,128));
  label.style().marginTop = -10;
  label = s.captionLabel();
  label.toUpperCase(false);
  
  // label.setControlFont(new ControlFont(createFont("Georgia",12),12));
  // label.style().marginTop = 2;
  // label.toUpperCase(true);
  
}

void draw() {
  background(0);
  fill(sliderValue);
  rect(0,0,width,100);
  textLeading(mouseX);
}

void slider(float theColor) {
  myColorBackground = color(theColor);
  println("a slider event. setting background to "+theColor);
}

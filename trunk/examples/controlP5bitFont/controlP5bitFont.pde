import controlP5.*;


ControlP5 controlP5;
controlP5.Label label;
Textfield myTextfield;
int myBitFontIndex;


void setup() {
  size(400,400);
  controlP5 = new ControlP5(this);
  label = new controlP5.Label(this, "some funny text here.", 100,40);
  label.position.x = 100;
  label.position.y = 100;
  label.toUpperCase(false);
  int myBitFontIndex = label.bitFontRenderer.addBitFont(loadImage("myFontA.gif"));
  label.setFont(myBitFontIndex);

  myTextfield = controlP5.addTextfield("texting",100,160,200,20);
  myTextfield.setFocus(true);
  myTextfield.valueLabel().setFont(myBitFontIndex);
  myTextfield.valueLabel().style().marginTop = -2;
}

void draw() {
  background(0);
  label.draw(this);
}

void mousePressed() {
  if(label.getFont() == ControlP5.standard56) {
    label.setFont(ControlP5.standard58);
  } 
  else {
    label.setFont(ControlP5.standard56);
  }
}


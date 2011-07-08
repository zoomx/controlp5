/**
 * ControlP5 Accordion
 * by andreas schlegel, 2011
 */
import controlP5.*;
import processing.opengl.*;

ControlP5 controlP5;

Accordion a;
ControlGroup l2;

void setup() {
  size(400,400,OPENGL);
  frameRate(30);

  controlP5 = new ControlP5(this);
  ControlGroup l = controlP5.addGroup("myGroup",100,200,200);
  controlP5.addBang("A-1",0,4,20,20).setGroup(l);
  controlP5.addBang("A-2",30,4,20,20).setGroup(l);
  l.setBackgroundColor(color(255,100));
  l.setBackgroundHeight(150);

  l2 = controlP5.addGroup("myGroup2",0,20,200);
  l2.setBackgroundColor(color(255,50));
  l2.setBackgroundHeight(150);

  controlP5.addBang("B-1",0,4,20,20).setGroup(l2);
  RadioButton r = controlP5.addRadioButton("radio",100,4);
  r.addItem("black",0);
  r.addItem("red",1);
  r.addItem("green",2);
  r.addItem("blue",3);
  r.addItem("grey",4);
  r.setColorLabel(0xffffffff);
  r.setValue(1);
  r.setGroup(l2);
  ControlGroup l3 = controlP5.addGroup("myGroup3",100,100,200);
  l3.setBackgroundColor(color(255,50));
  l3.setBackgroundHeight(50);
  
  a = controlP5.addAccordion("acco", 100,100,200);
  a.addItem(l);
  a.addItem(l2);
  a.addItem(l3);
  
  controlP5.addSlider("hello",0,100,50,10,10,100,10).moveTo(l3);
}


void keyPressed() {
  if(key=='1') {
    l2.remove();
  } else if (key=='2') {
    a.setWidth(300);
  } else if (key=='3') {
    a.setItemHeight(100);
  }
}

void draw() {
  background(0);
}



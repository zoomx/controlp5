/**
 * ControlP5 Checkbox
 * by andreas schlegel, 2009
 */
import controlP5.*;

ControlP5 controlP5;

CheckBox checkbox;

int myColorBackground;
void setup() {
  size(400,400);
  smooth();
  controlP5 = new ControlP5(this);
  checkbox = controlP5.addCheckBox("checkBox",20,20);  
  checkbox.setColorForeground(color(120));
  checkbox.setColorActive(color(255));
  checkbox.setColorLabel(color(128));
  checkbox.setItemsPerRow(3);
  checkbox.setSpacingColumn(30);
  checkbox.setSpacingRow(10);
  checkbox.addItem("0",0);
  checkbox.addItem("10",10);
  checkbox.addItem("50",50);
  checkbox.addItem("100",100);
  checkbox.addItem("200",200);
  checkbox.addItem("5",5);
}

void keyPressed() {
  if(key==' '){
    checkbox.deactivateAll();
  }
}

void draw() {
  background(myColorBackground);
  fill(0);
  rect(10,10,150,60);
}

void controlEvent(ControlEvent theEvent) {
  if(theEvent.isGroup()) {
    myColorBackground = 0;
    print("got an event from "+theEvent.group().name()+"\t");
    for(int i=0;i<theEvent.group().arrayValue().length;i++) {
      int n = (int)theEvent.group().arrayValue()[i];
      print(n);
      if(n==1) {
        myColorBackground += ((RadioButton)theEvent.group()).getItem(i).internalValue();
      }
    }
    println();
  }
}




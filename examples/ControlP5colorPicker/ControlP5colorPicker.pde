/**
 * ControlP5 Color picker. a simple color picker, 
 * 4 horizontal sliders controlling the RGBA channels of a color.
 * to grab the current color value, use function getColorValue() of
 * the color picker.
 *
 * by andreas schlegel, 2011
 */

import controlP5.*;

ControlP5 controlP5;

ColorPicker cp;

void setup() {
  size(400,400);
  controlP5 = new ControlP5(this);
  cp = controlP5.addColorPicker("picker",20,20,255,20);
}

void draw() {
  background(cp.getColorValue());
  fill(0);
  rect(10,10,275,80);
}

void keyPressed() {
  switch(key) {
    case('1'):
    cp.setArrayValue(new float[] {120,0,120,255});
    break;
    case('2'):
    cp.setColorValue(color(255,0,0,255));
    break;
  }
}



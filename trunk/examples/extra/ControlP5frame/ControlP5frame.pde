/**
 * ControlP5 Controlframe
 * with controlP5 2.0 all java.awt dependencies have been removed
 * as a consequence the option to display controllers in a separate
 * window had to be removed as well. 
 * this example shows you how to create a java.awt.frame and use controlP5
 *
 * by Andreas Schlegel, 2012
 * www.sojamo.de/libraries/controlp5
 *
 */

import java.awt.Frame;
import java.awt.BorderLayout;
import controlP5.*;

private ControlP5 cp5;

ControlFrame cf;

int def;

void setup() {
  size(200, 200);
  cp5 = new ControlP5(this);
  cp5.addSlider("test");
  
  // by calling function addControlFrame() a
  // new frame is created and an instance of class
  // ControlFrame is instanziated.
  cf = addControlFrame("extra");
  
  // with the newly created controlFrame we can
  // access the controlP5 object by calling cf.control()
  // and proceed to create new controllers as usual. 
  cf.control().addSlider("abc").plugTo(this, "def").setRange(0, 255);
}

void draw() {
  background(def);
}

ControlFrame addControlFrame(String theName) {
  Frame f = new Frame(theName);
  ControlFrame p = new ControlFrame(this, 400, 400);
  f.add(p);
  p.init();
  f.setTitle(theName);
  f.setSize(p.w, p.h);
  f.setLocation(120, 120);
  f.setResizable(false);
  f.setVisible(true);
  return p;
}


// the ControlFrame class extends PApplet, so we 
// are creating a new processing applet inside a
// new frame with a controlP5 object loaded
public class ControlFrame extends PApplet {

  ControlP5 cp5;

  Object parent;

  int w, h;

  int abc;

  private ControlFrame() {
  }

  ControlFrame(Object theParent, int theWidth, int theHeight) {
    parent = theParent;
    w = theWidth;
    h = theHeight;
  }


  public ControlP5 control() {
    return cp5;
  }
  public void setup() {
    size(w, h);
    frameRate(25);
    cp5 = new ControlP5(this);
  }

  public void draw() {
      background(abc);
  }
}


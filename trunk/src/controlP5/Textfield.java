package controlP5;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import processing.core.PApplet;

/**
 * controlP5 is a processing gui library.
 *
 *  2006-2011 by Andreas Schlegel
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307 USA
 *
 * @author 		Andreas Schlegel (http://www.sojamo.de)
 * @modified	##date##
 * @version		##version##
 *
 */

/**
 * A singleline input textfield, use arrow keys to go back and forth, use backspace to delete
 * characters. Using the up and down arrows lets you cycle through the history of the textfield.
 * 
 * @example controllers/ControlP5textfield
 * @nosuperclasses Controller Controller
 */
public class Textfield extends Controller {
	
	/*
	 * TODO 
	 * textspacing does not work properly for bitfonts
	 * sometimes first row of pixels in a bitfont texture gets cut off
	 */

	private boolean isTexfieldActive;

	private boolean isKeepFocus;

	private StringBuffer _myTextBuffer = new StringBuffer();

	private int _myTextBufferIndex = 0;

	private int _myTextBufferOverflow = 0;

	private int _myTextBufferIndexPosition = 0;

	public static int cursorWidth = 1;

	private Map<Integer, TextfieldCommand> keyMapping;

	private InputFilter _myInputFilter = InputFilter.DEFAULT;

	private List<Integer> ignorelist;

	private LinkedList<String> _myHistory;

	private int _myHistoryIndex;

	private boolean changed;

	private int len = 0;

	int offset = 4;

	int margin = 2;
	
	
	public enum InputFilter {
		INTEGER(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')), FLOAT(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.')), DEFAULT(
				new LinkedList<Character>());

		final List<Character> allowed;

		InputFilter(List<Character> theList) {
			allowed = theList;
		}

		protected boolean apply(char theCharater) {
			if (allowed.isEmpty()) {
				return true;
			} else {
				return allowed.contains(theCharater);
			}
		}

	}

	public Textfield(ControlP5 theControlP5, ControllerGroup theParent, String theName, String theDefaultValue, int theX, int theY, int theWidth, int theHeight) {
		super(theControlP5, theParent, theName, theX, theY, theWidth, theHeight);
		_myCaptionLabel = new Label(cp5, theName.toUpperCase(), 0, 0, color.getCaptionLabel());
		_myBroadcastType = STRING;
		_myValueLabel.setFixedSize(true);
		_myValueLabel.set("");
		_myValueLabel.setWidth(width - margin * 2);
		_myValueLabel.setPadding(0, 0);
		_myValueLabel.align(LEFT, CENTER);
		_myValueLabel.setColor(color.getValueLabel());
		_myValueLabel.toUpperCase(false);
		_myValueLabel.setFont(ControlP5.bitFont == ControlP5.standard58 ? ControlP5.standard56 : ControlP5.bitFont);
		_myValueLabel.setLabeltype(_myValueLabel.new SinglelineTextfield());

		_myHistory = new LinkedList<String>();
		_myHistory.addFirst("");

		keyMapping = new HashMap<Integer, TextfieldCommand>();
		keyMapping.put(ENTER, new Enter());
		keyMapping.put(DEFAULT, new InsertCharacter());
		keyMapping.put(DELETE, new DeleteCharacter());
		keyMapping.put(BACKSPACE, new DeleteCharacter());
		keyMapping.put(LEFT, new MoveLeft());
		keyMapping.put(RIGHT, new MoveRight());
		keyMapping.put(UP, new MoveUp());
		keyMapping.put(DOWN, new MoveDown());

		ignorelist = new LinkedList<Integer>();
		ignorelist.add(SHIFT);
		ignorelist.add(ALT);
		ignorelist.add(CONTROL);
		ignorelist.add(TAB);
		ignorelist.add(COMMANDKEY);

		setInputFilter(DEFAULT);
		changed = true;

	}

	public Textfield setFocus(boolean theValue) {
		isTexfieldActive = isActive = theValue;
		changed = true;
		return this;
	}

	public Textfield keepFocus(boolean theValue) {
		isKeepFocus = theValue;
		if (isKeepFocus) {
			setFocus(true);
		}
		return this;
	}

	public void setInputFilter(int theInputType) {
		switch (theInputType) {
		case (INTEGER):
			_myInputFilter = InputFilter.INTEGER;
			break;
		case (FLOAT):
			_myInputFilter = InputFilter.FLOAT;
			break;
		default:
			_myInputFilter = InputFilter.DEFAULT;
			break;
		}
	}

	@Override
	public Textfield setValue(float theValue) {
		setValue("" + theValue);
		return this;
	}

	@Override
	protected void updateFont(ControlFont theControlFont) {
		super.updateFont(theControlFont);
		changed = true;
	}

	public Textfield setValue(String theText) {
		_myTextBuffer = new StringBuffer(theText);
		setIndex(_myTextBuffer.length());
		changed = true;
		return this;
	}

	public Textfield setText(String theText) {
		return setValue(theText);
	}

	@Override
	protected void mousePressed() {
		if (isActive) {
			System.out.println("adjust cursor");
		}
		int x = (int) (getControlWindow().mouseX - getAbsolutePosition().x);
		int y = (int) (getControlWindow().mouseY - getAbsolutePosition().y);

		System.out.println(x + ":" + y);
		setFocus(true);
	}

	@Override
	protected void mouseReleasedOutside() {
		if (isKeepFocus == false) {
			isTexfieldActive = isActive = false;
		}
	}

	public int getIndex() {
		return _myTextBufferIndex;
	}

	String getText() {
		return _myTextBuffer.toString();
	}

	@Override
	public void draw(PApplet theApplet) {
		if (changed) {
			updateLabel(theApplet);
			changed = false;
		}
		theApplet.pushStyle();
		theApplet.fill(color.getBackground());
		theApplet.pushMatrix();
		theApplet.translate(position.x, position.y);
		theApplet.rect(0, 0, width, height);
		theApplet.noStroke();
		theApplet.fill(isTexfieldActive ? color.getActive() : color.getForeground());
		theApplet.rect(0, 0, width, 1);
		theApplet.rect(0, height - 1, width, 1);
		theApplet.rect(-1, 0, 1, height);
		theApplet.rect(width, 0, 1, height);

		theApplet.fill(_myValueLabel.getColor(), 128);

		if (_myValueLabel.getFont().get() instanceof ControlFont.PFontLabel) {
			if (_myTextBufferIndexPosition > len - offset) {
				theApplet.textAlign(PApplet.RIGHT);
				theApplet.translate(getWidth() - margin * 2, 0);
				if (isTexfieldActive) {
					theApplet.rect(2, 2, cursorWidth, height - 4);
				}
			} else {
				theApplet.textAlign(PApplet.LEFT);
				theApplet.translate(margin, 0);
				if (isTexfieldActive) {
					theApplet.rect(PApplet.max(0, PApplet.min(_myTextBufferIndexPosition, getWidth() - margin)), 2, cursorWidth, height - 4);
				}
			}
		} else {
			theApplet.translate(margin, 0);
			if (isTexfieldActive) {
				theApplet.rect(PApplet.min(getWidth() - margin * 2, _myTextBufferIndexPosition), 2, cursorWidth, height - 4);
			}
		}

		_myValueLabel.draw(theApplet, 0, 0, this);
		theApplet.popMatrix();
		theApplet.popStyle();
	}

	private void updateLabel(PApplet theApplet) {
		if (_myValueLabel.getFont().get() instanceof ControlFont.PFontLabel) {
			updateLabelPFont(theApplet);
		} else {
			updateLabelBitFont(theApplet);
		}
	}

	private void updateLabelBitFont(PApplet theApplet) {
		String str = getText();
		String t1 = str;
		int ww = ControlFont.getWidthFor(getText(), _myValueLabel, theApplet);
		if (ww < _myValueLabel.getWidth()) {
			_myTextBufferIndexPosition = ControlFont.getWidthFor(getText().substring(0, _myTextBufferIndex), _myValueLabel, theApplet);
			len = _myValueLabel.getWidth();
		} else {
			char[] c = str.toCharArray();
			int mx = 0;
			int n = 0;
			for (int i = 0; i < c.length; i++) {
				n += ControlFont.getWidthFor(c[i] + "", _myValueLabel, theApplet);
				if (n > _myValueLabel.getWidth() - offset) {
					break;
				}
				len = n;
				mx++;
			}
			t1 = "";
			n = 0;
			for (int i = PApplet.max(mx, _myTextBufferIndex - 1); i >= 0; i--) {
				n += ControlFont.getWidthFor(c[i] + "", _myValueLabel, theApplet);
				t1 = c[i] + t1;
				if (n > _myValueLabel.getWidth() - offset) {
					_myTextBufferOverflow = str.indexOf(t1);
					break;
				}
			}
			int strn = PApplet.max(0, PApplet.min(t1.length(), _myTextBufferIndex - _myTextBufferOverflow));
			_myTextBufferIndexPosition = ControlFont.getWidthFor(t1.substring(0, strn), _myValueLabel, theApplet);
		}

		_myValueLabel.setText(t1);
		changed = false;
	}

	private void updateLabelPFont(PApplet theApplet) {
		String str = getText();
		String t1 = str;
		int ww = ControlFont.getWidthFor(str, _myValueLabel, theApplet);
		if ((ww < getWidth() - margin * 2)) {
			_myTextBufferIndexPosition = ControlFont.getWidthFor(t1.substring(0, _myTextBufferIndex), _myValueLabel, theApplet);
			len = getWidth();
		} else {
			char[] c = str.toCharArray();
			int mx = 0;
			int n = 0;
			for (int i = 0; i < c.length; i++) {
				n += theApplet.textWidth(c[i]);
				if (n > _myValueLabel.getWidth() - offset) {
					break;
				}
				len = n;
				mx++;
			}
			t1 = "";
			n = 0;
			for (int i = PApplet.max(mx, _myTextBufferIndex - 1); i >= 0; i--) {
				n += theApplet.textWidth(c[i]);
				t1 = c[i] + t1;
				if (n >= _myValueLabel.getWidth() - offset) {
					_myTextBufferOverflow = str.indexOf(t1);
					break;
				}
			}
			int strn = PApplet.max(0, PApplet.min(t1.length(), _myTextBufferIndex - _myTextBufferOverflow));
			_myTextBufferIndexPosition = ControlFont.getWidthFor(t1.substring(0, strn), _myValueLabel, theApplet);
		}
		_myValueLabel.setText(t1);
		changed = false;
	}

	public void keyEvent(KeyEvent theKeyEvent) {
		if (isUserInteraction && isTexfieldActive && isActive && theKeyEvent.getID() == KeyEvent.KEY_PRESSED) {
			if (ignorelist.contains(cp5.keyHandler.keyCode)) {
				return;
			}
			if (keyMapping.containsKey(cp5.keyHandler.keyCode)) {
				keyMapping.get(cp5.keyHandler.keyCode).execute();
			} else {
				keyMapping.get(DEFAULT).execute();
			}
		}
	}

	private void setIndex(int theIndex) {
		_myTextBufferIndex = theIndex;
		changed = true;
	}

	interface TextfieldCommand {
		void execute();
	}

	class InsertCharacter implements TextfieldCommand {
		public void execute() {
			if (_myInputFilter.apply(cp5.keyHandler.key)) {
				_myTextBuffer.insert(_myTextBufferIndex, cp5.keyHandler.key);
				setIndex(_myTextBufferIndex + 1);
			}
		}
	}

	class Enter implements TextfieldCommand {
		public void execute() {
			// update current buffer with the last item inside the input history
			_myHistory.set(_myHistory.size() - 1, _myTextBuffer.toString());
			// set the history index to our last item
			_myHistoryIndex = _myHistory.size();
			// add a new and empty buffer to the history
			_myHistory.add("");
			// create a new text buffer
			_myTextBuffer = new StringBuffer();
			// reset the buffer index
			setIndex(0);
		}
	}

	class DeleteCharacter implements TextfieldCommand {
		public void execute() {
			if (_myTextBuffer.length() > 0 && _myTextBufferIndex > 0) {
				_myTextBuffer.deleteCharAt(_myTextBufferIndex - 1);
				setIndex(_myTextBufferIndex - 1);
			}
		}
	}

	class MoveLeft implements TextfieldCommand {
		public void execute() {
			setIndex((cp5.keyHandler.isCommandDown) ? 0 : PApplet.max(0, _myTextBufferIndex - 1));
		}
	}

	class MoveRight implements TextfieldCommand {
		public void execute() {
			setIndex((cp5.keyHandler.isCommandDown) ? _myTextBuffer.length() : PApplet.min(_myTextBuffer.length(), _myTextBufferIndex + 1));
		}
	}

	class MoveUp implements TextfieldCommand {
		public void execute() {
			if (_myHistoryIndex == 0) {
				return;
			}
			_myHistoryIndex = PApplet.max(0, --_myHistoryIndex);
			_myTextBuffer = new StringBuffer(_myHistory.get(_myHistoryIndex));
			setIndex(_myTextBuffer.length());
		}
	}

	class MoveDown implements TextfieldCommand {
		public void execute() {
			if (_myHistoryIndex >= _myHistory.size() - 1) {
				return;
			}

			_myHistoryIndex = PApplet.min(_myHistory.size() - 1, ++_myHistoryIndex);
			_myTextBuffer = new StringBuffer(_myHistory.get(_myHistoryIndex));
			setIndex(_myTextBuffer.length());
		}
	}

}

package controlP5;

/**
 * controlP5 is a processing gui library.
 *
 *  2007-2011 by Andreas Schlegel
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
 * a controlEvent is sent to a PApplet whenever a controlP5 action has been
 * made. you can receive events from controllers and tabs. by default tab events
 * are disabled and have to be enabled with Tab.activateEvent(). for detailed
 * information see the tab documentation.
 * 
 * @example ControlP5controlEvent
 */
public class ControlEvent {

	protected final ControllerInterface _myController;

	protected boolean isTab;

	protected boolean isController;

	protected boolean isGroup;
	
	public static int UNDEFINDED = -1;
	
	public static int CONTROLLER = 0;
	
	public static int TAB = 1;
	
	public static int GROUP = 2;

	protected int myAction;

	/**
	 * 
	 * @param theController Controller
	 */
	protected ControlEvent(Controller theController) {
		_myController = theController;
		isTab = false;
		isController = true;
		isGroup = false;
	}

	/**
	 * 
	 * @param theController Controller
	 */
	public ControlEvent(Tab theController) {
		_myController = theController;
		isTab = true;
		isGroup = false;
		isController = false;
	}

	/**
	 * 
	 * @param theController Controller
	 */
	public ControlEvent(ControllerGroup theController) {
		_myController = theController;
		isTab = false;
		isGroup = true;
		isController = false;
	}

	/**
	 * @return float
	 */
	public float value() {
		return getValue();
	}

	/**
	 * * returns the value of the controller as float.
	 * @return float
	 */
	public float getValue() {
		return _myController.value();
	}

	/**
	 * @return String
	 */
	public String stringValue() {
		return getStringValue();
	}
	
	/**
	 * returns a string value if applicable to the controller e.g. textfield has a
	 * string value.
	 * 
	 * @return String
	 */
	public String getStringValue() {
		return ((Controller) _myController).stringValue();
	}

	public float[] arrayValue() {
		return getArrayValue();
	}
	
	/**
	 * returns a float array, apllies for e.g. Range.
	 * 
	 * @return
	 */
	public float[] getArrayValue() {
		return _myController.arrayValue();
	}

	
	public Controller controller() {
		return getController();
	}
	
	/**
	 * returns the instance of the controller.
	 * 
	 * @return Controller Bang Button Knob Numberbox Radio Slider Textfield Toggle
	 *         MultiList Matrix
	 */
	public Controller getController() {
		return ((Controller) _myController);
	}

	public Tab tab() {
		return getTab();
	}
	
	/**
	 * return the tab that evoked the event.
	 * 
	 * @return Tab Tab
	 */
	public Tab getTab() {
		return (Tab) _myController;
	}

	
	
	public ControlGroup group() {
		return getGroup();
	}
	
	/**
	 * returns the tab that evoked the event.
	 * 
	 * @return Tab Tab
	 */
	public ControlGroup getGroup() {
		return (ControlGroup) _myController;
	}

	public String label() {
		return getLabel();
	}
	
	/**
	 * gets the label of the controller that evoked the event.
	 * 
	 * @return String
	 */
	public String getLabel() {
		return ((Controller) _myController).label();
	}

	/**
	 * check if the event was evoked by a tab.
	 * 
	 * @return boolean
	 */
	public boolean isTab() {
		return isTab;
	}

	/**
	 * check if the event was evoked by a controller.
	 * 
	 * @return boolean
	 */
	public boolean isController() {
		return isController;
	}

	/**
	 * check if the event was evoked by a controlGroup.
	 * 
	 * @return boolean
	 */
	public boolean isGroup() {
		return isGroup;
	}

	public String name() {
		return getName();
	}
	
	public String getName() {
		return _myController.name();
	}

	public int id() {
		return getId();
	}
	
	public int getId() {
		return _myController.id();
	}

	public int type() {
		if (isController) {
			return CONTROLLER;
		} else if (isTab) {
			return TAB;
		} else if (isGroup) {
			return GROUP;
		}
		return -1;
	}

	/**
	 * checks if the ControlEvent originates from a specific Controller or
	 * ControllerGroup.
	 * 
	 * @param theController
	 * @return
	 */
	public boolean isFrom(ControllerInterface theController) {
		return _myController.equals(theController);
	}
	
	/**
	 * checks if the ControlEvent originates from a specific Controller or
	 * ControllerGroup identifiable by name.
	 * 
	 * @param theController
	 * @return
	 */
	 
	public boolean isFrom(String theControllerName) {
		return _myController.name().equals(theControllerName);
	}
}

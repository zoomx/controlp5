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
 * A controlEvent is sent to a PApplet or a ControlListener whenever a
 * controller value has changed or a tab has been activated. By default tab
 * events are disabled and have to be enabled with Tab.activateEvent(). for
 * detailed information see the tab documentation.
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
	 * * returns the value of the controller as float.
	 * 
	 * @return float
	 */
	public float getValue() {
		return _myController.value();
	}



	/**
	 * returns a string value if applicable to the controller e.g. textfield has
	 * a string value.
	 * 
	 * @return String
	 */
	public String getStringValue() {
		return ((Controller) _myController).getStringValue();
	}

	/**
	 * returns a float array, apllies for e.g. Range.
	 * 
	 * @return
	 */
	public float[] getArrayValue() {
		return _myController.arrayValue();
	}



	/**
	 * returns the instance of the controller.
	 * 
	 * @return Controller Bang Button Knob Numberbox Radio Slider Textfield
	 *         Toggle MultiList Matrix
	 */
	public Controller getController() {
		return ((Controller) _myController);
	}


	/**
	 * return the tab that evoked the event.
	 * 
	 * @return Tab Tab
	 */
	public Tab getTab() {
		return (Tab) _myController;
	}
	


	/**
	 * returns the tab that evoked the event.
	 * 
	 * @return Tab Tab
	 */
	public ControlGroup getGroup() {
		return (ControlGroup) _myController;
	}

	/**
	 * gets the text of the controller's label that has evoked the event.
	 * 
	 * @return String
	 */
	public String getLabel() {
		return ((Controller) _myController).getLabel();
	}

	/**
	 * checks if the event was evoked by a tab.
	 * 
	 * @return boolean
	 */
	public boolean isTab() {
		return isTab;
	}

	/**
	 * checks if the event was evoked by a controller.
	 * 
	 * @return boolean
	 */
	public boolean isController() {
		return isController;
	}

	/**
	 * checks if the event was evoked by a controlGroup.
	 * 
	 * @return boolean
	 */
	public boolean isGroup() {
		return isGroup;
	}


	/**
	 * returns the index name of the controller.
	 * 
	 * @return
	 */
	public String getName() {
		return _myController.name();
	}

	/**
	 * returns the ID of the controller.
	 * 
	 * @return
	 */
	public int getId() {
		return _myController.id();
	}

	/**
	 * returns the type of the controller which can be ControlP5.CONTROLLER,
	 * ControlP5.TAB, ControlP5.GROUP
	 * 
	 * @return
	 */
	public int getType() {
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
	
	

	/**
	 * @deprecated
	 * @return
	 */
	public int type() {
		return getType();
	}


	/**
	 * @deprecated
	 * @return
	 */
	public int id() {
		return getId();
	}
	

	/**
	 * @deprecated
	 * @return
	 */
	public String name() {
		return getName();
	}
	

	/**
	 * @deprecated
	 * @return
	 */
	public String label() {
		return getLabel();
	}


	/**
	 * @return float
	 * @deprecated
	 */
	public float value() {
		return getValue();
	}

	/**
	 * @deprecated
	 * @return String
	 */
	public String stringValue() {
		return getStringValue();
	}

	/**
	 * @deprecated
	 * @return
	 */
	public float[] arrayValue() {
		return getArrayValue();
	}
	/**
	 * @deprecated
	 * @return
	 */
	public Controller controller() {
		return getController();
	}

	/**
	 * @deprecated
	 * @return
	 */
	public ControlGroup group() {
		return getGroup();
	}


	/**
	 * @deprecated
	 * @return
	 */
	public Tab tab() {
		return getTab();
	}
}

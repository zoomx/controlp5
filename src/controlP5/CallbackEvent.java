package controlP5;

/**
 * A CallbackEvent is sent to a PApplet or a CallbackListener whenever a
 * controller action such as enter, leave, press, release, etc. has occurred.
 * 
 * @example ControlP5callbackEvent
 */
public class CallbackEvent {

	private final int _myAction;

	private final Controller _myController;

	CallbackEvent(Controller theController, int theAction) {
		_myController = theController;
		_myAction = theAction;
	}

	/**
	 * returns an int value of either one of the following static variables
	 * ControlP5.ACTION_PRESSED, ControlP5.ACTION_ENTER, ControlP5.ACTION_LEAVE,
	 * ControlP5.ACTION_RELEASED, ControlP5.ACTION_RELEASEDOUTSIDE,
	 * ControlP5.ACTION_BROADCAST
	 * 
	 * @return int
	 */
	public int getAction() {
		return _myAction;
	}

	/**
	 * returns the Controller that triggered the Callback Event.
	 * 
	 * @return Controller
	 */
	public Controller getController() {
		return _myController;
	}

}

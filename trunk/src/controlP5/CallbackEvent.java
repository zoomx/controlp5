package controlP5;

public class CallbackEvent {

	private final int _myAction;
	private final Controller _myController;
	
	CallbackEvent(Controller theController, int theAction) {
		_myController = theController;
		_myAction = theAction;
	}
	
	public int getAction() {
		return _myAction;
	}
	
	public Controller getController() {
		return _myController;
	}
	
}

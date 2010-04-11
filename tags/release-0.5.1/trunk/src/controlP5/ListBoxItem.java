package controlP5;

public class ListBoxItem {
	protected String name;
	protected String text;
	protected int value;
	protected boolean isActive;
	protected CColor color;
	protected int id = -1;
		
	protected ListBoxItem(
			ListBox theListBox,
	        String theName,
	        int theValue) {
		name = theName;
		text = theName;
		value = theValue;
		color = new CColor(theListBox.color);
	}
	
	public CColor getColor() {
		return color;
	}
	
	public void setColor(CColor theColor) {
		color.set(theColor);
	}
	
	public void setColorActive(int theColor) {
		color.colorActive = theColor;
	}
	
	public void setColorForeground(int theColor) {
		color.colorForeground = theColor;
	}
	
	public void setColorBackground(int theColor) {
		color.colorBackground = theColor;
	}
	
	public void setColorLabel(int theColor) {
		color.colorCaptionLabel = theColor;
	}
	
	
	public void setId(int theId) {
		id = theId;
	}
	public int getId() {
		return id;
	}
}
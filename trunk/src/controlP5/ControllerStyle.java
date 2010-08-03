package controlP5;

/**
 * controlP5 is a processing gui library.
 *
 *  2007-2010 by Andreas Schlegel
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

public class ControllerStyle {

	public int paddingTop = 0;
	public int paddingRight = 0;
	public int paddingBottom = 0;
	public int paddingLeft = 0;
	public int marginTop = 0;
	public int marginRight = 0;
	public int marginBottom = 0;
	public int marginLeft = 0;
	public int background;
	public int backgroundWidth = -1;
	public int backgroundHeight = -1;
	public int color;

	/*
	 * 
	 */
	public void margin(int theValue) {
		marginTop = theValue;
		marginRight = theValue;
		marginBottom = theValue;
		marginLeft = theValue;
	}

	/*
	 * 
	 */
	public void padding(int theValue) {
		paddingTop = theValue;
		paddingRight = theValue;
		paddingBottom = theValue;
		paddingLeft = theValue;
	}

	/*
	 * 
	 */
	public void margin(int theTop, int theRight, int theBottom, int theLeft) {
		marginTop = theTop;
		marginRight = theRight;
		marginBottom = theBottom;
		marginLeft = theLeft;
	}

	/*
	 * 
	 */
	public void padding(int theTop, int theRight, int theBottom, int theLeft) {
		paddingTop = theTop;
		paddingRight = theRight;
		paddingBottom = theBottom;
		paddingLeft = theLeft;
	}

	/*
	 * 
	 */
	public void moveMargin(int theTop, int theRight, int theBottom, int theLeft) {
		marginTop += theTop;
		marginRight += theRight;
		marginBottom += theBottom;
		marginLeft += theLeft;
	}

	/*
	 * 
	 */
	public void movePadding(int theTop, int theRight, int theBottom, int theLeft) {
		paddingTop += theTop;
		paddingRight += theRight;
		paddingBottom += theBottom;
		paddingLeft += theLeft;
	}
}

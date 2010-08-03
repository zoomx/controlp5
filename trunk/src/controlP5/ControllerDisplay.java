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

import processing.core.PApplet;

/**
 * The interface ControllerDisplay can be used to define custom displays for
 * controllers.
 * 
 * @see controlP5.draw(processing.core.PApplet)
 * @see controlP5.setDisplay(ControllerDisplay)
 * 
 */
public interface ControllerDisplay {

	/**
	 * display() will be called by the controller's draw() function and will pass
	 * on a reference of PApplet and the Controller itself to your custom display.
	 * 
	 * @param theApplet
	 * @param theController
	 */
	public void display(PApplet theApplet, Controller theController);

}

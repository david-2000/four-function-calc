/**
 *	UI Inteface for the Calculator's Front-end.
 *  @author <a href="mailto:david.pineda@my.wheaton.edu">David Pineda</a>
 */

package ui;
// Imports
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public abstract class CalcUI
{
	/** 
	 *      * The character of the plus/minus sign.
	 *           */
	public static final char PLUS_MINUS =  177;


	/**
	 * Number of digits that can be displayed in the calculator screen.
	 */
	protected int n = 20;



	/**
	 * Sets the ActionListener object attached for the Calculator button
	 * represented by `button`.
	 * @param button A character representation of the button we are attaching an ActionListener to.
	 * @param action The ActionListener object to attach to a specific button.
	 * @throws NoSuchButtonExcetion If the specified button does not exist in the calculator.
	 * @throws InvalidListenerException If the ActionListener Object is null
	 * @returns True if success, False otherwise (e.g. the button provided does not actually exist)
	 */
	public abstract boolean SetButtonAction(char button, ActionListener action);
	

	/**
	 * Retrieves the number currently in display by the calculator.
	 * @return A string with the contents of the calculator screen.
	 */
	public abstract String GetNumber();


	/**
	 * Updates the number currently in display by the calculator.
	 * @param num A string with the contents to overwrite the calculator screen with.
	 */
	public abstract void SetNumber(BigDecimal num);

	/**
	 * Simulates an pressing a given key to trigger the corresponding ActionListener.
	 * @param key The calculatro key to be pressed.
	 */
	public abstract void PressKey(char key);
}

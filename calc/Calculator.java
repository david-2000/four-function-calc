/**
 *	Internal system for the Calculator
 *  @author <a href="mailto:david.pineda@my.wheaton.edu">David Pineda</a>
 */
package calc;

import ui.CalcUI;

public abstract class Calculator
{
	protected CalcUI ui;

	public abstract void addDigit(double d);

	public abstract void clear();

	public abstract void add();

	public abstract void sub();

	public abstract void mult();

	public abstract void div();

	public abstract void equals();

	public abstract void decimal();

	public abstract void plusMinus();
	
}

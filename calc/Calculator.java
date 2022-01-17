/**
 *	Internal system for the Calculator
 *  @author <a href="mailto:david.pineda@my.wheaton.edu">David Pineda</a>
 */
package calc;

import ui.CalcUI;

public class Calculator
{
	private CalcUI ui;
	private double num;
	private double mem;

	public Calculator(CalcUI ui)
	{
		this.ui = ui;
		num = mem = 0.0;
	}

	public void addDigit(int d)
	{
		num *= 10;
		num += d;
		ui.SetNumber(num+"");
	}

	public void clear()
	{
		num = 0;
		ui.SetNumber(num+"");
	}
}

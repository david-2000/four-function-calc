/**
 *	Internal system for the Calculator
 *  @author <a href="mailto:david.pineda@my.wheaton.edu">David Pineda</a>
 */
package calc;

import ui.CalcUI;
import java.math.BigDecimal;

public class SimpleCalculator extends Calculator
{
	private BigDecimal num;
	private BigDecimal mem;
	private char nextOp;
	private BigDecimal neg;
	private BigDecimal dec;
	private BigDecimal ten;
	private boolean restart;

	private BigDecimal bd(double num){ return new BigDecimal(num); }


	public SimpleCalculator(CalcUI ui)
	{
		this.ui = ui;
		num =  bd(0.0);
		mem = bd(0.0);
		neg = bd(1.0);
		dec = bd(1.0);
		ten = bd(10.0);
		nextOp = 'e';
	}


	public void addDigit(double d)
	{
		if ( restart )
			num = bd(0.0);
		restart = false;
		num = num.multiply(ten);
		BigDecimal digit = bd(d).divide(dec);
		digit = digit.multiply(neg);
		num = num.add(digit);
		dec = dec.multiply(bd(10.0).divide(ten));

		updateUI();
	}


	public void clear()
	{
		num = mem = bd(0.0);
		neg = dec = bd(1.0);
		ten = bd(10.0);
		nextOp = 'e';
		updateUI();
	}


	private void updateUI()
	{
		ui.SetNumber(num);
	}


	private void setOp(char op)
	{
		nextOp = op;
		restart = true;
	}


	public void add()
	{
		evaluate();
		this.setOp('+');
	}


	public void sub()
	{
		evaluate();
		this.setOp('-');
	}


	public void mult()
	{
		evaluate();
		this.setOp('*');
	}


	public void div()
	{
		evaluate();
		this.setOp('/');
	}


	public void decimal()
	{
		if ( ten.compareTo(bd(1.0)) == 0 )
			return;
		dec = dec.multiply(bd(10.0));
		ten = bd(1.0);
		updateUI();
	}


	public void plusMinus()
	{
		num = num.multiply(bd(-1.0));
		neg = neg.multiply(bd(-1.0));
		updateUI();
	}


	public void equals()
	{
		evaluate();
		num = mem.add(bd(0.0));
		mem = bd(0.0);
		updateUI();
		this.setOp('e');
		restart = true;
	}


	public void evaluate()
	{
		switch(nextOp){
			case '+':
				num = mem.add(num);
				break;
			case '-':
				mem = mem.subtract(num);
				break;
			case '*':
				mem = mem.multiply(num);
				break;
			case '/':
				mem = mem.divide(num);
				break;
			case 'e':
				mem = num.add(bd(0.0));
				break;
			default:
				break;
		}
		num = bd(0.0);
		neg = dec = bd(1.0);
		ten = bd(10.0);
	}
}

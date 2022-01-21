/**
 *	Internal system for the Calculator
 *  @author <a href="mailto:david.pineda@my.wheaton.edu">David Pineda</a>
 */
package calc;

import ui.CalcUI;
import java.math.BigDecimal;
import java.math.MathContext;

public class SimpleCalculator extends Calculator
{
	private boolean DEBUG = false;

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

	private void DEBUG(String message)
	{
		if (DEBUG)
			System.err.println(message);
	}

	private void restart_num()
	{
		if ( restart )
		{
			ten = bd(10.0);
			neg = dec = bd(1.0);
			mem = num;
			num = bd(0.0);
		}
		restart = false;
	}

	public void addDigit(double d)
	{
		restart_num();

		num = num.multiply(ten, MathContext.DECIMAL64);
		BigDecimal digit = bd(d).divide(dec, MathContext.DECIMAL64);
		digit = digit.multiply(neg, MathContext.DECIMAL64);
		num = num.add(digit);
		dec = dec.multiply(bd(10.0).divide(ten, MathContext.DECIMAL64), MathContext.DECIMAL64);

		DEBUG(String.format("<%d> -- ten[%s]\tdec[%s]\tneg[%s]\tmen[%s]\tnum[%s]\top[%c]\trestart[%b]", (int)d,ten, dec,neg,mem,num,nextOp,restart));

		updateUI();
	}


	public void clear()
	{
		num = mem = bd(0.0);
		neg = dec = bd(1.0);
		ten = bd(10.0);
		nextOp = 'e';
		updateUI();
		DEBUG(String.format("<C> -- ten[%s]\tdec[%s]\tneg[%s]\tmen[%s]\tnum[%s]\top[%c]\trestart[%b]",ten, dec,neg,mem,num,nextOp,restart));
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
		DEBUG(String.format("<+> -- ten[%s]\tdec[%s]\tneg[%s]\tmen[%s]\tnum[%s]\top[%c]\trestart[%b]",ten, dec,neg,mem,num,nextOp,restart));
	}


	public void sub()
	{
		evaluate();
		this.setOp('-');
		DEBUG(String.format("<-> -- ten[%s]\tdec[%s]\tneg[%s]\tmen[%s]\tnum[%s]\top[%c]\trestart[%b]",ten, dec,neg,mem,num,nextOp,restart));
	}


	public void mult()
	{
		evaluate();
		this.setOp('*');
		DEBUG(String.format("<*> -- ten[%s]\tdec[%s]\tneg[%s]\tmen[%s]\tnum[%s]\top[%c]\trestart[%b]",ten, dec,neg,mem,num,nextOp,restart));
	}


	public void div()
	{
		evaluate();
		this.setOp('/');
		DEBUG(String.format("</> -- ten[%s]\tdec[%s]\tneg[%s]\tmen[%s]\tnum[%s]\top[%c]\trestart[%b]",ten, dec,neg,mem,num,nextOp,restart));
	}


	public void decimal()
	{
		restart_num();
		if ( ten.compareTo(bd(1.0)) == 0 )
			return;
		dec = dec.multiply(bd(10.0));
		ten = bd(1.0);
		updateUI();
		DEBUG(String.format("<.> -- ten[%s]\tdec[%s]\tneg[%s]\tmen[%s]\tnum[%s]\top[%c]\trestart[%b]",ten, dec,neg,mem,num,nextOp,restart));
	}


	public void plusMinus()
	{
		restart_num();
		num = num.multiply(bd(-1.0));
		neg = neg.multiply(bd(-1.0));
		updateUI();
		DEBUG(String.format("<%s> -- ten[%s]\tdec[%s]\tneg[%s]\tmen[%s]\tnum[%s]\top[%c]\trestart[%b]",CalcUI.PLUS_MINUS, ten, dec,neg,mem,num,nextOp,restart));
	}


	public void equals()
	{
		evaluate();
		//num = mem.add(bd(0.0));
		mem = bd(0.0);
		updateUI();
		this.setOp('e');
		restart = true;
		DEBUG(String.format("<=> -- ten[%s]\tdec[%s]\tneg[%s]\tmen[%s]\tnum[%s]\top[%c]\trestart[%b]",ten, dec,neg,mem,num,nextOp,restart));
	}


	public void evaluate()
	{
		switch(nextOp){
			case '+':
				num = mem.add(num);
				break;
			case '-':
				num = mem.subtract(num);
				break;
			case '*':
				num = mem.multiply(num, MathContext.DECIMAL64);
				break;
			case '/':
				num = mem.divide(num, MathContext.DECIMAL64);
				break;
			case 'e':
				num = num;
				break;
			default:
				break;
		}
		restart = true;
		updateUI();
	}
}

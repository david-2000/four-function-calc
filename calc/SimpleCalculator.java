/**
 *	Internal system for the Calculator
 *  @author <a href="mailto:david.pineda@my.wheaton.edu">David Pineda</a>
 */
package calc;

import ui.CalcUI;

public class SimpleCalculator extends Calculator
{
	private double num;
	private double mem;
	private char nextOp = 'e';
	private int neg = 1;
	private int dec = 1;
	private int ten = 10;
	private boolean restart;


	public SimpleCalculator(CalcUI ui)
	{
		this.ui = ui;
		num = mem = 0.0;
		neg = dec = 1;
		ten = 10;
		nextOp = 'e';
	}

	
	public void addDigit(double d)
	{
		if ( restart )
			num = 0;
		restart = false;
		num *= ten;
		d /= dec;
		d *= neg;
		num += d;
		dec *= (10/ten);
		updateUI();
		System.out.printf("dc: op:%c\tnum:%f\tmem:%f\n", nextOp, num, mem);
	}


	public void clear()
	{
		num = mem = 0.0;
		neg = dec = 1;
		ten = 10;
		nextOp = 'e';
		updateUI();
	}


	private void updateUI()
	{
		ui.SetNumber(num+"");
	}


	private void setOp(char op)
	{
		nextOp = op;
		restart = true;
	}


	public void add()
	{
		System.out.printf("[+]\n");
		evaluate();
		this.setOp('+');
	}


	public void sub()
	{
		System.out.printf("[-]\n");
		evaluate();
		this.setOp('-');
	}


	public void mult()
	{
		System.out.printf("[*]\n");
		evaluate();
		this.setOp('*');
	}


	public void div()
	{
		System.out.printf("[/]\n");
		evaluate();
		this.setOp('/');
	}


	public void decimal()
	{
		dec *= 10;
		ten = 1;
		updateUI();
	}


	public void plusMinus()
	{
		num *= -1;
		neg *= -1;
		updateUI();
	}


	public void equals()
	{
		System.out.printf("[=]\n");
		evaluate();
		num = mem;
		mem = 0;
		updateUI();
		this.setOp('e');
		restart = true;
		System.out.printf("=: op:%s\tnum:%f\tmem:%f\n", nextOp, num, mem);
	}


	public void evaluate()
	{
		System.out.printf("1: op:%s\tnum:%f\tmem:%f\n", nextOp, num, mem);
		switch(nextOp){
			case '+':
				mem += num;
				num = 0;
				break;
			case '-':
				mem -= num;
				num = 0;
				break;
			case '*':
				mem *= num;
				num = 0;
				break;
			case '/':
				mem /= num;
				num = 0;
				break;
			case 'e':
				mem = num;
				num = 0;
				break;
			default:
				break;
		}
		System.out.printf("2: op:%s\tnum:%f\tmem:%f\n\n", nextOp, num, mem);
	}
}

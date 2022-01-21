/**
 * Class for running a CalcUI instance
 * @author <a href="mailto:david.pineda@my.wheaton.edu">David Pineda</a>
 */
package calc;

import ui.*;
import java.awt.event.*;
import java.util.Scanner;

public class CalcFactory
{

	public static void main(String[] args){
		char choice = '1';

		if( args.length > 0)
			choice = args[0].charAt(0);

		switch(choice)
		{
			case '1':
				setUpCalc("SimpleUI", "SimpleCalc");
				break;
			case '2':
				Calculator calc =setUpCalc("TestUI", "SimpleCalc");
				input(calc);
				break;
			default:
				break;
		}

	}


	public static Calculator setUpCalc(String uiType, String calcType){
		CalcUI ui;
		Calculator calc; 

		if (uiType.equals("SimpleUI"))
			ui = new SimpleUI();
		else if ( uiType.equals("TestUI"))
			ui = new TestUI();
		else
			ui = new TestUI();

		if (calcType.equals("SimpleCalc"))
			calc = new SimpleCalculator(ui);
		else if ( calcType.equals("NoIfs"))
			calc = new SimpleCalculator(ui);
		else
			calc = new SimpleCalculator(ui);


		for (int i = 0; i < 10; i++){
			double j = i;
			ui.SetButtonAction((char)(48+(int)i), e -> { calc.addDigit(j); });
		}

		ui.SetButtonAction('C', e -> { calc.clear(); });
		ui.SetButtonAction('+', e -> { calc.add(); });
		ui.SetButtonAction('-', e -> { calc.sub(); });
		ui.SetButtonAction('*', e -> { calc.mult(); });
		ui.SetButtonAction('/', e -> { calc.div(); });
		ui.SetButtonAction('=', e -> { calc.equals(); });
		ui.SetButtonAction('.', e -> { calc.decimal(); });
		ui.SetButtonAction(CalcUI.PLUS_MINUS, e -> { calc.plusMinus(); });
		return calc;
	}


	/**
	 * Lets you input strings to test with TESTUI interface
	 */
	public static void input(Calculator calc)
	{
		Scanner k = new Scanner(System.in);
		String opts = "0123456789-+/*.C="+CalcUI.PLUS_MINUS;
		boolean condition = true;
		while(condition)
		{
			String in = k.nextLine();

			for(int i =0; i < in.length(); i++){
				char c = in.charAt(i);
				if ( opts.indexOf(c) < 0){
					condition = false;
					break;
				}
				calc.ui.PressKey(c);


			}


		}
		System.out.println("Input is invalid. Quitting");


	}

}

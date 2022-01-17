/**
 * Class for running a CalcUI instance
 * @author <a href="mailto:david.pineda@my.wheaton.edu">David Pineda</a>
 */
package calc;

import ui.*;
import java.awt.event.*;

public class CalcFactory
{
	public static void main(String[] args){
		setUpCalc("Simple", "Simple");
	}


	public static Calculator setUpCalc(String uiType, String calcType){
		CalcUI ui = new SimpleUI();
		Calculator calc = new SimpleCalculator(ui);

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
}

/**
 * Class for running a CalcUI instance
 * @author <a href="mailto:david.pineda@my.wheaton.edu">David Pineda</a>
 */
package calc;

import ui.*;
import java.awt.event.*;

public class SetUp
{
	public static void main(String[] args){
		CalcUI ui = new SimpleUI();
	


		Calculator calc = new Calculator(ui);

		for (int i = 0; i < 10; i++){
			int j = i;
			ui.SetButtonAction((char)(48+i), e -> { calc.addDigit(j); });
		}

		ui.SetButtonAction('C', e -> { calc.clear(); });
	}
}

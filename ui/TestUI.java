/**
 * A simple rendition of the calculator User Interface. This one does not create any visible GUI components.
 * Used only for running JUNIT tests.
 * @author <a href="mailto:david.pineda@my.wheaton.edu">David Pineda</a>
 */
package ui;

// Imports
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.util.HashMap;
import java.text.DecimalFormat;
import java.math.BigDecimal;
import java.util.Iterator;


public class TestUI extends CalcUI
{
	/**
	 * ID variable for manual ActionPerformed calls.
	 */
	private int idGen;

	/**
	 * The things that are currently in display
	 */ 
	private JLabel[] display_field;
	private BigDecimal number = new BigDecimal(0.0);
	private DecimalFormat format;

	/**
	 * Map for Holding all the presable buttons.
	 */
	private HashMap<Character, JButton> buttons;



	public TestUI()
	{
		buttons = new HashMap<Character,JButton>();
		String f = "0.";
		for ( int i = 0 ; i < n-2 ; i++)
			f += "#";
		format = new DecimalFormat(f);
		SetUpDisplay();
		SetUpButtons();
		idGen = ActionEvent.ACTION_FIRST;
	}


	/**
	 * Private Method for seting up the number display.
	 */
	private void SetUpDisplay()
	{
		display_field = new JLabel[n];
		for ( int i = 0 ; i < display_field.length ; i++)
		{
			display_field[i] = new JLabel();
			display_field[i].setText("");
		}
	}


	/**
	 * Private Method for seting up the number display.
	 */
	private void SetUpButtons()
	{
		String bts ="0123456789+-*/C.="+CalcUI.PLUS_MINUS;
		for (int i = 0 ; i < bts.length() ; i++)
			buttons.put(bts.charAt(i), new JButton());
	}


	/**
	 * {@inheritdoc
	 */
	public boolean SetButtonAction(char button, ActionListener action)
	{
		if(buttons.get(button) == null)
			return false;
		buttons.get(button).addActionListener(action);
		return true;
	}


	/**
	 * {@inheritdoc}
	 */
	public String GetNumber()
	{
		return format.format(number);
	}


	/**
	 * {@inheritdoc}
	 */
	public void SetNumber(BigDecimal new_num){
		number = new_num.add(new BigDecimal(0.0));
		String num = format.format(new_num);
		int m = num.length();

		int i =0;
		for ( ; i < n-m ; i++)
			display_field[i].setText("");

		int j = 0;
		for (; i < n ; i++)
			display_field[i].setText(num.charAt(j++)+"");
	
	}

	/**
	 * {@inheritdoc}
	 */
	public void PressKey(char key)
	{
		if ( buttons.get(key) == null)
			return;
		ActionListener act = buttons.get(key).getActionListeners()[0];
		if (act == null)
			return;

		act.actionPerformed(new ActionEvent(this, idGen, ""+key, System.currentTimeMillis(), 0));
		idGen++;
	}

}

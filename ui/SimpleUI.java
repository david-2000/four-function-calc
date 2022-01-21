/**
 * A simple rendition of the calculator User Interface.
 *  @author <a href="mailto:david.pineda@my.wheaton.edu">David Pineda</a>
 */
package ui;

// Imports
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.HashMap;
import java.text.DecimalFormat;

//Temp
import javax.swing.BorderFactory;
import java.math.BigDecimal;

public class SimpleUI extends CalcUI
{
	/**
	 * ID variable for manual ActionPerformed calls.
	 */
	private int idGen;

	/**
	 * The entire Calculator App window.
	 */
	private JFrame window;

	/**
	 * Grid Layout and Constraintes used to Format it
	 */
	private GridBagLayout window_layout;
	private GridBagConstraints window_constraints;

	/**
	 * Display Panel contains the number on the screen.
	 * Each label can print one digit to the screen.
	 */
	private JPanel display_panel;
	private JLabel[] display_field;
	private BigDecimal number = new BigDecimal(0);
	private DecimalFormat format;

	/**
	 * Panels that hold pressable number buttons as well as the decimal operator
	 * and the PlusMinus Operation.
	 */
	private JPanel number_panel;

	/**
	 * Panels that hold pressable function buttons. These include:
	 * Addition, Subtraction, Division, Multiplication, Equals, and Clear.
	 */
	private JPanel function_panel;

	/**
	 * Map for Holding all the presable buttons.
	 */
	private HashMap<Character, JButton> buttons;



	public SimpleUI()
	{
		String f = "0.";
		for (int i = 0 ; i < n-2 ; i++)
			f += "#";
		format = new DecimalFormat(f);

		window = new JFrame();
		window.setSize(500,500);
		window_layout = new GridBagLayout();
		window_constraints = new GridBagConstraints();
		window_constraints.fill = GridBagConstraints.BOTH;
		window.setLayout(window_layout);
		//window.setIconImage(some_image);

		buttons = new HashMap<Character,JButton>();
		SetUpPanels();


		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		idGen = ActionEvent.ACTION_FIRST;
	}


	/**
	 * Private Method for seting up three distinct pannels: Display Number, Numbers, and Functions.
	 * Called by the constructor.
	 */
	private void SetUpPanels()
	{
		// ----- DISPLAY PANEL
		display_panel = new JPanel();
		display_panel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
		window_constraints.weightx = 1.0;
		window_constraints.weighty = 0.25;
		window_constraints.gridwidth = GridBagConstraints.REMAINDER;
		window_layout.setConstraints(display_panel, window_constraints);
		window.add(display_panel);
		SetUpDisplay();

		// ----- NUMBER PANEL
		number_panel= new JPanel();
		//number_panel.setBorder(BorderFactory.createLineBorder(Color.red));
		window_constraints.gridwidth = 1;
		window_constraints.gridheight = 2;
		window_constraints.weightx = 0.6;
		window_constraints.weighty = 1;
		window_layout.setConstraints(number_panel, window_constraints);
		window.add(number_panel);
		SetUpNumbers();

		// ----- function PANEL
		function_panel = new JPanel();
		//function_panel.setBorder(BorderFactory.createLineBorder(Color.green));
		window_constraints.weightx = 0.4;
		window_constraints.weighty = 1;
		window_layout.setConstraints(function_panel, window_constraints);
		window.add(function_panel);
		SetUpFunctions();
	}
	

	/**
	 * Private Method for seting up the number display.
	 */
	private void SetUpDisplay()
	{
		display_field = new JLabel[n];
		GridBagConstraints c = new GridBagConstraints();
		GridBagLayout g = new GridBagLayout();
		display_panel.setLayout(g);
		int font_size = 24;

		for ( int i = 0 ; i < n ; i++)
		{
			c.weighty = 1;
			c.weightx = 100.0/display_field.length;
			display_field[i] = new JLabel();
			display_field[i].setFont(new Font("Jersey M54", Font.PLAIN, font_size));
			display_field[i].setText("");
			display_panel.add(display_field[i], c);
		}
	}


	/**
	 * Private Method for seting up the number display.
	 */
	private void SetUpNumbers()
	{

		number_panel.setLayout(new GridLayout(4,3));
		JButton bt;
		Font font = new Font("Calibri", Font.PLAIN, 30);

		for (int i = 1 ; i < 10 ; i++)
		{
			char l = (char)(48 + i);
			bt = new JButton();
			bt.setText(((char) l) +"");
			buttons.put(l, bt);
			number_panel.add(bt);
			bt.setBackground(Color.PINK);
			bt.setFont(font);
		}
		
		bt = new JButton();
		bt.setText(CalcUI.PLUS_MINUS+"");
		buttons.put(CalcUI.PLUS_MINUS, bt);
		bt.setFont(font);
		number_panel.add(bt);

		bt = new JButton();
		bt.setText("0");
		buttons.put('0', bt);
		bt.setFont(font);
		number_panel.add(bt);

		bt = new JButton();
		bt.setText(".");
		buttons.put('.', bt);
		bt.setFont(font);
		number_panel.add(bt);
	}


	/**
	 * Private Method for seting up the number display.
	 */
	private void SetUpFunctions()
	{
		GridBagLayout g = new GridBagLayout();
		function_panel.setLayout(g);
		GridBagConstraints c = new GridBagConstraints();

		JButton bt = new JButton();
		bt.setText("C");
		buttons.put('C',bt);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth= 2;
		c.fill = GridBagConstraints.BOTH;
		function_panel.add(bt, c);
		
		bt = new JButton();
		bt.setText("+");
		buttons.put('+',bt);
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth= 1;
		function_panel.add(bt, c);

		bt = new JButton();
		bt.setText("-");
		buttons.put('-',bt);
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		function_panel.add(bt, c);
		
		bt = new JButton();
		bt.setText("*");
		buttons.put('*',bt);
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		function_panel.add(bt, c);

		bt = new JButton();
		bt.setText("/");
		buttons.put('/',bt);
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		function_panel.add(bt, c);

		bt = new JButton();
		bt.setText("=");
		buttons.put('=',bt);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth= 2;
		c.fill = GridBagConstraints.BOTH;
		function_panel.add(bt, c);
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
		String num = format.format(number);
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
		if (buttons.get(key) == null)
			return;
		ActionListener act = buttons.get(key).getActionListeners()[0];
		if (act == null)
			return;

		act.actionPerformed(new ActionEvent(this, idGen, ""+key, System.currentTimeMillis(), 0));
		idGen++;
	}

}

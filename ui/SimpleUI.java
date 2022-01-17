/**
 * A simple rendition of the calculator User Interface.
 *  @author <a href="mailto:david.pineda@my.wheaton.edu">David Pineda</a>
 */
package ui;

// Imports
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.HashMap;

//Temp
import javax.swing.BorderFactory;

public class SimpleUI implements CalcUI
{
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
		window = new JFrame();
		window.setSize(500,500);
		window_layout = new GridBagLayout();
		window_constraints = new GridBagConstraints();
		window_constraints.fill = GridBagConstraints.BOTH;
		window.setLayout(window_layout);
		//window.setIconImage(some_image);

		SetUpPanels();


		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
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
		display_field = new JLabel[20];
		GridBagConstraints c = new GridBagConstraints();
		GridBagLayout g = new GridBagLayout();
		display_panel.setLayout(g);
		int font_size = 24;

		for ( int i = 0 ; i < display_field.length ; i++)
		{
			c.weighty = 1;
			c.weightx = 100.0/display_field.length;
			display_field[i] = new JLabel();
			display_field[i].setFont(new Font("Jersey M54", Font.PLAIN, font_size));
			display_field[i].setText("");
			//display_field[i].setBorder(BorderFactory.createLineBorder(Color.black));
			display_panel.add(display_field[i], c);
		}
	}


	/**
	 * Private Method for seting up the number display.
	 */
	private void SetUpNumbers()
	{
		buttons = new HashMap<Character,JButton>();

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
		String ret = "";
		for (int i = 0; i < display_field.length ; i++)
			ret += display_field[i].getText();
		return ret;
	}


	/**
	 * {@inheritdoc}
	 */
	public void SetNumber(String num){
		if(num.length() > 20)
			num = num.substring(0,20);
		int n = display_field.length;
		int m = num.length();

		int i =0;
		for ( ; i < n-m ; i++)
			display_field[i].setText("");

		int j = 0;
		for (; i < n ; i++)
			display_field[i].setText(num.charAt(j++)+"");
	
	}

}

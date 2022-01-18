package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import ui.*;
import calc.*;

public class TestJUnit {
	private char PM = CalcUI.PLUS_MINUS;
	private static void testSequence(String seq, String[] expect)
	{
		Calculator calc = CalcFactory.setUpCalc("TestUI", "SimpleCalc");
		CalcUI ui = calc.ui;
		for (int i = 0 ; i < seq.length() ; i++)
			ui.PressKey(seq.charAt(i));

		boolean found = false;
		String actual = calc.ui.GetNumber();
		String message = "Output ["+actual+"] not in expected set { ";
		for(String x: expect){
			message += x +",";
			found |= x.equals(actual);
		}
		message = message.substring(0,message.length()-1) +" }";

		assertTrue(message, found);
	}

	/*
	 * -------------------------------------------------------------------------------------------------------------
	 *  				Tests For Number Building
	 * -------------------------------------------------------------------------------------------------------------
	 */
	@Test
	public void testDigits() {
		TestJUnit.testSequence("80192", new String[] {"80192", "80192.0"});
		TestJUnit.testSequence("1234567890", new String[] {"1234567890"});
		for(int i = 0; i < 10; i++)
			TestJUnit.testSequence(((char)48+i)+"", new String[] {((char)48+i)+""});
		for(int i = 0; i < 10; i++)
			TestJUnit.testSequence("0"+((char)48+i), new String[] {((char)48+i)+""});
		for(int i = 0; i < 10; i++)
			TestJUnit.testSequence(((char)48+i)+"0", new String[] {((char)48+i)+"0"});
	}


	/*
	 * -------------------------------------------------------------------------------------------------------------
	 *  				Tests For Decimal Building
	 * -------------------------------------------------------------------------------------------------------------
	 */
	@Test
	public void testDec(){
		TestJUnit.testSequence("1.2", new String[] { "1.2"});
		TestJUnit.testSequence("1.2213", new String[] { "1.2213"});
		TestJUnit.testSequence("121.2213", new String[] { "121.2213"});
	}

	@Test
	public void testMultDec(){
		TestJUnit.testSequence("1.2.", new String[] { "1.2"});
		TestJUnit.testSequence("1.221.3", new String[] { "1.2213"});
		TestJUnit.testSequence("121.2.213.", new String[] { "121.2213"});
		TestJUnit.testSequence("1.2.", new String[] { "1.2"});
		TestJUnit.testSequence("1.2...21.3", new String[] { "1.2213"});
		TestJUnit.testSequence("121.2.213.", new String[] { "121.2213"});
	}

	/*
	 * -------------------------------------------------------------------------------------------------------------
	 *  				Tests For PlusMinus Building
	 * -------------------------------------------------------------------------------------------------------------
	 */
	@Test
	public void testNegPre(){
		TestJUnit.testSequence(PM+"1", new String[] { "-1"});
		TestJUnit.testSequence(PM+"112", new String[] { "-112"});
	}

	@Test
	public void testNegMid(){
		TestJUnit.testSequence("2"+PM+"1", new String[] { "-21"});
		TestJUnit.testSequence("94"+PM+"112", new String[] { "-94112"});
	}

	@Test
	public void testNegPost(){
		TestJUnit.testSequence("2124"+PM, new String[] { "-2124"});
		TestJUnit.testSequence("9464"+PM, new String[] { "-9464"});
	}

	@Test
	public void testNegNegPre(){
		TestJUnit.testSequence(PM+""+PM+"1234", new String[] { "1234"});
		TestJUnit.testSequence(PM+""+PM+"134690", new String[] { "134690"});
	}

	@Test
	public void testNegMidNeg(){
		TestJUnit.testSequence(PM+"123"+PM+"1234", new String[] { "1231234"});
		TestJUnit.testSequence(PM+"345"+PM+"134690", new String[] { "345134690"});
	}

	@Test
	public void testNegPostNeg(){
		TestJUnit.testSequence(PM+"1231234"+PM, new String[] { "1231234"});
		TestJUnit.testSequence(PM+"345134690"+PM, new String[] { "345134690"});
	}

	@Test
	public void testNegNegNegPre(){
		TestJUnit.testSequence(PM+""+PM+""+PM+"1234", new String[] { "-1234"});
		TestJUnit.testSequence(PM+""+PM+""+PM+"134690", new String[] { "-134690"});
	}

	@Test
	public void testNegMidNegNeg(){
		TestJUnit.testSequence(PM+"123"+PM+"1234"+PM, new String[] { "-1231234"});
		TestJUnit.testSequence(PM+"345"+PM+"134690"+PM, new String[] { "-345134690"});
	}

	@Test
	public void testNegPostNegNeg(){
		TestJUnit.testSequence(PM+"1231234"+PM+""+PM, new String[] { "-1231234"});
		TestJUnit.testSequence(PM+"345134690"+PM+""+PM, new String[] { "-345134690"});
	}
}

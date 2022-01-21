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
		message = message.substring(0,message.length()-1) +" } for sequence["+seq+"]" ;

		assertTrue(message, found);
	}

	private static void testSequenceApprox(String seq, String[] expect)
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
			found |= actual.indexOf(x)==0;
		}
		message = message.substring(0,message.length()-1) +" } for sequence["+seq+"]" ;

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
	public void testRepeatDec(){
		TestJUnit.testSequence("1.2.", new String[] { "1.2"});
		TestJUnit.testSequence("1.221.3", new String[] { "1.2213"});
		TestJUnit.testSequence("121.2.213.", new String[] { "121.2213"});
		TestJUnit.testSequence("1.2.", new String[] { "1.2"});
		TestJUnit.testSequence("1.2...21.3", new String[] { "1.2213"});
		TestJUnit.testSequence("121.2.213.", new String[] { "121.2213"});
	}

	@Test
	public void testFirsttDec(){
		TestJUnit.testSequence(".12", new String[] { "0.12"});
		TestJUnit.testSequence(".12213", new String[] { "0.12213"});
		TestJUnit.testSequence(".1212213", new String[] { "0.1212213"});
		TestJUnit.testSequence(".12", new String[] { "0.12"});
		TestJUnit.testSequence(".12213", new String[] { "0.12213"});
		TestJUnit.testSequence(".1212213", new String[] { "0.1212213"});
	}

	@Test
	public void testFirsttDecRepeat(){
		TestJUnit.testSequence("..1.2", new String[] { "0.12"});
		TestJUnit.testSequence(".12..2.1.3.", new String[] { "0.12213"});
		TestJUnit.testSequence(".....1212213", new String[] { "0.1212213"});
		TestJUnit.testSequence(".12.", new String[] { "0.12"});
		TestJUnit.testSequence(".12..2.13", new String[] { "0.12213"});
		TestJUnit.testSequence(".1.2.1.2.2..1...3", new String[] { "0.1212213"});
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

	/*
	 * -------------------------------------------------------------------------------------------------------------
	 *  				Tests For PlusMinus with Decimal Number Building
	 * -------------------------------------------------------------------------------------------------------------
	 */
	@Test
	public void testDecNegPre(){
		TestJUnit.testSequence(PM+".1", new String[] { "-0.1"});
		TestJUnit.testSequence(PM+"1.12", new String[] { "-1.12"});
	}

	@Test
	public void testDecNegMid(){
		TestJUnit.testSequence(".02"+PM+"1", new String[] { "-0.021"});
		TestJUnit.testSequence("9.4"+PM+"112", new String[] { "-9.4112"});
	}

	@Test
	public void testDecNegPost(){
		TestJUnit.testSequence("21.24"+PM, new String[] { "-21.24"});
		TestJUnit.testSequence("946.4"+PM, new String[] { "-946.4"});
	}

	@Test
	public void testDecNegNegPre(){
		TestJUnit.testSequence(PM+"."+PM+"1234", new String[] { "0.1234"});
		TestJUnit.testSequence(PM+"."+PM+"134690", new String[] { "0.13469"});
	}

	@Test
	public void testDecNegMidNeg(){
		TestJUnit.testSequence(PM+"123"+PM+".1234", new String[] { "123.1234"});
		TestJUnit.testSequence(PM+"345"+PM+".134690", new String[] { "345.13469"});
	}

	@Test
	public void testDecNegPostNeg(){
		TestJUnit.testSequence(PM+"123123.4"+PM, new String[] { "123123.4"});
		TestJUnit.testSequence(PM+"3451346.90"+PM, new String[] { "3451346.9"});
	}

	@Test
	public void testDecNegNegNegPre(){
		TestJUnit.testSequence(PM+""+PM+""+PM+"12.34", new String[] { "-12.34"});
		TestJUnit.testSequence(PM+""+PM+""+PM+"134690.", new String[] { "-134690"});
	}

	@Test
	public void testDecNegMidNegNeg(){
		TestJUnit.testSequence(PM+"123"+PM+"12.34"+PM, new String[] { "-12312.34"});
		TestJUnit.testSequence(PM+"345"+PM+"1346.90"+PM, new String[] { "-3451346.9"});
	}

	@Test
	public void testDecNegPostNegNeg(){
		TestJUnit.testSequence(PM+".1231234"+PM+""+PM, new String[] { "-0.1231234"});
		TestJUnit.testSequence(PM+".345134690"+PM+""+PM, new String[] { "-0.34513469"});
	}

	/*
	 * -------------------------------------------------------------------------------------------------------------
	 *  				Tests For Addition
	 * -------------------------------------------------------------------------------------------------------------
	 */
	@Test
	public void testAddEq(){
		TestJUnit.testSequence("2+1=", new String[] { "3"});
		TestJUnit.testSequence("21+100=", new String[] { "121"});
	}

	@Test
	public void testDecAddEq(){
		TestJUnit.testSequence(".2+1=", new String[] { "1.2"});
		TestJUnit.testSequence("2+.1=", new String[] { "2.1"});
		TestJUnit.testSequence(".2+.1=", new String[] { "0.3"});
		TestJUnit.testSequence("21+100.12=", new String[] { "121.12"});
		TestJUnit.testSequence("21.12+100=", new String[] { "121.12"});
		TestJUnit.testSequence("21.12+100.12=", new String[] { "121.24"});
	}

	@Test
	public void testAddAddEq(){
		TestJUnit.testSequence("2+1+5=", new String[] { "8"});
		TestJUnit.testSequence("12+54+42=", new String[] { "108"});
		TestJUnit.testSequence("21+221+21=", new String[] { "263"});
	}

	@Test
	public void testDecAddAddEq(){
		TestJUnit.testSequence(".2+1+5=", new String[] { "6.2"});
		TestJUnit.testSequence("2+.1+5=", new String[] { "7.1"});
		TestJUnit.testSequence("2+1+.5=", new String[] { "3.5"});
		TestJUnit.testSequence("12+.54+42=", new String[] { "54.54"});
		TestJUnit.testSequence("21+2.21+21=", new String[] { "44.21"});
	}


	@Test
	public void testAdd(){
		TestJUnit.testSequence("2+", new String[] { "2"});
		TestJUnit.testSequence("2+1", new String[] { "1"});
		TestJUnit.testSequence("12+54", new String[] { "54"});
		TestJUnit.testSequence("11+21+12", new String[] { "12"});
	}

	@Test
	public void testDecAdd(){
		TestJUnit.testSequence(".2+1", new String[] { "1"});
		TestJUnit.testSequence("12+5.4", new String[] { "5.4"});
		TestJUnit.testSequence("21+21.", new String[] { "21", "21.0"});
	}

	@Test
	public void testAddAdd(){
		TestJUnit.testSequence("2+1+", new String[] { "3"});
		TestJUnit.testSequence("2+21+", new String[] { "23"});
		TestJUnit.testSequence("2+21+3+", new String[] { "26"});
	}

	@Test
	public void testDecAddAdd(){
		TestJUnit.testSequence(".2+1+5+", new String[] { "6.2"});
		TestJUnit.testSequence("2+.1+5+", new String[] { "7.1"});
		TestJUnit.testSequence("2+1+.5+", new String[] { "3.5"});
		TestJUnit.testSequence("12+.54+42+", new String[] { "54.54"});
		TestJUnit.testSequence("21+2.21+21+", new String[] { "44.21"});
	}


	/*
	 * -------------------------------------------------------------------------------------------------------------
	 *  				Tests For Subtraction
	 * -------------------------------------------------------------------------------------------------------------
	 */
	@Test
	public void testSubEq(){
		TestJUnit.testSequence("2-1=", new String[] { "1"});
		TestJUnit.testSequence("21-100=", new String[] { "-79"});
	}

	@Test
	public void testDecSubEq(){
		TestJUnit.testSequence(".2-1=", new String[] { "-0.8"});
		TestJUnit.testSequence("2-.1=", new String[] { "1.9"});
		TestJUnit.testSequence(".2-.1=", new String[] { "0.1"});
		TestJUnit.testSequence("21-100.12=", new String[] { "-79.12"});
		TestJUnit.testSequence("21.12-100=", new String[] { "-78.88"});
		TestJUnit.testSequence("21.12-100.12=", new String[] { "-79"});
	}

	@Test
	public void testSubSubEq(){
		TestJUnit.testSequence("2-1-5=", new String[] { "-4"});
		TestJUnit.testSequence("12-54-42=", new String[] { "-84"});
		TestJUnit.testSequence("21-221-21=", new String[] { "-221"});
	}

	@Test
	public void testDecSubSubEq(){
		TestJUnit.testSequence(".2-1-5=", new String[] { "-5.8"});
		TestJUnit.testSequence("2-.1-5=", new String[] { "-3.1"});
		TestJUnit.testSequence("2-1-.5=", new String[] { "0.5"});
		TestJUnit.testSequence("12-.54-42=", new String[] { "-30.54"});
		TestJUnit.testSequence("21-2.21-21=", new String[] { "-2.21"});
	}


	@Test
	public void testSub(){
		TestJUnit.testSequence("2-", new String[] { "2"});
		TestJUnit.testSequence("2-1", new String[] { "1"});
		TestJUnit.testSequence("12-54", new String[] { "54"});
		TestJUnit.testSequence("11-21-12", new String[] { "12"});
	}

	@Test
	public void testDecSub(){
		TestJUnit.testSequence(".2-1", new String[] { "1"});
		TestJUnit.testSequence("12-5.4", new String[] { "5.4"});
		TestJUnit.testSequence("21-21.", new String[] { "21", "21.0"});
	}

	@Test
	public void testSubSub(){
		TestJUnit.testSequence("2-1-", new String[] { "1"});
		TestJUnit.testSequence("2-21-", new String[] { "-19"});
		TestJUnit.testSequence("2-21-3-", new String[] { "-22"});
	}

	@Test
	public void testDecSubSub(){
		TestJUnit.testSequence(".2-1-5-", new String[] { "-5.8"});
		TestJUnit.testSequence("2-.1-5-", new String[] { "-3.1"});
		TestJUnit.testSequence("2-1-.5-", new String[] { "0.5"});
		TestJUnit.testSequence("12-.54-42-", new String[] { "-30.54"});
		TestJUnit.testSequence("21-2.21-21-", new String[] { "-2.21"});
	}



	/*
	 * -------------------------------------------------------------------------------------------------------------
	 *  				Tests For Multiplication
	 * -------------------------------------------------------------------------------------------------------------
	 */
	@Test
	public void testMulEq(){
		TestJUnit.testSequence("2*1=", new String[] { "2"});
		TestJUnit.testSequence("21*100=", new String[] { "2100"});
	}

	@Test
	public void testDecMulEq(){
		TestJUnit.testSequence(".2*1=", new String[] { "0.2"});
		TestJUnit.testSequence("2*.1=", new String[] { "0.2"});
		TestJUnit.testSequence(".2*.1=", new String[] { "0.02"});
		TestJUnit.testSequence("21*100.12=", new String[] { "2102.52"});
		TestJUnit.testSequence("21.12*100=", new String[] { "2112"});
		TestJUnit.testSequence("21.12*100.12=", new String[] { "2114.5344"});
	}

	@Test
	public void testMulMulEq(){
		TestJUnit.testSequence("2*1*5=", new String[] { "10"});
		TestJUnit.testSequence("12*54*42=", new String[] { "27216"});
		TestJUnit.testSequence("21*221*21=", new String[] { "97461"});
	}

	@Test
	public void testDecMulMulEq(){
		TestJUnit.testSequence(".2*1*5=", new String[] { "1"});
		TestJUnit.testSequence("2*.1*6=", new String[] { "1.2"});
		TestJUnit.testSequence("2*3*.5=", new String[] { "3"});
		TestJUnit.testSequence("12*.54*42=", new String[] { "272.16"});
		TestJUnit.testSequence("21*2.21*21=", new String[] { "974.61"});
	}


	@Test
	public void testMul(){
		TestJUnit.testSequence("2*", new String[] { "2"});
		TestJUnit.testSequence("2*1", new String[] { "1"});
		TestJUnit.testSequence("12*54", new String[] { "54"});
		TestJUnit.testSequence("11*21*12", new String[] { "12"});
	}

	@Test
	public void testDecMul(){
		TestJUnit.testSequence(".2*1", new String[] { "1"});
		TestJUnit.testSequence("12*5.4", new String[] { "5.4"});
		TestJUnit.testSequence("21*21.", new String[] { "21", "21.0"});
	}

	@Test
	public void testMulMul(){
		TestJUnit.testSequence("2*1*", new String[] { "2"});
		TestJUnit.testSequence("2*21*", new String[] { "42"});
		TestJUnit.testSequence("2*21*3*", new String[] { "126"});
	}

	@Test
	public void testDecMulMul(){
		TestJUnit.testSequence(".2*1*5*", new String[] { "1"});
		TestJUnit.testSequence("2*.1*5*", new String[] { "1"});
		TestJUnit.testSequence("2*1*.6*", new String[] { "1.2"});
		TestJUnit.testSequence("12*.54*42*", new String[] { "272.16"});
		TestJUnit.testSequence("21*2.21*21*", new String[] { "974.61"});
	}

	/*
	 * -------------------------------------------------------------------------------------------------------------
	 *  				Tests For Division
	 * -------------------------------------------------------------------------------------------------------------
	 */
	@Test
	public void testDivEq(){
		TestJUnit.testSequence("2/1=", new String[] { "2"});
		TestJUnit.testSequence("21/100=", new String[] { "0.21"});
	}

	@Test
	public void testDecDivEq(){
		TestJUnit.testSequence(".2/1=", new String[] { "0.2"});
		TestJUnit.testSequence("2/.1=", new String[] { "20"});
		TestJUnit.testSequence(".2/.1=", new String[] { "2"});
		TestJUnit.testSequenceApprox("21/100.12=", new String[] { "0.2097483"});
		TestJUnit.testSequence("21.12/100=", new String[] { "0.2112"});
		TestJUnit.testSequence("6.12/.12=", new String[] { "51"});
		TestJUnit.testSequence("6.12/6.12=", new String[] { "1"});
	}

	@Test
	public void testDivDivEq(){
		TestJUnit.testSequence("2/1/5=", new String[] { "0.4"});
		TestJUnit.testSequence("12/4/6=", new String[] { "0.5"});
		TestJUnit.testSequenceApprox("210/21/1.15=", new String[] { "8.69565217"});
	}

	@Test
	public void testDecDivDivEq(){
		TestJUnit.testSequence(".2/1/5=", new String[] { "0.04"});
		TestJUnit.testSequence("2/.1/16=", new String[] { "1.25"});
		TestJUnit.testSequence("2/5/.5=", new String[] { "0.8"});
		TestJUnit.testSequenceApprox("12/.23/.5=", new String[] { "104.347826"});
		TestJUnit.testSequenceApprox("21/2.21/21=", new String[] { "0.4524886"});
	}


	@Test
	public void testDiv(){
		TestJUnit.testSequence("2/", new String[] { "2"});
		TestJUnit.testSequence("2/1", new String[] { "1"});
		TestJUnit.testSequence("12/54", new String[] { "54"});
		TestJUnit.testSequence("11/21/12", new String[] { "12"});
	}

	@Test
	public void testDecDiv(){
		TestJUnit.testSequence(".2/1", new String[] { "1"});
		TestJUnit.testSequence("12/5.4", new String[] { "5.4"});
		TestJUnit.testSequence("21/21.", new String[] { "21", "21.0"});
	}

	@Test
	public void testDivDiv(){
		TestJUnit.testSequence("2/1/", new String[] { "2"});
		TestJUnit.testSequenceApprox("2/21/", new String[] { "0.095238"});
		TestJUnit.testSequenceApprox("2/21/3/", new String[] { "0.0317460"});
	}

	@Test
	public void testDecDivDiv(){
		TestJUnit.testSequence(".2/1/5/", new String[] { "0.04"});
		TestJUnit.testSequence("2/.1/5/", new String[] { "4"});
		TestJUnit.testSequenceApprox("2/1/.35/", new String[] { "5.71428571"});
		TestJUnit.testSequenceApprox("12/.54/42/", new String[] { "0.5291005"});
		TestJUnit.testSequenceApprox("21/2.21/21/", new String[] { "0.4524886"});
	}


}

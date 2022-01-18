package ui;
import java.util.Scanner;
import java.math.BigDecimal;
public class Main
{
	public static void main(String[] args){

		Scanner key = new Scanner(System.in);
		CalcUI ui = new SimpleUI();
		ui.SetNumber(new BigDecimal(312.9));
	}

}

package calcuator;

import javax.swing.JOptionPane;

public class CalculatorEngine {

	public boolean canPrint = true;

	// Addition logic
	public double AddNumbers(double number1, double number2) {
		return number1 + number2;
	}

	// Subraction Logic
	public double SubractNumbers(double number1, double number2) {
		return number1 - number2;
	}

	// Multiplication logic
	public double MultiplyNumbers(double number1, double number2) {
		return number1 * number2;
	}

	// Division Logic
	public double DivideNumbers(double number1, double number2) {
		if (number2 == 0) {
			JOptionPane.showMessageDialog(null, "cannot divide by 0");
			canPrint = false;
			return 0;
		}
		return number1 / number2;
	}

}

package calcuator;

import javax.swing.JOptionPane;

public class CalculatorValidator extends CalculatorEngine {

	double number1 = 0;
	double number2 = 0;
	String input;

	double result = 0.0;

	boolean continueCalculation = false;
	boolean isValid;
	

	//Takes Input from user
	public void TakeInput() {

		do {
			canPrint = true;

			// take the input for first numnber
			input = JOptionPane.showInputDialog(null, "please give the first number");
			if (input == null) {
				return;
			}

			try {
				number1 = Double.parseDouble(input);
				isValid = true;
			} catch (NumberFormatException e) {
				isValid = false;
			}

			while (!isValid) {
				input = JOptionPane.showInputDialog(null, "please give a number not alphabets or any other symbol");
				try {
					number1 = Double.parseDouble(input);
					isValid = true;
				} catch (NumberFormatException e) {
					isValid = false;
				}
			}

			// take input form second number
			input = JOptionPane.showInputDialog(null, "please give the second number");
			if (input == null) {
				return;
			}

			try {
				number2 = Double.parseDouble(input);
				isValid = true;
			} catch (NumberFormatException e) {
				isValid = false;
			}

			while (!isValid) {
				input = JOptionPane.showInputDialog(null, "please give a number not alphabets or any other symbol");
				try {
					number2 = Double.parseDouble(input);
					isValid = true;
				} catch (NumberFormatException e) {
					isValid = false;
				}
			}

			// make the user to select a option from the list of enums
			OperationType operation = (OperationType) JOptionPane.showInputDialog(null, "Select an operation",
					"Calculator", JOptionPane.QUESTION_MESSAGE, null, OperationType.values(), OperationType.ADDITION);

			if (operation == null) {
				return;
			}
			
			ValidateInput(operation);

		} while (continueCalculation);
	}

	//Validates input 
	public void ValidateInput(OperationType operation) {

		// check the type of operation user want to perform
		switch (operation) {
		case OperationType.ADDITION:
			result = AddNumbers(number1, number2);
			break;
		case OperationType.SUBRACTION:
			result = SubractNumbers(number1, number2);
			break;
		case OperationType.MULTIPLICATION:
			result = MultiplyNumbers(number1, number2);
			break;
		case OperationType.DIVISION:
			result = DivideNumbers(number1, number2);
			break;
		default:
			break;
		}

		if (canPrint) {
			JOptionPane.showMessageDialog(null, result);
		}

		int choice = JOptionPane.showConfirmDialog(null, "Do you want to continue calculation?", "Confirmation",
				JOptionPane.YES_NO_OPTION);

		if (choice == JOptionPane.YES_OPTION) {
			continueCalculation = true;
		} else {
			continueCalculation = false;
		}
	}

}

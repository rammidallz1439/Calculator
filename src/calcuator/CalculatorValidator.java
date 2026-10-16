
package calcuator;

import javax.swing.JOptionPane;

public class CalculatorValidator {

    private double number1 = 0;
    private double number2 = 0;
    private String input;

    private double result = 0.0;
    private boolean continueCalculation = false;

    private final CalculatorEngine engine = new CalculatorEngine();

    // Takes input from the user
    public void TakeInput() {

        do {

            // Get the first number
            input = JOptionPane.showInputDialog(
                    null, "Please give the first number");

            if (input == null) {
                return;
            }

            if (!IsValidNumber(input)) {
                JOptionPane.showMessageDialog(
                        null, "Please enter a valid number.");
                continue;
            }

            number1 = Double.parseDouble(input);

            // Get the second number
            input = JOptionPane.showInputDialog(
                    null, "Please give the second number");

            if (input == null) {
                return;
            }

            if (!IsValidNumber(input)) {
                JOptionPane.showMessageDialog(
                        null, "Please enter a valid number.");
                continue;
            }

            number2 = Double.parseDouble(input);

            // Select an operation
            OperationType operation = (OperationType)
                    JOptionPane.showInputDialog(
                            null,
                            "Select an operation",
                            "Calculator",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            OperationType.values(),
                            OperationType.ADDITION);

            if (operation == null) {
                return;
            }

            ValidateInput(operation);

        } while (continueCalculation);
    }

    // Checks whether the input can be converted into a number
    private boolean IsValidNumber(String input) {

        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validates the operation and displays the result
    public void ValidateInput(OperationType operation) {

        String operator;

        switch (operation) {

            case ADDITION:
                operator = "+";
                break;

            case SUBRACTION:
                operator = "-";
                break;

            case MULTIPLICATION:
                operator = "×";
                break;

            case DIVISION:
                operator = "÷";
                break;

            default:
                return;
        }

        try {

            result = engine.Calculate(number1, number2, operator);

            JOptionPane.showMessageDialog(null, result);

        } catch (ArithmeticException e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        int choice = JOptionPane.showConfirmDialog(
                null,
                "Do you want to continue calculation?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);

        continueCalculation = (choice == JOptionPane.YES_OPTION);
    }
}

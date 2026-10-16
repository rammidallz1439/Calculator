
package calcuator;

public class CalculatorEngine {

    public double Calculate(double number1, double number2, String operator) {

        switch (operator) {

            case "+":
                return number1 + number2;

            case "-":
                return number1 - number2;

            case "×":
                return number1 * number2;

            case "÷":
                if (number2 == 0) {
                    throw new ArithmeticException("Cannot divide by zero!");
                }
                return number1 / number2;

            case "%":
                if (number2 == 0) {
                    throw new ArithmeticException("Cannot calculate remainder with zero!");
                }
                return number1 % number2;

            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}

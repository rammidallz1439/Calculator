
package calcuator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CalculatorUI {

    private JTextField display;
    private CalculatorEngine engine = new CalculatorEngine();

    private double firstOperand = 0;
    private String pendingOperator = "";
    private boolean startNewNumber = true;

    public void CreateTheFrame() {

        JFrame frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 550);
        frame.setMinimumSize(new Dimension(320, 450));
        frame.setLayout(new BorderLayout(8, 8));

        // Display
        display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.PLAIN, 32));
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        display.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(
                javax.swing.BorderFactory.createEmptyBorder(10, 10, 0, 10));
        displayPanel.add(display, BorderLayout.CENTER);

        // Button grid: 5 rows, 4 columns
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 6, 6));
        buttonPanel.setBorder(
                javax.swing.BorderFactory.createEmptyBorder(5, 10, 10, 10));

        String[] buttonLabels = {
            "C", "%", "÷", "×",
            "7", "8", "9", "-",
            "4", "5", "6", "+",
            "1", "2", "3", "=",
            "0", ".", "±", "⌫"
        };

        // Create buttons and attach the same listener
        for (String label : buttonLabels) {

            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setFocusable(false);

            button.addActionListener(e ->
                HandleButtonClick(e.getActionCommand())
            );

            buttonPanel.add(button);
        }

        frame.add(displayPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Handle every button through one method
    private void HandleButtonClick(String command) {

        // Number buttons
        if (command.matches("[0-9]")) {

            if (startNewNumber || display.getText().equals("0")) {
                display.setText(command);
            } else {
                display.setText(display.getText() + command);
            }

            startNewNumber = false;
            return;
        }

        // Decimal point
        if (command.equals(".")) {

            if (startNewNumber) {
                display.setText("0.");
                startNewNumber = false;
            } else if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }

            return;
        }

        // Clear everything
        if (command.equals("C")) {
            firstOperand = 0;
            pendingOperator = "";
            display.setText("0");
            startNewNumber = true;
            return;
        }

        // Change the sign
        if (command.equals("±")) {

            if (!display.getText().equals("0")) {
                double value = Double.parseDouble(display.getText());
                display.setText(FormatResult(-value));
            }

            return;
        }

        // Delete the last digit
        if (command.equals("⌫")) {

            if (!startNewNumber) {
                String text = display.getText();

                if (text.length() <= 1
                        || (text.length() == 2 && text.startsWith("-"))) {
                    display.setText("0");
                } else {
                    display.setText(text.substring(0, text.length() - 1));
                }
            }

            return;
        }

        // Calculate the result
        if (command.equals("=")) {

            if (!pendingOperator.isEmpty() && !startNewNumber) {
                Calculate();
            }

            return;
        }

        // Arithmetic operators
        if (command.equals("+") || command.equals("-")
                || command.equals("×") || command.equals("÷")
                || command.equals("%")) {

            if (!pendingOperator.isEmpty() && !startNewNumber) {
                Calculate();
            }

            firstOperand = Double.parseDouble(display.getText());
            pendingOperator = command;
            startNewNumber = true;
        }
    }


private void Calculate() {

    double secondOperand = Double.parseDouble(display.getText());

    try {
        double result = engine.Calculate(
            firstOperand,
            secondOperand,
            pendingOperator
        );

        display.setText(FormatResult(result));

        firstOperand = result;
        pendingOperator = "";
        startNewNumber = true;

    } catch (ArithmeticException e) {

        JOptionPane.showMessageDialog(null, e.getMessage());

        display.setText("0");
        firstOperand = 0;
        pendingOperator = "";
        startNewNumber = true;
    }
}

    // Avoid displaying 5.0 when the result is a whole number
    private String FormatResult(double value) {

        if (Double.isFinite(value)
                && value == Math.rint(value)
                && Math.abs(value) < 1.0e16) {

            return String.valueOf((long) value);
        }

        return String.valueOf(value);
    }
}

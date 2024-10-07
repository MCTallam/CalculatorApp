package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private String currentDisplay = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        View.OnClickListener listener = v -> {
            Button button = (Button) v;
            String buttonText = button.getText().toString();

            if (isOperator(buttonText)) {
                if (!isOperatorPressed) {
                    firstNumber = Double.parseDouble(currentDisplay);
                    operator = buttonText;
                    isOperatorPressed = true;
                    currentDisplay = "";
                }
            } else if (buttonText.equals("=")) {
                calculateResult();
                isOperatorPressed = false;
            } else if (buttonText.equals("C")) {
                clearDisplay();
            } else if (buttonText.equals("⌫")) {
                backspace();
            } else {
                currentDisplay += buttonText;
                display.setText(currentDisplay);
            }
        };

        // Assign buttons to the listener
        int[] buttonIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide, R.id.btnDot, R.id.btnEquals, R.id.btnClear, R.id.btnBackspace};

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private boolean isOperator(String buttonText) {
        return buttonText.equals("+") || buttonText.equals("-") || buttonText.equals("×") || buttonText.equals("÷");
    }

    private void calculateResult() {
        double secondNumber = Double.parseDouble(currentDisplay);
        double result = 0;

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "×":
                result = firstNumber * secondNumber;
                break;
            case "÷":
                result = firstNumber / secondNumber;
                break;
        }

        currentDisplay = String.valueOf(result);
        display.setText(currentDisplay);
    }

    private void clearDisplay() {
        currentDisplay = "";
        firstNumber = 0;
        isOperatorPressed = false;
        display.setText("0");
    }

    private void backspace() {
        if (currentDisplay.length() > 0) {
            currentDisplay = currentDisplay.substring(0, currentDisplay.length() - 1);
            display.setText(currentDisplay);
        }
    }
}
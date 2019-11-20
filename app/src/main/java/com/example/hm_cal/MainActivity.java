package com.example.hm_cal;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import javax.script.*;

public class MainActivity extends AppCompatActivity {

    // The equation being built by pressing keys
    public StringBuilder equation = new StringBuilder();

    // The boi evaluating the String expressions
    public ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");

    // Toggle boolean between can and can't type
    public boolean canType = true;

    /**
     * Starts the app and determines which screen the app starts on.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Checks if the equation is within the 10 character limit.
     * @param equation string builder to be checked.
     */
    public void isLength(StringBuilder equation) {
        if (equation.length() > 10) {
            canType = false;
        }
    }

    /**
     * Adds button text to equation text box
     * @param view page being handled
     */
    public void btnPress(View view){
        this.isLength(equation);
        if (!canType) {
            return;
        }

        switch (view.getId()) {
            case R.id.btn_0:
                equation.append("0");
                break;
            case R.id.btn_1:
                equation.append("1");
                break;
            case R.id.btn_2:
                equation.append("2");
                break;
            case R.id.btn_3:
                equation.append("3");
                break;
            case R.id.btn_4:
                equation.append("4");
                break;
            case R.id.btn_5:
                equation.append("5");
                break;
            case R.id.btn_6:
                equation.append("6");
                break;
            case R.id.btn_7:
                equation.append("7");
                break;
            case R.id.btn_8:
                equation.append("8");
                break;
            case R.id.btn_9:
                equation.append("9");
                break;
            case R.id.btn_decimal:
                equation.append(".");
                break;
            case R.id.btn_plus:
                equation.append("+");
                break;
            case R.id.btn_subtract:
                equation.append("-");
                break;
            case R.id.btn_multiply:
                equation.append("×");
                break;
            case R.id.btn_divide:
                equation.append("÷");
                break;
        }

        TextView answer = findViewById(R.id.textView);
        answer.setText(equation.toString());
    }

    /**
     * Clears the display box
     * @param view page being handled
     */
    public void allClear(View view) {
        equation.setLength(0);
        TextView answer = findViewById(R.id.textView);
        answer.setText(equation.toString());
        canType = true;
    }

    /**
     * Deletes last character in the display box
     * @param view page being handled
     */
    public void backSpace(View view) {
        if (equation.length() > 0 && canType) {
            equation.deleteCharAt(equation.length() - 1);
            TextView answer = findViewById(R.id.textView);
            answer.setText(equation.toString());
        }
    }

    /**
     * Attempts to calcuate the String expression in equation and display it
     * @param view page being handled
     */
    public void calculate(View view) {
        TextView answer = findViewById(R.id.textView);

        if (equation.toString().equals("") || !canType) {
            return;
        }

        String result = equation.toString().replace("×", "*")
                .replace("÷", "/");

        this.allClear(view);

        try {
            String equals = engine.eval(result).toString();

            if (equals.endsWith(".0")) {
                equation.append(equals.substring(0, equals.length() - 2));

            } else {
                if (equals.equals("NaN") || equals.equals("Infinity")) {
                    equation.append("Undefined");
                    canType = false;
                } else {
                    equation.append(equals);
                }
            }

            answer.setText(equation.toString());

        } catch (ScriptException e) {
            // An Error can't be modified
            equation.append("Error");
            answer.setText(equation.toString());
            canType = false;
        }
    }
}

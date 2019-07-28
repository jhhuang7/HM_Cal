package com.example.hm_cal;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import javax.script.*;

public class MainActivity extends AppCompatActivity {

    // The equation being built by pressing keys
    StringBuilder equation = new StringBuilder();

    // The boi evaluating the String expressions
    ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");

    @Override
    /**
     * Starts the app and determines which screen the app starts on.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public float add(float x, float y) {
        return x + y;
    }

    public int add(int x, int y) {
        return x + y;
    }

    public float subtract(float x, float y) {
        return x - y;
    }

    public int subtract(int x, int y) {
        return x - y;
    }

    public float multiply(float x, float y) {
        return x * y;
    }

    public int multiply(int x, int y) {
        return x * y;
    }

    public float divide(float x, float y) {
        return x / y;
    }

    public int divide(int x, int y) {
        return x / y;
    }

    /**
     * Adds button text to equation text box
     * @param view page being handled
     */
    public void btnPress(View view){
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
    }

    /**
     * Deletes last character in the display box
     * @param view page being handled
     */
    public void backSpace(View view) {
        if (equation.length() > 0) {
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

        if (equation.toString().equals("")) {
            return;
        }

        String result = equation.toString().replace("×", "*")
                .replace("÷", "/");

        try {
            String equals = engine.eval(result).toString();

            if (equals.endsWith(".0")) {
                answer.setText(equals.substring(0, equals.length() - 2));
            } else {
                answer.setText(equals);
            }
        } catch (ScriptException e) {
            answer.setText("Error");
        }
    }

    public static void main(String[] args) {
        ScriptEngine testEngine = new ScriptEngineManager().getEngineByName("rhino");

        try {
            System.out.println(testEngine.eval("10+2*6"));
            System.out.println(testEngine.eval("100*2+12"));
            System.out.println(testEngine.eval("100*(2+12)"));
            System.out.println(testEngine.eval("100*(2+12)/14"));
        } catch (ScriptException e) {
            System.out.println("Fuck, we've done goofed!");
        }
    }
}



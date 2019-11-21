package com.example.hm_cal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.*;
import android.content.*;
import android.view.*;
import android.widget.*;

import javax.script.*;

/**
 * Main class for control app functions.
 */
public class MainActivity extends AppCompatActivity {

    // The equation being built by pressing keys.
    public StringBuilder equation = new StringBuilder();

    // The engine evaluating the String expressions.
    public ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");

    // Toggle boolean between can and can't type.
    public boolean canType = true;

    // Toggle boolean between is and isn't error.
    public boolean isError = false;

    /**
     * Starts the app and determines which screen the app starts on.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showMessage(1);
    }

    /**
     * Shows pop up message in app.
     * @param mode 1 or 2 for welcome or error message.
     */
    public void showMessage(int mode) {
        Context context = getApplicationContext();

        String text = "";
        switch (mode) {
            case 1:
                text = "Welcome to HM Cal!";
                break;
            case 2:
                String[] errors = {"Empty Equation", "Error", "Exceeding Length", ""};
                int num;
                if (equation.toString().equals("")) {
                    num = 0;
                } else if (isError) {
                    num = 1;
                } else if (!canType) {
                    num = 2;
                } else {
                    num = 3;
                }
                text = "Function disabled due to " + errors[num] + ".";
                break;
        }

        int duration = Toast.LENGTH_SHORT;

        Toast popup = Toast.makeText(context, text, duration);
        popup.show();
    }

    /**
     * Checks if the equation is within the 10 character limit.
     * @param equation string builder to be checked.
     */
    public void isLength(StringBuilder equation) {
        canType = equation.length() <= 10;
    }

    /**
     * Adds button text to equation text box.
     * @param view page being handled.
     */
    public void btnPress(View view) {
        isLength(equation);
        if (!canType || isError) {
            showMessage(2);
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

        TextView answer = findViewById(R.id.equation);
        answer.setText(equation.toString());
    }

    /**
     * Clears the display box.
     * @param view page being handled.
     */
    public void allClear(View view) {
        equation.setLength(0);
        TextView answer = findViewById(R.id.equation);
        answer.setText(equation.toString());
        canType = true;
        isError = false;
    }

    /**
     * Deletes last character in the display box.
     * @param view page being handled.
     */
    public void backSpace(View view) {
        if (equation.length() > 0 && !isError) {
            equation.deleteCharAt(equation.length() - 1);
            TextView answer = findViewById(R.id.equation);
            answer.setText(equation.toString());
            return;
        }
        showMessage(2);
    }

    /**
     * Attempts to calculate the String expression in equation and display it.
     * @param view page being handled.
     */
    public void calculate(View view) {
        TextView answer = findViewById(R.id.equation);

        if (equation.toString().equals("") || isError) {
            showMessage(2);
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
                    isError = true;
                } else {
                    if (equals.length() > 10) {
                        equation.append(equals.substring(0, 11));
                    } else {
                        equation.append(equals);
                    }
                }
            }
            answer.setText(equation.toString());
        } catch (Exception e) {
            // An Error can't be modified.
            equation.append("Error");
            answer.setText(equation.toString());
            canType = false;
            isError = true;
        }
    }
}

package com.example.hm_cal;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    // The equation being built by pressing keys
    private StringBuilder equation = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//       FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
     * Adds button text to equation textbox
     * @param view
     */
    public void btnPress(View view){
        switch (view.getId()) {
            case R.id.btn_0:
                equation.append("0 ");
                break;
            case R.id.btn_1:
                equation.append("1 ");
                break;
            case R.id.btn_2:
                equation.append("2 ");
                break;
            case R.id.btn_3:
                equation.append("3 ");
                break;
            case R.id.btn_4:
                equation.append("4 ");
                break;
            case R.id.btn_5:
                equation.append("5 ");
                break;
            case R.id.btn_6:
                equation.append("6 ");
                break;
            case R.id.btn_7:
                equation.append("7 ");
                break;
            case R.id.btn_8:
                equation.append("8 ");
                break;
            case R.id.btn_9:
                equation.append("9 ");
                break;
            case R.id.btn_decimal:
                equation.append(". ");
                break;
            case R.id.btn_plus:
                equation.append("+ ");
                break;
            case R.id.btn_subtract:
                equation.append("- ");
                break;
            case R.id.btn_multiply:
                equation.append("× ");
                break;
            case R.id.btn_divide:
                equation.append("÷ ");
                break;
        }

        TextView answer = (TextView) findViewById(R.id.textView);
        answer.setText(equation.toString());
    }

    public void allClear(View view) {
        equation.setLength(0);
        TextView answer = (TextView) findViewById(R.id.textView);
        answer.setText(equation.toString());
    }

    public void backSpace(View view) {
        if (equation.length() > 0) {
            equation.deleteCharAt(equation.length() - 1);
            TextView answer = (TextView) findViewById(R.id.textView);
            answer.setText(equation.toString());
        }
    }

    public void calculate(View view) {
        TextView answer = (TextView) findViewById(R.id.textView);
        System.out.println(equation.toString());
        answer.setText("I've done goofed");


    }

    // Shit happens
    public static int evaluate(String expression)
    {
        char[] tokens = expression.toCharArray();

        // Stack for numbers: 'values'
        Stack<Integer> values = new Stack<Integer>();

        // Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<Character>();

        for (int i = 0; i < tokens.length; i++)
        {
            // Current token is a whitespace, skip it
            if (tokens[i] == ' ')
                continue;

            // Current token is a number, push it to stack for numbers
            if (tokens[i] >= '0' && tokens[i] <= '9')
            {
                StringBuffer sbuf = new StringBuffer();
                // There may be more than one digits in number
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(Integer.parseInt(sbuf.toString()));
            }

            // Current token is an opening brace, push it to 'ops'
            else if (tokens[i] == '(')
                ops.push(tokens[i]);

                // Closing brace encountered, solve entire brace
            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }

            // Current token is an operator.
            else if (tokens[i] == '+' || tokens[i] == '-' ||
                    tokens[i] == '×' || tokens[i] == '÷')
            {
                // While top of 'ops' has same or greater precedence to current
                // token, which is an operator. Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));

                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }
        }

        // Entire expression has been parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));

        // Top of 'values' contains result, return it
        return values.pop();
    }

    // Returns true if 'op2' has higher or same precedence as 'op1',
    // otherwise returns false.
    public static boolean hasPrecedence(char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '×' || op1 == '÷') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    // A utility method to apply an operator 'op' on operands 'a'
    // and 'b'. Return the result.
    public static int applyOp(char op, int b, int a)
    {
        switch (op)
        {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '×':
                return a * b;
            case '÷':
                if (b == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(evaluate("10 + 2 × 6"));
        System.out.println(evaluate("100 × 2 + 12"));
        System.out.println(evaluate("100 × ( 2 + 12 )"));
        System.out.println(evaluate("100 × ( 2 + 12 ) ÷ 14"));
    }
}



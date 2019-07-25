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
                equation.append("*");
                break;
            case R.id.btn_divide:
                equation.append("/");
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
        EditText num1 = (EditText) findViewById(R.id.editText1);
        EditText num2 = (EditText) findViewById(R.id.editText2);

        if (num1.getText().toString().equals("") || num1.getText().toString().equals(".")) {
            num1.setText("0");
        }
        if (num2.getText().toString().equals("") || num2.getText().toString().equals(".")) {
            num2.setText("0");
        }

        float result = add(Float.parseFloat(num1.getText().toString()),
                Float.parseFloat(num2.getText().toString()));

//        answer.setText("" + result);
        
        answer.setText("Yeah but nah!");
    }
}

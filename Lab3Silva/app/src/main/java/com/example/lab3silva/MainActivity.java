/*
Project: Lab3Silva
Purpose Details: Calculator UI
Course: IST 402
Author: Nimesh Silva
Date Developed: 9/20/2019
Last Date Changed: 9/28/2019
Revision: v3
*/

package com.example.lab3silva;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;



public class MainActivity extends AppCompatActivity {

    //Instance variables
    private TextView txtResult; // Reference to EditText of result
    private String inStr = "0"; // Current input string
    private char lastOperator = ' ';
    private int result = 0;     //Result of computation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = (TextView) findViewById(R.id.txtResult);
        txtResult.setText("0");

        //Listener class for all buttons
        BtnListener listener = new BtnListener();

        ((Button) findViewById(R.id.btn0)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btn1)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btn2)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btn3)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btn4)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btn5)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btn6)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btn7)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btn8)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btn9)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnPlus)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnMinus)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnMultiply)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnDivide)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnEquals)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnClear)).setOnClickListener(listener);

    }

    private class BtnListener implements OnClickListener {
        @Override
        //On-click event handler
        public void onClick(View view) {
            switch (view.getId()) {
                //Number buttons: '0' to '9'
                case R.id.btn0:
                case R.id.btn1:
                case R.id.btn2:
                case R.id.btn3:
                case R.id.btn4:
                case R.id.btn5:
                case R.id.btn6:
                case R.id.btn7:
                case R.id.btn8:
                case R.id.btn9:
                    String inDigit = ((Button) view).getText().toString();
                    if (inStr.equals("0")) {
                        inStr = inDigit; //no leading zero
                    } else {
                        inStr += inDigit; //accumulate input digit
                    }
                    txtResult.setText(inStr);
                    //Clear buffer if last operator is '='
                    if (lastOperator == '=') {
                        result = 0;
                        lastOperator = ' ';
                    }
                    break;
                // Operator buttons: '+', '-', '*', '/' and '='
                case R.id.btnPlus:
                    compute();
                    lastOperator = '+';
                    break;
                case R.id.btnMinus:
                    compute();
                    lastOperator = '-';
                    break;
                case R.id.btnMultiply:
                    compute();
                    lastOperator = '*';
                    break;
                case R.id.btnDivide:
                    compute();
                    lastOperator = '/';
                    break;
                case R.id.btnEquals:
                    compute();
                    lastOperator = '=';
                    break;
                case R.id.btnClear:
                    result = 0;
                    inStr = "0";
                    lastOperator = ' ';
                    txtResult.setText("0");
                    break;
            }
        }
        private void compute() {
            int inNum = Integer.parseInt(inStr);
            inStr = "0";
            if (lastOperator == ' ') {
                result = inNum;
            } else if (lastOperator == '+') {
                result += inNum;
            } else if (lastOperator == '-') {
                result -= inNum;
            } else if (lastOperator == '*') {
                result *= inNum;
            } else if (lastOperator == '/') {
                result /= inNum;
            } else if (lastOperator == '=') {
                // Keep the result for the next operation
            }
            txtResult.setText(String.valueOf(result));
        }


    }
}


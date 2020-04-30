/*
Project: Lab3Silva
Purpose Details: Calculator UI
Course: IST 402
Author: Nimesh Silva
Date Developed: 9/29/2019
Last Date Changed: N/A
Revision: v1
*/

package com.example.lab5silva;



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
    private String lastOperator = " ";
    private double result = 0;     //Result of computation
    private double inNum = 0;
    private double radians = 0;
    private double MemoryStore = 0;

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
        ((Button) findViewById(R.id.btnMC)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnMR)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnMS)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnMPlus)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnMMinus)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnBack)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnCE)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnClear)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnSquareRoot)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnPercent)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnRecip)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnMod)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnLn)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnSin)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnCos)).setOnClickListener(listener);
        ((Button) findViewById(R.id.btnLog)).setOnClickListener(listener);

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
                    if (lastOperator == "=") {
                        result = 0;
                        lastOperator = " ";
                    }
                    break;
                // Operator buttons: '+', '-', '*', '/' and '='
                case R.id.btnPlus:
                    compute();
                    lastOperator = "+";
                    break;
                case R.id.btnMinus:
                    compute();
                    lastOperator = "-";
                    break;
                case R.id.btnMultiply:
                    compute();
                    lastOperator = "*";
                    break;
                case R.id.btnDivide:
                    compute();
                    lastOperator = "/";
                    break;
                case R.id.btnEquals:
                    compute();
                    lastOperator = "=";
                    break;
                case R.id.btnClear:
                    result = 0;
                    inStr = "0";
                    lastOperator = " ";
                    txtResult.setText("0");
                    break;
                case R.id.btnSquareRoot:
                    inNum = Double.parseDouble(inStr);
                    txtResult.setText(String.valueOf(Math.sqrt(inNum)));
                    break;
                case R.id.btnPercent:
                 compute();
                 lastOperator = "%";
                    break;
                case R.id.btnRecip:
                    inNum = Double.parseDouble(inStr);
                    txtResult.setText(String.valueOf(1/inNum));
                    break;
                case R.id.btnMod:
                    compute();
                    lastOperator = "MD";
                    break;
                case R.id.btnSin:
                    inNum = Double.parseDouble(inStr);
                    radians = Math.toRadians(inNum);
                    txtResult.setText(String.valueOf(Math.sin(radians))); //45.0 degrees is 0.07071 radians
                    break;
                case R.id.btnCos:
                    inNum = Double.parseDouble(inStr);
                    radians = Math.toRadians(inNum);
                    txtResult.setText(String.valueOf(Math.cos(radians)));
                    break;
                case R.id.btnTan:
                    inNum = Double.parseDouble(inStr);
                    radians = Math.toRadians(inNum);
                    txtResult.setText(String.valueOf(Math.tan(radians)));
                    break;
                case R.id.btnLn:
                    inNum = Double.parseDouble(inStr);
                    radians = Math.toRadians(inNum);
                    txtResult.setText(String.valueOf(Math.log(radians)));
                    break;
                case R.id.btnLog:
                    inNum = Double.parseDouble(inStr);
                    radians = Math.toRadians(inNum);
                    txtResult.setText(String.valueOf(Math.log10(radians)));
                    break;
                case R.id.btnMC:
                    MemoryStore = 0;
                    break;
                case R.id.btnMS:
                    MemoryStore = Double.parseDouble(inStr);
                    break;
                case R.id.btnMMinus:
                    inNum = Double.parseDouble(inStr);
                    MemoryStore -= inNum;
                case R.id.btnMPlus:
                    inNum = Double.parseDouble(inStr);
                    MemoryStore += inNum;
                    break;
            }
        }
        private void compute() {
            int inNum = Integer.parseInt(inStr);
            inStr = "0";
            if (lastOperator == " ") {
                result = inNum;
            } else if (lastOperator == "+") {
                result += inNum;
            } else if (lastOperator == "-") {
                result -= inNum;
            } else if (lastOperator == "*") {
                result *= inNum;
            } else if (lastOperator == "/") {
                result /= inNum;
            } else if (lastOperator == "=") {
                // Keep the result for the next operation
            } else if (lastOperator == "%") {
                result += (inNum * result)/100; //72 + (5%)3.6 = 75.6
            } else if (lastOperator == "MD") {
                result %= inNum; //Modulus
            }
            txtResult.setText(String.valueOf(result));
        }


    }
}



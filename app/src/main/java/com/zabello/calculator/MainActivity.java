package com.zabello.calculator;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    EditText numberField;

    Button   btnSin;
    Button   btnCos;
    Button   btnTan;
    Button   btnCtg;
    Button   btnLog;
    Button   btnPower;
    Button   btnPercent;
    Button   btnFact;

    boolean stateError;
    boolean lastNumeric;
    boolean lastDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        numberField = (EditText) findViewById(R.id.numberField);


    }


    public void onNumberClick(View view) {
        Button button = (Button) view;
        if (stateError) {
            numberField.setText(button.getText());
            stateError = false;
        } else {

            numberField.append(button.getText());
        }

        lastNumeric = true;
    }

    public void onOperationClick(View view) {

        Button button = (Button) view;
        if (lastNumeric && !stateError) {
            numberField.append(button.getText());
            lastNumeric = false;
            lastDot = false;
        }
    }

    public void onDelClick(View view) {

        numberField.setText("");
        lastNumeric = false;
        stateError = false;
        lastDot = false;
    }

    public void onDotClick(View view) {

        if (lastNumeric && !stateError && !lastDot) {
            numberField.append(".");
            lastNumeric = false;
            lastDot = true;
        }
    }


    public void onEqualClick(View view) {

        if (lastNumeric && !stateError) {
            String txt = numberField.getText().toString();

            Expression expression = new ExpressionBuilder(txt).build();
            try {

                double result = expression.evaluate();
                numberField.setText(Double.toString(result));
                lastDot = true;
            } catch (ArithmeticException ex) {

                numberField.setText("Error");
                stateError = true;
                lastNumeric = false;
            }
        }
    }
}
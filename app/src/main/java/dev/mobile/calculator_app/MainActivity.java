package dev.mobile.calculator_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button ac, ctv, plus, minus, divide, mult, point, equal, zero, one, two, three, four, five, six, seven, eight, nine;
    TextView result_tv, solution_tv;

    String input = "";
    String operator = "";
    double value1 = Double.NaN, value2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_tv = findViewById(R.id.result_tv);
        solution_tv = findViewById(R.id.solution_tv);

        ctv = findViewById(R.id.button_c);
        ac = findViewById(R.id.button_ac);

        plus = findViewById(R.id.button_plus);
        minus = findViewById(R.id.button_minus);
        mult = findViewById(R.id.button_multiply);
        divide = findViewById(R.id.button_divide);

        point = findViewById(R.id.button_dot);
        equal = findViewById(R.id.button_equals);

        one = findViewById(R.id.button_1);
        two = findViewById(R.id.button_2);
        three = findViewById(R.id.button_3);
        four = findViewById(R.id.button_4);
        five = findViewById(R.id.button_5);
        six = findViewById(R.id.button_6);
        seven = findViewById(R.id.button_7);
        eight = findViewById(R.id.button_8);
        nine = findViewById(R.id.button_9);
        zero = findViewById(R.id.button_0);

        setNumberButtonListeners();
        setOperatorButtonListeners();
        setUtilityButtonListeners();
    }

    private void setNumberButtonListeners() {
        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                input += button.getText().toString();
                solution_tv.setText(input);
            }
        };

        zero.setOnClickListener(numberClickListener);
        one.setOnClickListener(numberClickListener);
        two.setOnClickListener(numberClickListener);
        three.setOnClickListener(numberClickListener);
        four.setOnClickListener(numberClickListener);
        five.setOnClickListener(numberClickListener);
        six.setOnClickListener(numberClickListener);
        seven.setOnClickListener(numberClickListener);
        eight.setOnClickListener(numberClickListener);
        nine.setOnClickListener(numberClickListener);
        point.setOnClickListener(numberClickListener);
    }

    private void setOperatorButtonListeners() {
        View.OnClickListener operatorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (!Double.isNaN(value1)) {
                    value2 = Double.parseDouble(input);
                    value1 = perfOperation();
                    result_tv.setText(String.valueOf(value1));
                } else {
                    value1 = Double.parseDouble(input);
                }
                input = "";
                operator = button.getText().toString();
            }
        };

        plus.setOnClickListener(operatorClickListener);
        minus.setOnClickListener(operatorClickListener);
        mult.setOnClickListener(operatorClickListener);
        divide.setOnClickListener(operatorClickListener);
    }

    private void setUtilityButtonListeners() {
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Double.isNaN(value1)) {
                    value2 = Double.parseDouble(input);
                    double result = perfOperation();
                    result_tv.setText(String.valueOf(result));
                    value1 = Double.NaN;
                }
                input = "";
            }
        });

        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value1 = Double.NaN;
                value2 = Double.NaN;
                input = "";
                operator = "";
                solution_tv.setText("");
                result_tv.setText("");
            }
        });

        ctv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.length() > 0) {
                    input = input.substring(0, input.length() - 1);
                    solution_tv.setText(input);
                }
            }
        });
    }

    public double perfOperation() {
        switch (operator) {
            case "+":
                return value1 + value2;
            case "-":
                return value1 - value2;
            case "*":
                return value1 * value2;
            case "/":
                if (value2 != 0) {
                    return value1 / value2;
                } else {
                    result_tv.setText("Error");
                    return Double.NaN;
                }
            default:
                return value1;
        }
    }
}

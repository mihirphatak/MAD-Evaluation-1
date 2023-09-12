package com.example.evaluation1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayResult extends AppCompatActivity {

    TextView inputA, inputB, op, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);

        inputA = findViewById(R.id.inp_a_label);
        inputB = findViewById(R.id.inp_b_label);
        op = findViewById(R.id.inp_op_label);
        result = findViewById(R.id.result_label);

        if (getIntent() != null && getIntent().hasExtra(MainActivity.OP_KEY)) {
            Operation operation = (Operation) getIntent().getSerializableExtra(MainActivity.OP_KEY);
            double a = operation.getA();
            double b = operation.getB();
            double res = 0.0;
            String oper = operation.getOperation();
            if (oper.equals("+")) {
                res = a + b;
            } else if (oper.equals("-")) {
                res = a - b;
            } else if (oper.equals("*")) {
                res = a * b;
            } else {
                res = a / b;
            }
//            inputA.setText(String.format("%.2f", a));
//            op.setText(oper);
//            inputB.setText(String.format("%.2f", b));
//            result.setText("=" + String.format("%.2f", res));
            result.setText(String.format("%.2f", a) + oper + String.format("%.2f", b) + "=" + String.format("%.2f", res));
        }

        findViewById(R.id.result_cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
package com.example.evaluation1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button pickButton, calculateButton, clearButton;
    EditText inputA, inputB;
    TextView operation;
    public static final String OP_KEY = "OP_SEL";

    private ActivityResultLauncher<Intent> startPickOperationForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        String op = data.getStringExtra(PickOperation.SELECTED_OP);
                        operation.setText(op);
                    }
                }
            });

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputA = findViewById(R.id.input_a);
        inputB = findViewById(R.id.input_b);
        operation = findViewById(R.id.operation_main_label);

        pickButton = findViewById(R.id.pick_button_main);
        calculateButton = findViewById(R.id.calculate_button_main);
        clearButton = findViewById(R.id.clear_button_main);

        pickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PickOperation.class);
                startPickOperationForResult.launch(intent);
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inpA = String.valueOf(inputA.getText());
                String inpB = String.valueOf(inputB.getText());
                String op = String.valueOf(operation.getText());
                try {
                    double a = Double.parseDouble(String.valueOf(inputA.getText()));
                    double b = Double.parseDouble(String.valueOf(inputB.getText()));
                    if (op.equals("?")) {
                        Toast.makeText(MainActivity.this, "Please select operation.", Toast.LENGTH_SHORT).show();
                    } else if (!op.equals("?") && op.equals("/") && b == 0) {
                        Toast.makeText(MainActivity.this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                    } else {
                        Operation operation1 = new Operation(a, b, op);
                        Intent intent = new Intent(MainActivity.this, DisplayResult.class);
                        intent.putExtra(OP_KEY, operation1);
                        startActivity(intent);
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Please enter both inputs.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operation.setText("?");
                inputA.setText("");
                inputB.setText("");
            }
        });
    }
}
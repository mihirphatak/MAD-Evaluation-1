package com.example.evaluation1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class PickOperation extends AppCompatActivity {

    RadioGroup operation;

    public final static String SELECTED_OP = "OP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_operation);

        operation = findViewById(R.id.radioGroup);
        operation.check(R.id.radio_button_add);

        findViewById(R.id.select_pick_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                int checked = operation.getCheckedRadioButtonId();
                if(checked == R.id.radio_button_add) {
                    intent.putExtra(SELECTED_OP, "+");
                }
                else if(checked == R.id.radio_button_subtract) {
                    intent.putExtra(SELECTED_OP, "-");
                }
                else if(checked == R.id.radio_button_multiply) {
                    intent.putExtra(SELECTED_OP, "*");
                }
                else {
                    intent.putExtra(SELECTED_OP, "/");
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        findViewById(R.id.cancel_button_pick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
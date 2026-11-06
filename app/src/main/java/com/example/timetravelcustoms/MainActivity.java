package com.example.timetravelcustoms;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup rgReason = findViewById(R.id.rg_reason);
        Button btnInspect = findViewById(R.id.btn_inspect);

        btnInspect.setOnClickListener(v -> {

            int selectedId = rgReason.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(this, "Please choose a reason for Travel", Toast.LENGTH_LONG).show();
            } else {
                RadioButton selectedRadio = findViewById(selectedId);

                String reason = selectedRadio.getText().toString();

                Toast.makeText(this, reason, Toast.LENGTH_SHORT).show();
            }

        });
    }
}
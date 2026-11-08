package com.example.timetravelcustoms;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // 1.bich nkhaltou Ui bi les elements
        LinearLayout layout = findViewById(R.id.layout_result_mng);
        TextView tvIcon = findViewById(R.id.tv_icon_result_mng);
        TextView tvResult = findViewById(R.id.tv_result_mng);
        Button btnNew = findViewById(R.id.btn_new_traveler_mng);
        String matricule = "0497";

        // 3.resultat o ism traveller
        boolean verdict = getIntent().getBooleanExtra(
                MainActivity.makeKey(matricule, "VERDICT"), false);

        String name = getIntent().getStringExtra(
                MainActivity.makeKey(matricule, "NOM_VOYAGEUR"));

        // 4. verification il ism traveller
        if (name == null || name.trim().isEmpty()) {
            name = "Voyageur";
        }

        // 5. verification mtaa resultat
        if (verdict == true) {

            // ken voyage accepted
            layout.setBackgroundColor(Color.parseColor("#2E7D32"));

            tvIcon.setText("✓");
            tvResult.setText("Bon voyage " + name + " !");

        } else {

            // ken voyage rejected
            layout.setBackgroundColor(Color.parseColor("#C62828"));

            tvIcon.setText("✕");
            tvResult.setText(name +
                    " est en état d'arrestation temporelle");

        }

        // 6. button ili yraja3 lil main screen
        btnNew.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this,
                    MainActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_SINGLE_TOP);

            startActivity(intent);
            finish();
        });
    }
}
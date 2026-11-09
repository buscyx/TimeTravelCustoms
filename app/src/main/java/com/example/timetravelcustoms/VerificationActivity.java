package com.example.timetravelcustoms;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // elements connexion
        TextView tvName = findViewById(R.id.tv_name_mng);
        TextView tvAge = findViewById(R.id.tv_age_mng);
        TextView tvReason = findViewById(R.id.tv_reason_mng);

        Button btnApprove = findViewById(R.id.btn_approve_mng);
        Button btnReject = findViewById(R.id.btn_reject_mng);

        // mat defining
        String matricule = "0497";

        // info retrieval mn 3and nacer's part
        String name = getIntent().getStringExtra(
                MainActivity.makeKey(matricule, "NOM_VOYAGEUR"));

        String birth = getIntent().getStringExtra(
                MainActivity.makeKey(matricule, "ANNEE_NAISSANCE"));

        String destination = getIntent().getStringExtra(
                MainActivity.makeKey(matricule, "ANNEE_DESTINATION"));

        String reason = getIntent().getStringExtra(
                MainActivity.makeKey(matricule, "RAISON"));

        // info check
        if (name == null || birth == null ||
                destination == null || reason == null) {

            Toast.makeText(this, "Missing traveler information",
                    Toast.LENGTH_LONG).show();

            finish();
            return;
        }

        // age
        int temporalAge = Integer.parseInt(destination)
                - Integer.parseInt(birth);

        // display
        tvName.setText("TRAVELER: " + name);
        tvAge.setText("TEMPORAL AGE: " + temporalAge + " YEARS");
        tvReason.setText("TRAVEL REASON: " + reason);

        // paradoxe
        if (reason.equals("Modification of the past")
                && isPrime(temporalAge)) {

            btnApprove.setEnabled(false);
        }

        // approval
        btnApprove.setOnClickListener(v -> {

            Intent intent = new Intent(
                    VerificationActivity.this,
                    ResultActivity.class);

            intent.putExtra(
                    MainActivity.makeKey(matricule, "VERDICT"), true);

            intent.putExtra(
                    MainActivity.makeKey(matricule, "NOM_VOYAGEUR"), name);

            startActivity(intent);
        });

        // 9. Rejection
        btnReject.setOnClickListener(v -> {

            Intent intent = new Intent(
                    VerificationActivity.this,
                    ResultActivity.class);

            intent.putExtra(
                    MainActivity.makeKey(matricule, "VERDICT"), false);

            intent.putExtra(
                    MainActivity.makeKey(matricule, "NOM_VOYAGEUR"), name);

            startActivity(intent);
        });
    }

    // prime ( literally 9rineha fl bac)
    public static boolean isPrime(int number) {

        if (number < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {

            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
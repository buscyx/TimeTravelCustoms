package com.example.timetravelcustoms;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        //info retrieve(jebtha mn maintAct so naceur make sure ur info is similar before commiting plz)
        String matricule = "0497";

        String name = getIntent().getStringExtra(
                MainActivity.makeKey(matricule, "NOM_VOYAGEUR"));

        String birth = getIntent().getStringExtra(
                MainActivity.makeKey(matricule, "ANNEE_NAISSANCE"));

        String destination = getIntent().getStringExtra(
                MainActivity.makeKey(matricule, "ANNEE_DESTINATION"));

        String reason = getIntent().getStringExtra(
                MainActivity.makeKey(matricule, "RAISON"));

        //age
        int temporalAge = Integer.parseInt(destination) - Integer.parseInt(birth);

        //info display
        TextView tvName = findViewById(R.id.tv_name_mng);
        TextView tvAge = findViewById(R.id.tv_age_mng);
        TextView tvReason = findViewById(R.id.tv_reason_mng);

        tvName.setText("TRAVELER: " + name);
        tvAge.setText("TEMPORAL AGE: " + temporalAge);
        tvReason.setText("TRAVEL REASON: " + reason);


        Button btnApprove = findViewById(R.id.btn_approve_mng);

        if (reason.equals("Modification du passé") && isPrime(temporalAge)) {
            btnApprove.setEnabled(false);
        }
        // button connection
        Button btnReject = findViewById(R.id.btn_reject_mng);

// if true
        btnApprove.setOnClickListener(v -> {
            Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);

            intent.putExtra(MainActivity.makeKey(matricule, "VERDICT"), true);
            intent.putExtra(MainActivity.makeKey(matricule, "NOM_VOYAGEUR"), name);

            startActivity(intent);
        });

//if false
        btnReject.setOnClickListener(v -> {
            Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);

            intent.putExtra(MainActivity.makeKey(matricule, "VERDICT"), false);
            intent.putExtra(MainActivity.makeKey(matricule, "NOM_VOYAGEUR"), name);

            startActivity(intent);
        });
    }

    //prime
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
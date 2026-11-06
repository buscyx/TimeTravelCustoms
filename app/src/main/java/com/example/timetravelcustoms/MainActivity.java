
package com.example.timetravelcustoms;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // njibou les IDs
        EditText edtName = findViewById(R.id.edt_name_mng);
        EditText edtBirth = findViewById(R.id.edt_birth_mng);
        EditText edtDestination = findViewById(R.id.edt_destination_mng);

        RadioGroup rgReason = findViewById(R.id.rg_reason_mng);
        Button btnInspect = findViewById(R.id.btn_inspect_mng);

        // onClick
        btnInspect.setOnClickListener(v -> {

            String name = edtName.getText().toString().trim();
            String birth = edtBirth.getText().toString().trim();
            String destination = edtDestination.getText().toString().trim();

            int selectedId = rgReason.getCheckedRadioButtonId();

            // verif khfif lel inputs
            if (name.isEmpty() || birth.isEmpty() || destination.isEmpty()) {

                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_LONG).show();

            } else if (selectedId == -1) {

                Toast.makeText(this, "Please select a travel reason", Toast.LENGTH_LONG).show();

            } else {

                // njibou reason for travel
                RadioButton selectedRadio = findViewById(selectedId);
                String reason = selectedRadio.getText().toString();

                // nabaa3thohom lel act2
                Intent intent = new Intent(MainActivity.this, VerificationActivity.class);

                String matricule = "0497";

                // mathalan : 0497_BUSCY, 0497_2005, etc..

                intent.putExtra(makeKey(matricule, "NOM_VOYAGEUR"), name);
                intent.putExtra(makeKey(matricule, "ANNEE_NAISSANCE"), birth);
                intent.putExtra(makeKey(matricule, "ANNEE_DESTINATION"), destination);
                intent.putExtra(makeKey(matricule, "RAISON"), reason);

                startActivity(intent);
            }
        });
    }

    // makeKey chnwa taaml:
    public static String makeKey(String matricule, String nom) {
        return matricule + "_" + nom;
    }
}

package com.example.emed_projecte_android.ui.theme;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.emed_projecte_android.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Objects;

public class DoctorLogReg extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView doctorRegistrationHeader;
    private Spinner doctorTypeSpinner;
    private TextInputLayout nameLayout, officeLayout, specialityLayout, doctorNameLoginLayout;
    private TextInputEditText nameText, officeText, specialityText, doctorNameLoginText;
    private CheckBox disponibilityCheckbox;
    private Button returnBtn, registerDoctorButton, loginDoctorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorlogreg);

        progressBar = findViewById(R.id.progressBar);
        doctorRegistrationHeader = findViewById(R.id.doctorRegistrationHeader);
        doctorTypeSpinner = findViewById(R.id.doctorTypeSpinner);
        nameLayout = findViewById(R.id.nameLayout);
        officeLayout = findViewById(R.id.officeLayout);
        specialityLayout = findViewById(R.id.specialityLayout);
        nameText = findViewById(R.id.nameText);
        officeText = findViewById(R.id.officeText);
        specialityText = findViewById(R.id.specialityText);
        disponibilityCheckbox = findViewById(R.id.disponibilityCheckbox);
        registerDoctorButton = findViewById(R.id.registerDoctorButton);
        doctorNameLoginLayout = findViewById(R.id.doctorNameLoginLayout);
        doctorNameLoginText = findViewById(R.id.doctorNameLoginText);
        loginDoctorButton = findViewById(R.id.loginDoctorButton);
        returnBtn = findViewById(R.id.returnBtn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.doctor_types_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        doctorTypeSpinner.setAdapter(adapter);

        doctorTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnFunction(v);
            }
        });

        registerDoctorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerDoctor();
            }
        });

        loginDoctorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDoctor();
            }
        });
    }

    private void registerDoctor() {
        String doctorType = Objects.requireNonNull(doctorTypeSpinner.getSelectedItem()).toString();
        String name = Objects.requireNonNull(nameText.getText()).toString();
        String office = Objects.requireNonNull(officeText.getText()).toString();
        String speciality = Objects.requireNonNull(specialityText.getText()).toString();
        boolean disponibility = disponibilityCheckbox.isChecked();

        new RegisterTask().execute(doctorType, name, office, speciality, String.valueOf(disponibility));
    }

    private void loginDoctor() {
        String doctorName = Objects.requireNonNull(doctorNameLoginText.getText()).toString();
        new LoginTask().execute(doctorName);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private class RegisterTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String doctorType = params[0];
            String name = params[1];
            String office = params[2];
            String speciality = params[3];
            boolean disponibility = Boolean.parseBoolean(params[4]);

            // Realiza la lógica para enviar la información al servidor y registrar al doctor

            // Aquí deberías hacer una solicitud HTTP al servidor, por ejemplo, con Retrofit
            // Simula una respuesta exitosa para este ejemplo
            return "success";
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("success")) {
                showToast("Doctor registered successfully");
            } else {
                showToast("Registration failed");
            }
        }
    }

    private class LoginTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String doctorName = params[0];
            //FALTA IMPLEMENTAR LA LOGICA PER CONNECTAR AMB EL SERVIDOR
            return "success";
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("success")) {
                showToast("Doctor logged in successfully");
            } else {
                showToast("Doctor not found");
            }
        }
    }
    public void returnFunction(View view){
        Intent intentRegister = new Intent(DoctorLogReg.this, MainActivity.class);
        DoctorLogReg.this.startActivity(intentRegister);
    }
}
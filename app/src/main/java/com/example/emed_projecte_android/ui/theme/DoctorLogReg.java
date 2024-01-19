package com.example.emed_projecte_android.ui.theme;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.emed_projecte_android.MainActivity;
import com.example.emed_projecte_android.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class DoctorLogReg extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.emed_projecte_android.ui.theme.MESSAGE";
    private TextInputLayout nameLayout, officeLayout, specialityLayout, phoneLayout, hospitalLayout;
    private TextInputEditText nameText, officeText, specialityText, phoneText, hospitalText;
    private Spinner doctorTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorlogreg);

        nameLayout = findViewById(R.id.nameLayout);
        officeLayout = findViewById(R.id.officeLayout);
        specialityLayout = findViewById(R.id.specialityLayout);
        phoneLayout = findViewById(R.id.phoneLayout);
        hospitalLayout = findViewById(R.id.hospitalLayout);

        nameText = findViewById(R.id.nameText);
        officeText = findViewById(R.id.officeText);
        specialityText = findViewById(R.id.specialityText);
        phoneText = findViewById(R.id.phoneText);
        hospitalText = findViewById(R.id.hospitalText);

        doctorTypeSpinner = findViewById(R.id.doctorTypeSpinner);

        // Configuración del Spinner
        String[] doctorTypes = {"DoctorI", "DoctorSS", "DoctorM"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, doctorTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        doctorTypeSpinner.setAdapter(adapter);
    }

    public void registerDoctor(View view) { //Cuando le das al botón de registrar doctor
        String name = nameText.getText().toString();
        String office = officeText.getText().toString();
        String speciality = specialityText.getText().toString();
        String phone = phoneText.getText().toString();
        String hospital = hospitalText.getText().toString();

        if (!name.isEmpty() && !office.isEmpty() && !speciality.isEmpty() && !phone.isEmpty() && !hospital.isEmpty()) {

            String query = "http://192.168.1.39:9000/Android/registerDoctor";
            new RegisterDoctorTask().execute(query, name, office, speciality, phone, hospital);
        } else {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
        }
    }

    private class RegisterDoctorTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String query = params[0];
            String name = params[1];
            String office = params[2];
            String speciality = params[3];
            String phone = params[4];
            String hospital = params[5];

            try {
                URL url = new URL(query);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Construir el cuerpo de la solicitud
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, StandardCharsets.UTF_8));
                writer.write("name=" + name +
                        "&office=" + office +
                        "&speciality=" + speciality +
                        "&phone=" + phone +
                        "&hospital=" + hospital);

                writer.flush();
                writer.close();
                os.close();

                // Obtener la respuesta del servidor
                InputStream stream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                return sb.toString();

            } catch (IOException e) {
                Log.e("RegisterDoctorActivity", "Error en la solicitud HTTP", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null && result.equals("existe")) {
                Toast.makeText(DoctorLogReg.this, "El doctor ya está registrado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DoctorLogReg.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                Intent intentRegister = new Intent(DoctorLogReg.this, MainActivity.class);
                DoctorLogReg.this.startActivity(intentRegister);
            }
        }
    }

    public void returnFunction(View view){
        Intent intentRegister = new Intent(DoctorLogReg.this, MainActivity.class);
        DoctorLogReg.this.startActivity(intentRegister);
    }
}

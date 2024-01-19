package com.example.emed_projecte_android.ui.theme;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.example.emed_projecte_android.MainActivity;
import com.example.emed_projecte_android.R;
import com.google.android.material.textfield.TextInputEditText;
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

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText nombreUsuariotext;
    private TextInputEditText surnameUsuariotext;
    private TextInputEditText mailtext;
    private TextInputEditText passwordtext;
    private TextInputEditText DNIText;
    private TextInputEditText numberSSText;
    private TextInputEditText numberMText;
    private TextInputEditText phonenumberText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nombreUsuariotext = findViewById(R.id.nombreUsuariotext);
        surnameUsuariotext = findViewById(R.id.surnameUsuariotext);
        mailtext = findViewById(R.id.mailtext);
        passwordtext = findViewById(R.id.passwordtext);
        DNIText = findViewById(R.id.DNIText);
        numberSSText = findViewById(R.id.numberSSText);
        numberMText = findViewById(R.id.numberMText);
        phonenumberText = findViewById(R.id.phonenumberText);
    }

    public void register(View view) { //Cuando le das al boton register
        String name = nombreUsuariotext.getText().toString();
        String surname = surnameUsuariotext.getText().toString();
        String mail = mailtext.getText().toString();
        String password = passwordtext.getText().toString();
        String dni = DNIText.getText().toString();
        String numberSS = numberSSText.getText().toString();
        String numberM = numberMText.getText().toString();
        String phone_number = phonenumberText.getText().toString();

        if (!name.isEmpty() && !surname.isEmpty() && !mail.isEmpty() && !password.isEmpty() &&
                !dni.isEmpty() && !numberSS.isEmpty() && !numberM.isEmpty() && !phone_number.isEmpty()) {

            String query = "http://192.168.1.39:9000/Android/register";
            new RegisterTask().execute(query, name, surname, mail, password, dni, numberSS, numberM, phone_number);
        } else {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
        }
    }

    private class RegisterTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String query = params[0];
            String name = params[1];
            String surname = params[2];
            String mail = params[3];
            String password = params[4];
            String dni = params[5];
            String numberSS = params[6];
            String numberM = params[7];
            String phone_number = params[8];

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
                        "&surname=" + surname +
                        "&mail=" + mail +
                        "&password=" + password +
                        "&dni=" + dni +
                        "&numberSS=" + numberSS +
                        "&numberM=" + numberM +
                        "&phone_number=" + phone_number);

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
                Log.e("RegisterActivity", "Error en la solicitud HTTP", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null && result.equals("existe")) {
                Toast.makeText(RegisterActivity.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, SearchDoctor.class);
                startActivity(intent);
            }
        }
    }
    public void returnFunction(View view){
        Intent intentRegister = new Intent(RegisterActivity.this, MainActivity.class);
        RegisterActivity.this.startActivity(intentRegister);
    }
}
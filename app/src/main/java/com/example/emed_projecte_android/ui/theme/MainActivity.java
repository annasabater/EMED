package com.example.emed_projecte_android.ui.theme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.emed_projecte_android.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    public static final String TEXT1 = "User's email: ";
    public static final String TEXT2 = "User's password: ";
    static final String SHARED_PREFS = "PROVA" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.buttonLogin);
        progressBar = findViewById(R.id.progressBar);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email = ((TextInputEditText) findViewById(R.id.mailtext)).getText().toString();
                String password = ((TextInputEditText) findViewById(R.id.passwordtext)).getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    // Iniciar tarea asíncrona para realizar la solicitud HTTP
                    new LoginTask().execute(email, password);
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, "Por favor, ingrese correo y contraseña", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button registerButton = findViewById(R.id.buttonRegistro);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private class LoginTask extends AsyncTask<String, Void, String> {
        private String mail;
        private String password;

        @Override
        protected String doInBackground(String... params) {
            mail = params[0];
            password = params[1];
            try {
                OkHttpClient client = new OkHttpClient();
                RequestBody formBody = new FormBody.Builder()
                        .add("mail", params[0])
                        .add("password", params[1])
                        .build();

                Request request = new Request.Builder()
                        .url("http://tu_servidor/login")  // Reemplaza con la URL correcta de tu servidor
                        .post(formBody)
                        .build();

                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            progressBar.setVisibility(View.INVISIBLE);
            if (result != null && result.equals("Welcome")) {
                Toast.makeText(MainActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SearchDoctors.class);
                startActivity(intent);
                saveData();  // Llama a saveData después de iniciar sesion
            } else {
                Toast.makeText(MainActivity.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
            }
        }

        public void saveData() {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(TEXT1, mail);
            editor.putString(TEXT2, password);
            editor.apply();
            Toast.makeText(MainActivity.this, "Data saved", Toast.LENGTH_SHORT).show();
        }
    }
}

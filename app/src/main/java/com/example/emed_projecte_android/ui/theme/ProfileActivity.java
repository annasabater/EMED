package com.example.emed_projecte_android.ui.theme;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.emed_projecte_android.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ContactoActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        progressBar = findViewById(R.id.progressBar);

        Button updateButton = findViewById(R.id.buttonUpdateProfile);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                String name = ((TextInputEditText) findViewById(R.id.nombreUsuariotext)).getText().toString();
                String surname = ((TextInputEditText) findViewById(R.id.surnameUsuariotext)).getText().toString();
                String mail = ((TextInputEditText) findViewById(R.id.mailtext)).getText().toString();
                String password = ((TextInputEditText) findViewById(R.id.passwordtext)).getText().toString();
                String dni = ((TextInputEditText) findViewById(R.id.DNIText)).getText().toString();
                String numberSS = ((TextInputEditText) findViewById(R.id.numberSSText)).getText().toString();
                String numberM = ((TextInputEditText) findViewById(R.id.numberMText)).getText().toString();
                String phoneNumber = ((TextInputEditText) findViewById(R.id.phonenumberText)).getText().toString();

                if (!name.isEmpty() && !surname.isEmpty() && !mail.isEmpty() && !password.isEmpty()) {
                    new UpdateUserInfoTask().execute(name, surname, mail, password, dni, numberSS, numberM, phoneNumber);
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(ContactoActivity.this, "Por favor, complete todos los campos obligatorios", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class UpdateUserInfoTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient();
                RequestBody formBody = new FormBody.Builder()
                        .add("name", params[0])
                        .add("surname", params[1])
                        .add("mail", params[2])
                        .add("password", params[3])
                        .add("dni", params[4])
                        .add("numberSS", params[5])
                        .add("numberM", params[6])
                        .add("phoneNumber", params[7])
                        .build();

                // Construir la solicitud HTTP
                Request request = new Request.Builder()
                        .url("http://tu_servidor/updateUserInfo") // Reemplazar con la URL correcta de tu servidor
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
            if (result != null && result.equals("Success")) {
                Toast.makeText(ContactoActivity.this, "Información actualizada exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ContactoActivity.this, "Error al actualizar la información", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

package com.example.emed_projecte_android.ui.theme;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
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


public class DeleteActivity extends AppCompatActivity {

    private TextInputEditText mailtext;
    private TextInputEditText passwordtext;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        mailtext = findViewById(R.id.mailtext);
        passwordtext = findViewById(R.id.passwordtext);
        progressBar = findViewById(R.id.progressBar);
    }

    public void delete(View view) {
        String mail = mailtext.getText().toString();
        String password = passwordtext.getText().toString();

        if (!mail.isEmpty() && !password.isEmpty()) {
            String query = "http://tu_servidor/DeleteAccount";
            new DeleteAccountTask().execute(query, mail, password);
        } else {
            Toast.makeText(this, "Ingresa tu correo y contraseña", Toast.LENGTH_SHORT).show();
        }
    }

    private class DeleteAccountTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            String query = params[0];
            String mail = params[1];
            String password = params[2];

            try {
                OkHttpClient client = new OkHttpClient();
                RequestBody formBody = new FormBody.Builder()
                        .add("mail", mail)
                        .add("password", password)
                        .build();

                Request request = new Request.Builder()
                        .url(query)
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
            if (result != null && result.equals("success")) {
                Toast.makeText(DeleteActivity.this, "Cuenta eliminada con éxito", Toast.LENGTH_SHORT).show();
                Intent intentRegister = new Intent(DeleteActivity.this, com.example.emed_projecte_android.ui.theme.MainActivity.class);
                DeleteActivity.this.startActivity(intentRegister);
            } else {
                Toast.makeText(DeleteActivity.this, "Error al eliminar la cuenta", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void returnFunction(View view){
        Intent intentRegister = new Intent(DeleteActivity.this, MainActivity.class);
        DeleteActivity.this.startActivity(intentRegister);
    }

    public void buttonHome(View view){
        Intent intentRegister = new Intent(DeleteActivity.this, MainActivity.class);
        DeleteActivity.this.startActivity(intentRegister);
    }
}
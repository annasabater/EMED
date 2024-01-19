package com.example.emed_projecte_android.ui.theme;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
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

public class SearchDoctor extends AppCompatActivity {
    private TextInputEditText nombreDoctortext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        nombreDoctortext = findViewById(R.id.nombreDoctortext);
    }

    public void buttonBuscarDoctor(View view) {
        String doctorName = nombreDoctortext.getText().toString();

        if (!doctorName.isEmpty()) {
            String query = "http://tu_servidor/SearchDoctors";
            new SearchDoctorTask().execute(query, doctorName);
        } else {
            Toast.makeText(this, "Ingresa el nombre del doctor", Toast.LENGTH_SHORT).show();
        }
    }

    private class SearchDoctorTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String query = params[0];
            String doctorName = params[1];

            try {
                OkHttpClient client = new OkHttpClient();
                RequestBody formBody = new FormBody.Builder()
                        .add("doctorName", doctorName)
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
            if (result != null) {
                Toast.makeText(SearchDoctor.this, "Doctor encontrado: " + result, Toast.LENGTH_SHORT).show();
                Intent intentContact = new Intent(SearchDoctor.this, ContactActivity.class);
                startActivity(intentContact);
            } else {
                Toast.makeText(SearchDoctor.this, "Error en la solicitud", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void returnFunction(View view) {
        Intent intentRegister = new Intent(SearchDoctor.this, MainActivity.class);
        startActivity(intentRegister);
    }

    public void deleteButton(View view) {
        Intent intentDelete = new Intent(SearchDoctor.this, DeleteActivity.class);
        startActivity(intentDelete);
    }
}

package com.example.emed_projecte_android.ui.theme;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.emed_projecte_android.R;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SearchDoctors extends AppCompatActivity {
    private EditText doctorNameEditText;
    private EditText specialityEditText;
    private EditText hospitalEditText;
    private ProgressBar progressBar;
    public Button LanguageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        doctorNameEditText = findViewById(R.id.nombreDoctortext);
        specialityEditText = findViewById(R.id.Specialitytext);
        hospitalEditText = findViewById(R.id.Hospitaltext);
        progressBar = findViewById(R.id.progressBar);

        Button searchDoctorButton = findViewById(R.id.buttonBuscarDoctor);
        Button searchSpecialityButton = findViewById(R.id.buttonBuscarEspecialidad);
        Button searchHospitalButton = findViewById(R.id.buttonBuscarHospital);
        LanguageButton = findViewById(R.id.LanguageButton);
        LanguageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLanguage = new Intent(SearchDoctors.this, LanguageActivity.class);
                startActivity(intentLanguage);
            }
        });

        searchDoctorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String doctorName = doctorNameEditText.getText().toString();
                new SearchDoctorTask().execute(doctorName);
            }
        });

        searchSpecialityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String speciality = specialityEditText.getText().toString();
                new SearchSpecialityTask().execute(speciality);
            }
        });

        searchHospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hospitalName = hospitalEditText.getText().toString();
                new SearchHospitalTask().execute(hospitalName);
            }
        });
    }

    private class SearchDoctorTask extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            String doctorName = params[0];
            return performSearch("searchDoctor", "name", doctorName);
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            progressBar.setVisibility(View.INVISIBLE);

            if (result != null) {
                try {
                    //  resultado JSON
                    Toast.makeText(SearchDoctors.this, result.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(SearchDoctors.this, "Error en la búsqueda del doctor", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class SearchSpecialityTask extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            String speciality = params[0];
            return performSearch("searchSpeciality", "speciality", speciality);
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            progressBar.setVisibility(View.INVISIBLE);

            if (result != null) {
                try {
                    Toast.makeText(SearchDoctors.this, result.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(SearchDoctors.this, "Error en la búsqueda de especialidad", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class SearchHospitalTask extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            String hospitalName = params[0];
            return performSearch("searchHospital", "name", hospitalName);
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            progressBar.setVisibility(View.INVISIBLE);

            if (result != null) {
                try {
                    // Maneja el resultado JSON para la búsqueda de hospital
                    Toast.makeText(SearchDoctors.this, result.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(SearchDoctors.this, "Error en la búsqueda de hospital", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private JSONObject performSearch(String endpoint, String paramName, String paramValue) {
        try {
            URL url = new URL("http://tu-servidor/" + endpoint + "?" + paramName + "=" + paramValue);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return new JSONObject(stringBuilder.toString());
            } finally {
                urlConnection.disconnect();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void returnFunction(View view){
        Intent intentRegister = new Intent(SearchDoctors.this, MainActivity.class);
        SearchDoctors.this.startActivity(intentRegister);
    }
}

package com.example.emed_projecte_android.ui.theme;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.emed_projecte_android.R;
import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ContactActivity extends AppCompatActivity {

    private TextView titleTextView, doctorNameTextView, doctorOfficeTextView, doctorSpecialityTextView, doctorPhoneTextView, doctorHospitalTextView;
    private DatePicker appointmentDatePicker;
    private Button returnBtn, requestAppointmentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        titleTextView = findViewById(R.id.titleTextView);
        doctorNameTextView = findViewById(R.id.doctorNameTextView);
        doctorOfficeTextView = findViewById(R.id.doctorOfficeTextView);
        doctorSpecialityTextView = findViewById(R.id.doctorSpecialityTextView);
        doctorPhoneTextView = findViewById(R.id.doctorPhoneTextView);
        doctorHospitalTextView = findViewById(R.id.doctorHospitalTextView);
        appointmentDatePicker = findViewById(R.id.appointmentDatePicker);
        returnBtn = findViewById(R.id.returnBtn);
        requestAppointmentButton = findViewById(R.id.requestAppointmentButton);

        doctorNameTextView.setText("Doctor Name");
        doctorOfficeTextView.setText("Doctor Office");
        doctorSpecialityTextView.setText("Doctor Speciality");
        doctorPhoneTextView.setText("Doctor Phone Number");
        doctorHospitalTextView.setText("Doctor Hospital");

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(ContactActivity.this, SearchDoctor.class);
                ContactActivity.this.startActivity(intentRegister);
                finish();
            }
        });

        requestAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RequestAppointmentTask().execute();
            }
        });
    }

    private String getSelectedDate() {
        int day = appointmentDatePicker.getDayOfMonth();
        int month = appointmentDatePicker.getMonth() + 1; // Els mesos començen des de 0 per tant +1
        int year = appointmentDatePicker.getYear();

        return year + "-" + month + "-" + day;
    }

    private class RequestAppointmentTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String selectedDate = getSelectedDate(); //obte data seleccionada

            //  implementar la logica

            try {
                OkHttpClient client = new OkHttpClient();
                RequestBody formBody = new FormBody.Builder()
                        .add("selectedDate", selectedDate)
                        .build();

                Request request = new Request.Builder()
                        .url("http://tu_servidor/RequestAppointment")
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
            // respuesta servidor
            if (result != null) {
                if (result.equals("cita_solicitada")) {
                    Toast.makeText(ContactActivity.this, "Cita solicitada con éxito", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ContactActivity.this, "Error al solicitar la cita", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

package com.example.emed_projecte_android.ui.theme;

import static com.example.emed_projecte_android.ui.theme.MainActivity.SHARED_PREFS;
import static com.example.emed_projecte_android.ui.theme.MainActivity.TEXT1;
import static com.example.emed_projecte_android.ui.theme.MainActivity.TEXT2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.emed_projecte_android.R;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class PrincipalMainActivity  extends AppCompatActivity {
    private String text1;
    private String text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_main);
        loadData();

        TimerTask t = new TimerTask() {
            @Override
            public void run() {
                if(!Objects.equals(text1, "") && !Objects.equals(text2, "")) {
                    Intent intent = new Intent(PrincipalMainActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(PrincipalMainActivity.this, PrincipalMainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        };
        Timer tiempo = new Timer();
        tiempo.schedule(t,5000);
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text1 = sharedPreferences.getString( TEXT1,"" );
        text2 = sharedPreferences.getString( TEXT2,"" );
    }
}

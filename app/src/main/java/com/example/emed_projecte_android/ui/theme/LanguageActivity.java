package com.example.emed_projecte_android.ui.theme;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.emed_projecte_android.R;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
    }

    public void CatalanLanguage(View view) {
        setLocale("català");
    }

    public void SpanishLanguage(View view) {
        setLocale("español");
    }

    public void EnglishLanguage(View view) {
        setLocale("english");
    }

    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = getBaseContext().getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);
        Intent intentRegister = new Intent(LanguageActivity.this, SearchDoctors.class);
        this.startActivity(intentRegister);
    }

    private void restartActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void returnFunction(View view){
        Intent intentRegister = new Intent(LanguageActivity.this, MainActivity.class);
        LanguageActivity.this.startActivity(intentRegister);
    }
}

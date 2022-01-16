package com.nechay.visualiser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class SettingsActivity extends Activity {
    private Button enButt;
    private Button ruButt;
    private Button ukButt;
    private static Locale currentLocale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_settings);
        enButt = (Button) findViewById(R.id.enButt);
        ruButt = (Button) findViewById(R.id.ruButt);
        ukButt = (Button) findViewById(R.id.ukButt);
        enButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("en");
            }
        });
        ruButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("ru");
            }
        });
        ukButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("uk");
            }
        });

    }
    /*
    public static Locale getLocale(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        String lang = sharedPreferences.getString("language", "en");
        switch (lang) {
            case "English":
                lang = "en";
                break;
            case "Russian":
                lang = "ru";
                break;
            case "Ukrainian":
                lang="uk";
                break;
            case "ru":
                lang="ru";
                break;
        }
        return new Locale(lang);
    }

*/
    private void setLocale(String s) {
        final Resources resources = getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        final Configuration configuration = resources.getConfiguration();
        final Locale locale = new Locale(s);
        setCurrentLocale(locale);
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, dm);
        recreate();

    }
/*
    public static Locale getCurrentLocale() {
        return currentLocale;
    }
*/
    public static void setCurrentLocale(Locale currentLocale) {
        SettingsActivity.currentLocale = currentLocale;
    }
}
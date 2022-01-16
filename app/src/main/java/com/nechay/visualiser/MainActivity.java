package com.nechay.visualiser;

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

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends Activity {
    private Button newGrafB;
    private Button settingsB;
    private Button exitB;
    private Button descriptionB;
    private Intent setAct;
    private boolean nado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        nado = false;
        super.onCreate(savedInstanceState);
        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        newGrafB = (Button) findViewById(R.id.main_new_graf);
        settingsB = (Button)  findViewById(R.id.main_settings);
        exitB = (Button)  findViewById(R.id.main_exit);
        descriptionB = (Button)  findViewById(R.id.main_description);
        newGrafB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAct = new Intent(MainActivity.this, NewGraphActivity.class);
                startActivity(setAct);

            }
        });
        settingsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAct = new Intent(MainActivity.this, SettingsActivity.class);
                nado=true;
                startActivity(setAct);
            }
        });
        descriptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAct = new Intent(MainActivity.this,DescriptionActivity.class);
                startActivity(setAct);

            }
        });
        exitB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    protected void onPause() {

        super.onPause();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onResume() {
        if(nado){
            recreate();
            nado=false;
        }
        super.onResume();
    }
}
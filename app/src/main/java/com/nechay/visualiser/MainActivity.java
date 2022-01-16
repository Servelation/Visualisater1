package com.nechay.visualiser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {
    //                              AppCompatActivity ????
    Button newGrafB;
    Button loadGrafB;
    Button settingsB;
    Button exitB;
    Intent setAct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        newGrafB = (Button) findViewById(R.id.main_new_graf);
        loadGrafB = (Button) findViewById(R.id.main_load_graf);
        settingsB = (Button)  findViewById(R.id.main_settings);
        exitB = (Button)  findViewById(R.id.main_exit);

        newGrafB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAct = new Intent(MainActivity.this, NewGraphActivity.class);
                startActivity(setAct);
            }
        });

        loadGrafB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAct = new Intent(MainActivity.this, LoadGraphActivity.class);
                startActivity(setAct);
            }
        });
    }
}
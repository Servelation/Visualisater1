package com.nechay.visualiser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TableRow;

public class VisualaserActivity extends Activity {
    TableRow tableRow;
    FrameLayout frame;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_visualaser);
        //tableRow =  (TableRow) findViewById(R.id.table_row_surface);
        View v = new GraphSurfaceView(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        v.setLayoutParams(layoutParams);
        frame = (FrameLayout) findViewById(R.id.frame_surface);
        frame.addView(v);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
    /*
    SurfaceView SurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_visualaser);
        graphSurfaceView = (SurfaceView)findViewById(R.id.surfaceView);

        // Setting the touch listener
        graphSurfaceView.setOnTouchListener(graphSurfaceView);
    }


}

     */
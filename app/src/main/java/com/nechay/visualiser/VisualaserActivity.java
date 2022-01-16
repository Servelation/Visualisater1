package com.nechay.visualiser;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TableRow;

public class VisualaserActivity extends Activity {
    FrameLayout frame;
    EditText editTextNumber;
    Button setButton;
    Button kruskalButton;
    Button returnButton;
    private GraphSurfaceView surfaceGraph;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_visualaser);
        editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        setButton =  (Button) findViewById(R.id.setButton);
        kruskalButton =  (Button) findViewById(R.id.kruskalButton);
        returnButton =  (Button) findViewById(R.id.returnButton);
        View v = new GraphSurfaceView(this, this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        v.setLayoutParams(layoutParams);
        frame = (FrameLayout) findViewById(R.id.frame_surface);
        frame.addView(v);
        setSurfaceGraph((GraphSurfaceView) v);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(surfaceGraph.getPressedW()!=null && !editTextNumber.getText().toString().equals("")) {
                    surfaceGraph.getPressedW().setWeight(Integer.valueOf(editTextNumber.getText().toString()));
                }
            }
        });
        kruskalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlgorithmKr();
            }
        });
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                surfaceGraph.getGraph().getEdges().forEach(e -> e.setHidden(false));
            }
        });
    }
    public void AlgorithmKr() {

        Kruskal algKrus = new Kruskal(getSurfaceGraph().getGraph().getVertexList().size()+1, getSurfaceGraph().getGraph().getEdges().size()+1);
        algKrus.setGr(getSurfaceGraph().getGraph());
        int ii = 0;
        for(Edge r:getSurfaceGraph().getGraph().getEdges()) {
            algKrus.edge[ii].src =  r.getVertex1().getNumber()-1;
            algKrus.edge[ii].dest =  r.getVertex2().getNumber()-1;
            algKrus.edge[ii++].weight = r.getWeight().getWeight();
            System.out.println("было добавлено ребро");
        }
        algKrus.KruskalMST(getSurfaceGraph().getGraph());
        getSurfaceGraph().getGraph().setOstov(algKrus.getOstov());
        getSurfaceGraph().getGraph().getEdges().forEach(e ->
                e.setHidden(!getSurfaceGraph().getGraph().getOstov().getEdges().contains(e))
        );
    }
    public GraphSurfaceView getSurfaceGraph() {
        return surfaceGraph;
    }
    public void setSurfaceGraph(GraphSurfaceView surfaceGraph) {
        this.surfaceGraph = surfaceGraph;
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
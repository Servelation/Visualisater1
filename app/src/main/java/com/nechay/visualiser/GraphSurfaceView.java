package com.nechay.visualiser;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Selection;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputContentInfo;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

//String path = getApplicationInfo().dataDir;
class GraphSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private Graph graph;
    private SurfaceThread surfaceThread;
    private Weight w;
    private Vertex v;
    private Activity act;
    public GraphSurfaceView(Context context, Activity act) {
        super(context);
        this.act = act;
        graph = new Graph(NewGraphActivity.getCountOfRows()-2,NewGraphActivity.convertM());
        // Make Game Surface focusable so it can handle events. .
        this.setFocusable(true);
        // Sét callback.
        this.getHolder().addCallback(this);

        //Canvas canvas = getHolder().lockCanvas();
        System.out.println(this.getHeight());
        System.out.println(this.getWidth());

        //v.onDraw( this);
       // canvas.drawCircle(100,100,50,paint);
       // getHolder().unlockCanvasAndPost(canvas);
    }
    @Override
    public void draw(Canvas canvas)  {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        this.getGraph().getEdges().forEach(e -> e.onDraw(canvas));
        this.getGraph().getVertexList().forEach(v -> v.onDraw(canvas));

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            v = pressedVertex(graph,event.getX(), event.getY());
            w = pressedWeight(graph,event.getX(), event.getY());

        }else if(event.getAction() == MotionEvent.ACTION_MOVE){
            if(v!=null){
                v.update(event.getX(),event.getY());
            }


        }else if (event.getAction() == MotionEvent.ACTION_UP) {
            if(v!=null){
                v.update(event.getX(),event.getY());
                v = null;
            }
        }

        /*
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            path = new Path();
            path.moveTo(event.getX(), event.getY());
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            path.lineTo(event.getX(), event.getY());
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            path.lineTo(event.getX(), event.getY());
        }
        if (path != null) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawPath(path, paint);
            getHolder().unlockCanvasAndPost(canvas);
        }

         */
        return true;
    }
    private Weight pressedWeight(Graph g, float x, float y){
       for(Edge e:g.getEdges()) {
           g.getEdges().forEach(edg -> edg.getWeight().setToggled(false));
           if (    x > (e.getWeight().getX() - 60) &&
                   x < (e.getWeight().getX() + 60) &&
                   y > (e.getWeight().getY() - 60) &&
                   y < (e.getWeight().getY() + 60)) {
               e.getWeight().setToggled(true);
               return e.getWeight();
           }
       }
       return null;
    }
    private Vertex pressedVertex(Graph g, float x, float y){
        for(Vertex v:g.getVertexList()){
            if( v.getX()>(x-60) &&
                v.getX()<(x+60) &&
                v.getY()>(y-60) &&
                v.getY()<(y+60) ){
                    return v;
            }
        }
        return null;
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

        this.surfaceThread = new SurfaceThread(this,surfaceHolder);
        this.surfaceThread.setRunning(true);
        this.surfaceThread.start();
    }
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

        /*
        boolean retry= true;
        while(retry) {
            try {
                this.surfaceThread.setRunning(false);

                // Parent thread must wait until the end of GameThread.
                this.surfaceThread.join();
            }catch(InterruptedException e)  {
                e.printStackTrace();
            }
            retry= true;
        }

         */
    }
    public void update()  {
        this.getGraph().getEdges().forEach(e -> e.update());
    }
    public Weight getPressedW() {
        return w;
    }
    public void setPressedW(Weight w) {
        this.w = w;
    }
    public Graph getGraph() {
        return graph;
    }
    public void setGraph(Graph graph) {
        this.graph = graph;
    }
}
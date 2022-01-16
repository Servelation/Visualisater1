package com.nechay.visualiser;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

class GraphSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private Graph graph;
    private Path path;

    private SurfaceThread surfaceThread;
    private Thread thread = null;
    volatile boolean running = false;
    private Vertex v;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public GraphSurfaceView(Context context) {
        super(context);
        graph = new Graph(NewGraphActivity.getCountOfRows()-2,NewGraphActivity.convertM());
        // Make Game Surface focusable so it can handle events. .
        this.setFocusable(true);
        // Sét callback.
        this.getHolder().addCallback(this);

        //Canvas canvas = getHolder().lockCanvas();
        System.out.println(this.getHeight());
        System.out.println(this.getWidth());

        //v.onDraw( this);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(Color.WHITE);
       // canvas.drawCircle(100,100,50,paint);
       // getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    public void draw(Canvas canvas)  {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        for(Edge e: graph.getEdges()){
            e.onDraw(canvas);
        }
        for(Vertex v:graph.getVertexList()){
            v.onDraw(canvas);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            v = pressedVertex(graph,event.getX(), event.getY());
        }else if(event.getAction() == MotionEvent.ACTION_MOVE && v!=null){
            v.update(event.getX(),event.getY());
        }else if (event.getAction() == MotionEvent.ACTION_UP && v!=null) {
            v.update(event.getX(),event.getY());
            v = null;
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
        graph.getEdges().forEach(e -> e.update());
    }

}
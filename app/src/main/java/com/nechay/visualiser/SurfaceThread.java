package com.nechay.visualiser;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class SurfaceThread extends Thread {
    private boolean running;
    private GraphSurfaceView graphSurface;
    private SurfaceHolder surfaceHolder;
    public SurfaceThread(GraphSurfaceView graphSurface, SurfaceHolder surfaceHolder)  {
        this.graphSurface= graphSurface;
        this.surfaceHolder= surfaceHolder;
    }

    @Override
    public void run()  {
        long startTime = System.nanoTime();

        while(running)  {
            Canvas canvas= null;
            try {
                // Get Canvas from Holder and lock it.
                canvas = this.surfaceHolder.lockCanvas();
                // Synchronized
                synchronized (canvas)  {
                    this.graphSurface.update();
                    this.graphSurface.draw(canvas);
                }
            }catch(Exception e)  {
                // Do nothing.
            } finally {
                if(canvas!= null)  {
                    // Unlock Canvas.
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            long now = System.nanoTime() ;
            // Interval to redraw game
            // (Change nanoseconds to milliseconds)
            long waitTime = (now - startTime)/1000000;
            if(waitTime < 10)  {
                waitTime= 10; // Millisecond.
            }
            //System.out.print(" Wait Time="+ waitTime);
            try {
                // Sleep.
                this.sleep(waitTime);
            } catch(InterruptedException e)  {

            }
            startTime = System.nanoTime();
            //System.out.print(".");
        }
    }

    public void setRunning(boolean running)  {
        this.running= running;
    }
}
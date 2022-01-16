package com.nechay.visualiser;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;

public class Arc extends Edge {
    private Path path;
    private Path pathStamp;
    public Arc(Vertex v1, Vertex v2){
        super(v1,v2);
        path = new Path();

        pathStamp = new Path();
        pathStamp.lineTo(-20, -28);
        pathStamp.lineTo(28, 0);
        pathStamp.lineTo(-20, 28);
        pathStamp.close();

        getPaintLine().setPathEffect(new PathDashPathEffect(pathStamp, 55, 0, PathDashPathEffect.Style.MORPH));
    }
    public void onDraw( Canvas canvas){
        if(!isHidden()) {
            path.reset();
            path.moveTo(this.getX1(), this.getY1());
            path.lineTo(this.getX2(), this.getY2());
            canvas.drawPath(path, getPaintLine());
            drawWeight(canvas);
        }
    }

}

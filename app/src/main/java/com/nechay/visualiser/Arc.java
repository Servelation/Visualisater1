package com.nechay.visualiser;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;

public class Arc extends Edge {
    private Path path;
    private Path pathStamp;
    Paint p1;
    Paint paint;
    public double absAngle(){
        return Math.acos((this.getX2()-this.getX1())/(Math.sqrt(Math.pow((double)(this.getX2()-this.getX1()),2)+Math.pow((double)(this.getY2()-this.getY1()),2))));
    }
    public Arc(Vertex v1, Vertex v2){
        super(v1,v2);
        path = new Path();


        pathStamp = new Path();
        pathStamp.lineTo(-20, -28);
        pathStamp.lineTo(28, 0);
        pathStamp.lineTo(-20, 28);
        pathStamp.close();

        p1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        p1.setStyle(Paint.Style.STROKE);
        p1.setStrokeWidth(30);
        paint = new Paint(p1);
        paint.setColor(Color.rgb(0 ,0,100));
        paint.setPathEffect(new PathDashPathEffect(pathStamp, 55, 0, PathDashPathEffect.Style.MORPH));
    }
    public void onDraw( Canvas canvas){
        path.reset();
        path.moveTo(this.getX1(),this.getY1());
        path.lineTo(this.getX2(),this.getY2());
        canvas.drawPath(path,paint);
        drawWeight(canvas);
    }

}

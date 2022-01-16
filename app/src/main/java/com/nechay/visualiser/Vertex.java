package com.nechay.visualiser;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

public class Vertex {
    private float x, y;
    private int number;
    private Paint paintInner = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paintOuter = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paintText = new Paint(Paint.SUBPIXEL_TEXT_FLAG);
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    public void update(float x, float y){
        this.x = x;
        this.y = y;
    }
    public void onDraw( Canvas canvas){
        canvas.drawCircle(x,y,70, paintOuter);
        canvas.drawCircle(x,y,50, paintInner);
        canvas.drawText(Integer.toString(number),x-30,y+30,paintText);
    }
    public Vertex() {
        this.x = 1.0f;
        this.y = 1.0f;
        this.number = 0;
        paintInner.setStyle(Paint.Style.FILL_AND_STROKE);
        paintInner.setStrokeWidth(3);
        paintInner.setColor(Color.rgb(255,255,255));
        paintOuter.setStyle(Paint.Style.FILL_AND_STROKE);
        paintOuter.setStrokeWidth(3);
        paintOuter.setColor(Color.rgb(100,0,0));
        paintText.setStyle(Paint.Style.FILL);
        paintText.setStrokeWidth(30);
        paintText.setColor(Color.rgb(0,0,0));
    }
    public Vertex(float x, float y, int number) {
        this.x = x;
        this.y = y;
        this.number = number;
        paintInner.setStyle(Paint.Style.FILL_AND_STROKE);
        paintInner.setStrokeWidth(3);
        paintInner.setColor(Color.rgb(255,255,255));
        paintOuter.setStyle(Paint.Style.FILL_AND_STROKE);
        paintOuter.setStrokeWidth(3);
        paintOuter.setColor(Color.rgb(100,0,0));
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextSize(80);
        paintText.setColor(Color.rgb(0,0,0));
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public ArrayList<Edge> getEdges() { return edges; }
}

package com.nechay.visualiser;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class Edge {
    private float x1,x2,y1,y2;
    private int weight;
    private Vertex vertex1,vertex2;
    private Paint paintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paintLoop = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paintText = new Paint(Paint.SUBPIXEL_TEXT_FLAG);
    private Path path;
    public Paint getPaintLine() {
        return paintLine;
    }

    public void setPaintLine(Paint paintLine) {
        this.paintLine = paintLine;
    }

    public Edge(Vertex vertex1, Vertex vertex2) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.x1 = vertex1.getX();
        this.x2 = vertex2.getX();
        this.y1 = vertex1.getY();
        this.y2 = vertex2.getY();
        this.weight=5;
        paintLine.setStyle(Paint.Style.FILL_AND_STROKE);
        paintLine.setStrokeWidth(8);
        paintLine.setColor(Color.rgb(0 ,0,100));
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(8);
        paintLine.setColor(Color.rgb(0 ,0,100));
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextSize(60);
        paintText.setColor(Color.rgb(0,0,0));
        vertex1.getEdges().add(this);
        vertex2.getEdges().add(this);
    }
    public void onDraw( Canvas canvas){
        if(vertex1.equals(vertex2)){
            path = new Path();
            path.addCircle(x1+48,y1,96,Path.Direction.CW);
            canvas.drawPath(path,paintLine);
        }else{
            canvas.drawLine(x1, y1, x2, y2, paintLine);
        }
        drawWeight(canvas);

    }
    public void drawWeight(Canvas canvas){
        canvas.drawText(   Integer.toString(getWeight()),
                (getVertex1().getX()+getVertex2().getX())/2,
                (getVertex1().getY()+getVertex2().getY())/2 -20,
                getPaintText());
    }
    public void update(){
        setX1(getVertex1().getX());
        setX2(getVertex2().getX());
        setY1(getVertex1().getY());
        setY2(getVertex2().getY());
    }

    public float getX1() {
        return x1;
    }
    public void setX1(float x1) {
        this.x1 = x1;
    }
    public float getX2() {
        return x2;
    }
    public void setX2(float x2) {
        this.x2 = x2;
    }
    public float getY1() {
        return y1;
    }
    public void setY1(float y1) {
        this.y1 = y1;
    }
    public float getY2() {
        return y2;
    }
    public void setY2(float y2) {
        this.y2 = y2;
    }
    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }
    public Vertex getVertex1() { return vertex1; }
    public void setVertex1(Vertex vertex1) { this.vertex1 = vertex1; }
    public Vertex getVertex2() { return vertex2; }
    public void setVertex2(Vertex vertex2) { this.vertex2 = vertex2; }

    public Paint getPaintText() {
        return paintText;
    }

    public void setPaintText(Paint paintText) {
        this.paintText = paintText;
    }
}

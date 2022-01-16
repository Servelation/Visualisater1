package com.nechay.visualiser;

import android.graphics.Color;
import android.graphics.Paint;
import android.text.Editable;
import android.text.InputFilter;

import androidx.annotation.NonNull;

public class Weight{

    private Paint paintText = new Paint(Paint.SUBPIXEL_TEXT_FLAG);
    private int weight;
    private float x, y;
    private boolean toggled;
    public Weight() {
        setWeight(0);
        setX(1f);
        setY(1f);
        setToggled(false);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextSize(60);
        paintText.setColor(Color.rgb(0,0,0));
    }
    public Weight(int weight, float x, float y) {
        setWeight(weight);
        setX(x);
        setY(y);
        setToggled(false);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextSize(60);
        paintText.setColor(Color.rgb(0,0,0));
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }
    public boolean isToggled() {
        return toggled;
    }
    public void setToggled(boolean toggled) {
        paintText.setColor(Color.rgb(toggled?210:0,0,0));
        this.toggled = toggled;
    }
    public Paint getPaintText() {
        return paintText;
    }
    public void setPaintText(Paint paintText) {
        this.paintText = paintText;
    }
}

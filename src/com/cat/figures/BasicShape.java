package com.cat.figures;

import com.google.gson.annotations.Expose;
import javafx.scene.canvas.GraphicsContext;

public abstract class BasicShape implements Shape {
    @Expose
    protected double x;
    @Expose
    protected double y;
    protected GraphicsContext graphicsContext;
    @Expose
    protected int diameter = 30;
    @Expose
    protected int count;

    public BasicShape(double x, double y, GraphicsContext graphicsContext, int diameter, int count) {
        this.x = x;
        this.y = y;
        this.graphicsContext = graphicsContext;
        this.diameter = diameter;
        this.count = count;
    }

    @Override
    public void moveUP() {
        y -= 5;
    }

    @Override
    public void moveDOWN() {
        y += 5;
    }

    @Override
    public void moveLEFT() {
        x -= 5;
    }

    @Override
    public void moveRIGHT() {
        x += 5;
    }

    @Override
    public void increase() {
        diameter += 5;
    }

    @Override
    public void decrease() {
        diameter -= 5;
    }

    @Override
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public int getDiameter() {
        return diameter;
    }
}

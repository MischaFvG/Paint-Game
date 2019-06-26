package com.cat.figures;

import com.cat.Board;
import com.google.gson.annotations.Expose;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends BasicShape {
    @Expose
    private String objectType = "Ball";


    public Ball(double x, double y, GraphicsContext graphicsContext, int diameter, int count) {
        super(x, y, graphicsContext, diameter, count);
    }

    @Override
    public void draw() {
        if (Board.getActiveNumber() == count) {
            graphicsContext.setFill(Color.RED);
            graphicsContext.fillOval(x, y, diameter, diameter);
        }
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(5);
        graphicsContext.strokeOval(x, y, diameter, diameter);
    }

    @Override
    public void addObjectToGroup(Shape shape) {

    }

}

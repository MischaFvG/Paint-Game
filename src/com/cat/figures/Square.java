package com.cat.figures;

import com.cat.Board;
import com.google.gson.annotations.Expose;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends BasicShape {
    @Expose
    private String objectType = "Square";

    public Square(double x, double y, GraphicsContext graphicsContext, int diameter, int count) {
        super(x, y, graphicsContext, diameter, count);
    }

    @Override
    public void draw() {
        if (Board.getActiveNumber() == count) {
            graphicsContext.setFill(Color.YELLOW);
            graphicsContext.fillRect(x, y, diameter, diameter);
        }
        graphicsContext.setStroke(Color.BLUE);
        graphicsContext.setLineWidth(5);
        graphicsContext.strokeRect(x, y, diameter, diameter);
    }

    @Override
    public void addObjectToGroup(Shape shape) {

    }

}

package com.cat.figures;


import com.cat.Board;
import com.google.gson.annotations.Expose;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends BasicShape {
    @Expose
    private String objectType = "Triangle";

    public Triangle(double x, double y, GraphicsContext graphicsContext, int diameter, int count) {
        super(x, y, graphicsContext, diameter, count);
    }

    @Override
    public void draw() {
        double[] xPoints = {x, x + diameter / 2, x + diameter};
        double[] yPoints = {y + diameter, y, y + diameter};
        if (Board.getActiveNumber() == count) {
            graphicsContext.setFill(Color.GREEN);
            graphicsContext.fillPolygon(xPoints, yPoints, 3);
        }
        graphicsContext.setStroke(Color.PURPLE);
        graphicsContext.setLineWidth(5);
        graphicsContext.strokePolygon(xPoints, yPoints, 3);
    }

    @Override
    public void addObjectToGroup(Shape shape) {

    }
}

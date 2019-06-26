package com.cat.figures;

import com.cat.figures.Shape;
import com.google.gson.annotations.Expose;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class ObjectsGroup implements Shape {
    @Expose
    private String objectType = "Group";
    protected GraphicsContext graphicsContext;
    @Expose
    protected int count;
    @Expose
    private List<Shape> shapeList = new ArrayList<>();

    public ObjectsGroup(GraphicsContext graphicsContext, int count) {
        this.graphicsContext = graphicsContext;
        this.count = count;
    }

    public List<Shape> getShapeList() {
        return shapeList;
    }

    @Override
    public void moveUP() {
        for (Shape shape : shapeList) {
            shape.moveUP();
        }
    }

    @Override
    public void moveDOWN() {
        for (Shape shape : shapeList) {
            shape.moveDOWN();
        }
    }

    @Override
    public void moveLEFT() {
        for (Shape shape : shapeList) {
            shape.moveLEFT();
        }
    }

    @Override
    public void moveRIGHT() {
        for (Shape shape : shapeList) {
            shape.moveRIGHT();
        }
    }

    @Override
    public void draw() {
        for (Shape shape : shapeList) {
            shape.draw();
        }
    }

    @Override
    public void increase() {
        for (Shape shape : shapeList) {
            shape.increase();
        }
    }

    @Override
    public void decrease() {
        for (Shape shape : shapeList) {
            shape.decrease();
        }
    }

    @Override
    public void setCount(int count) {
        this.count = count;
        for (Shape shape : shapeList) {
            shape.setCount(count);
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public int getDiameter() {
        return 0;
    }

    @Override
    public void addObjectToGroup(Shape shape) {
        shape.setCount(count);
        shapeList.add(shape);
    }
}

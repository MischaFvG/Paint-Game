package com.cat.save_and_read;

import com.cat.figures.*;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class ReadObjectsFromFile {
    private int activeObjectNumber;
    private List<ShapesList> shapeList;

    private class ShapesList {
        private String objectType;
        private int x;
        private int y;
        private int diameter;
        private int count;
        private List<ShapesList> shapeList;
    }

    public ReadObjectsFromFile(int activeObjectNumber, List<ShapesList> shapesList) {
        this.activeObjectNumber = activeObjectNumber;
        this.shapeList = shapesList;
    }

    public List<Shape> createListOfShapes(GraphicsContext graphicsContext) {
        List<Shape> shapes = new ArrayList<>();
        for (ShapesList shapesList : shapeList) {
            if (shapesList.objectType.equals("Ball")) {
                shapes.add(new Ball(shapesList.x, shapesList.y, graphicsContext, shapesList.diameter, shapesList.count));
            }
            if (shapesList.objectType.equals("Square")) {
                shapes.add(new Square(shapesList.x, shapesList.y, graphicsContext, shapesList.diameter, shapesList.count));
            }
            if (shapesList.objectType.equals("Triangle")) {
                shapes.add(new Triangle(shapesList.x, shapesList.y, graphicsContext, shapesList.diameter, shapesList.count));
            }
            if (shapesList.objectType.equals("Group")) {
                ObjectsGroup objectsGroup = new ObjectsGroup(graphicsContext, shapesList.count);
                for (ShapesList shapesList1 : shapesList.shapeList) {
                    if (shapesList1.objectType.equals("Ball")) {
                        objectsGroup.addObjectToGroup(new Ball(shapesList1.x, shapesList1.y, graphicsContext, shapesList1.diameter, shapesList1.count));
                    }
                    if (shapesList1.objectType.equals("Square")) {
                        objectsGroup.addObjectToGroup(new Square(shapesList1.x, shapesList1.y, graphicsContext, shapesList1.diameter, shapesList1.count));
                    }
                    if (shapesList1.objectType.equals("Triangle")) {
                        objectsGroup.addObjectToGroup(new Triangle(shapesList1.x, shapesList1.y, graphicsContext, shapesList1.diameter, shapesList1.count));
                    }
                }
                shapes.add(objectsGroup);
            }
        }
        return shapes;
    }

    public int getActiveObjectNumber() {
        return activeObjectNumber;
    }

    @Override
    public String toString() {
        return "ReadObjectsFromFile{" +
                "activeObjectNumber=" + activeObjectNumber +
                ", shapesList=" + shapeList +
                '}';
    }
}

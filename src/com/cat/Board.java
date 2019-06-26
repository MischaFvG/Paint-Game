package com.cat;

import com.cat.figures.*;
import com.cat.save_and_read.ReadObjectsFromFile;
import com.cat.save_and_read.SaveObjects;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.canvas.GraphicsContext;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private double x = 0;
    private double y = 0;
    private GraphicsContext graphicsContext;
    private int count = 1;
    private static int activeNumber;
    private List<Shape> shapeList = new ArrayList<>();
    private List<ObjectsGroup> objectsGroupList = new ArrayList<>();

    public Board(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public static int getActiveNumber() {
        return activeNumber;
    }

    public void chooseNextElement() {
        if (activeNumber + 1 < count) {
            activeNumber++;
        } else {
            activeNumber = 1;
        }
    }

    public void choosePreviousElement() {
        if (activeNumber - 1 > 0) {
            activeNumber--;
        } else if (count - 1 > 0) {
            activeNumber = count - 1;
        }
    }

    public void addBall() {
        shapeList.add(new Ball(x, y, graphicsContext, 30, count));
        count++;
        activeNumber = count - 1;
    }

    public void addSquare() {
        shapeList.add(new Square(x, y, graphicsContext, 30, count));
        count++;
        activeNumber = count - 1;
    }

    public void addTriangle() {
        shapeList.add(new Triangle(x, y, graphicsContext, 30, count));
        count++;
        activeNumber = count - 1;
    }

    public void draw() {
        clean();
        for (Shape shape : shapeList) {
            shape.draw();
        }
    }

    public void moveRIGHT() {
        for (Shape shape : shapeList) {
            if (shape.getCount() == activeNumber) {
                shape.moveRIGHT();
            }
        }
    }

    public void moveLEFT() {
        for (Shape shape : shapeList) {
            if (shape.getCount() == activeNumber) {
                shape.moveLEFT();
            }
        }
    }

    public void moveUP() {
        for (Shape shape : shapeList) {
            if (shape.getCount() == activeNumber) {
                shape.moveUP();
            }
        }
    }

    public void moveDOWN() {
        for (Shape shape : shapeList) {
            if (shape.getCount() == activeNumber) {
                shape.moveDOWN();
            }
        }
    }

    public void increase() {
        for (Shape shape : shapeList) {
            if (shape.getCount() == activeNumber) {
                shape.increase();
            }
        }
    }

    public void decrease() {
        for (Shape shape : shapeList) {
            if (shape.getCount() == activeNumber) {
                shape.decrease();
            }
        }
    }

    public void deleteObject() {
        for (Shape shape : shapeList) {
            if (shape.getCount() == activeNumber) {
                shapeList.remove(shape);
                count--;
                int timeCount = 1;
                for (Shape shapeTime : shapeList) {
                    shapeTime.setCount(timeCount);
                    timeCount++;
                }
                break;
            }
        }
        for (ObjectsGroup objectsGroup : objectsGroupList) {
            if (objectsGroup.getCount() == activeNumber) {
                objectsGroupList.remove(objectsGroup);
                break;
            }
        }
        activeNumber = count - 1;
    }

    public void clean() {
        graphicsContext.clearRect(0, 0, graphicsContext.getCanvas().getWidth(), graphicsContext.getCanvas().getHeight());
    }

    public void groupObjects(double currentX, double currentY) {
        boolean x = false;
        for (Shape shape : shapeList) {
            if (currentX > shape.getX() && currentX < shape.getX() + shape.getDiameter()) {
                if (currentY > shape.getY() && currentY < shape.getY() + shape.getDiameter()) {
                    Shape tmpShape = null;
                    for (Shape shape1 : shapeList) {
                        if (shape1.getCount() == activeNumber) {
                            tmpShape = shape1;
                        }
                    }
                    if (tmpShape.getClass() == ObjectsGroup.class) {
                        tmpShape.addObjectToGroup(shape);
                        shapeList.remove(shape);
                        count--;
                        int tmpCount = 1;
                        for (Shape shape1 : shapeList) {
                            shape1.setCount(tmpCount);
                            tmpCount++;
                        }
                    } else {
                        ObjectsGroup objectsGroup = new ObjectsGroup(graphicsContext, count);
                        objectsGroup.addObjectToGroup(shape);
                        objectsGroup.addObjectToGroup(tmpShape);
                        shapeList.remove(shape);
                        shapeList.remove(tmpShape);
                        shapeList.add(objectsGroup);
                        objectsGroupList.add(objectsGroup);
                        count--;
                        int tmpCount = 1;
                        for (Shape shape1 : shapeList) {
                            shape1.setCount(tmpCount);
                            tmpCount++;
                        }
                    }
                    activeNumber = count - 1;
                    clean();
                    draw();
                    x = true;
                    break;
                }
            }
        }
        if (!x) {
            Shape current = null;
            for (Shape shape : shapeList) {
                if (shape.getCount() == activeNumber) {
                    current = shape;
                    break;
                }
            }
            if (current.getClass() == Ball.class || current.getClass() == Square.class || current.getClass() == Triangle.class) {
                for (ObjectsGroup objectsGroup : objectsGroupList) {
                    List<Shape> shapes = objectsGroup.getShapeList();
                    for (Shape shape : shapes) {
                        if (currentX > shape.getX() && currentX < shape.getX() + shape.getDiameter()) {
                            if (currentY > shape.getY() && currentY < shape.getY() + shape.getDiameter()) {
                                objectsGroup.addObjectToGroup(current);
                                this.shapeList.remove(current);
                                count--;
                                int tmpCount = 1;
                                for (Shape shape1 : this.shapeList) {
                                    shape1.setCount(tmpCount);
                                    tmpCount++;
                                }
                                activeNumber = count - 1;
                                clean();
                                draw();
                                return;
                            }
                        }
                    }
                }
            } else {
                ObjectsGroup tmpObjectsGroup = null;
                for (ObjectsGroup objectsGroup : objectsGroupList) {
                    if (objectsGroup.getCount() == activeNumber) {
                        tmpObjectsGroup = objectsGroup;
                    }
                }
                for (ObjectsGroup objectsGroup : objectsGroupList) {
                    List<Shape> shapes = objectsGroup.getShapeList();
                    for (Shape shape : shapes) {
                        if (currentX > shape.getX() && currentX < shape.getX() + shape.getDiameter()) {
                            if (currentY > shape.getY() && currentY < shape.getY() + shape.getDiameter()) {
                                for (Shape shape1 : shapes) {
                                    tmpObjectsGroup.addObjectToGroup(shape1);
                                }
                                this.shapeList.remove(objectsGroup);
                                this.objectsGroupList.remove(objectsGroup);
                                count--;
                                int tmpCount = 1;
                                for (Shape shape1 : this.shapeList) {
                                    shape1.setCount(tmpCount);
                                    tmpCount++;
                                }
                                activeNumber = count - 1;
                                clean();
                                draw();
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    public void readObjectsFromFile() {
        Gson gson = new Gson();
        String jsonString = "";
        try {
            jsonString = FileUtils.readFileToString(new File("saveObjects.txt"), "UTF-8");
            System.out.println(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!jsonString.isEmpty()) {
            ReadObjectsFromFile readObjectsFromFile = gson.fromJson(jsonString, ReadObjectsFromFile.class);
            shapeList = readObjectsFromFile.createListOfShapes(graphicsContext);
            activeNumber = readObjectsFromFile.getActiveObjectNumber();
            count = shapeList.size() + 1;
            for (Shape shape : shapeList) {
                if (shape.getClass() == ObjectsGroup.class) {
                    objectsGroupList.add((ObjectsGroup) shape);
                }
            }
        }
    }

    public void saveObjectsListToFile() {
        SaveObjects saveObjects = new SaveObjects(activeNumber, shapeList);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(saveObjects);
        try {
            FileUtils.writeStringToFile(new File("saveObjects.txt"), json, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

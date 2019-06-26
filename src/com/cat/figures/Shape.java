package com.cat.figures;

public interface Shape {
    void moveUP();

    void moveDOWN();

    void moveLEFT();

    void moveRIGHT();

    void draw();

    void increase();

    void decrease();

    void setCount(int count);

    int getCount();

    double getX();

    double getY();

    int getDiameter();

    void addObjectToGroup(Shape shape);
}

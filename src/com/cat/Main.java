package com.cat;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
    private static final int BOARD_WIDTH = 500;
    private static final int BOARD_HEIGHT = 500;
    private static Board board;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Course_Project_1");
        Canvas canvas = new Canvas();
        canvas.setWidth(BOARD_WIDTH);
        canvas.setHeight(BOARD_HEIGHT);
        BorderPane group = new BorderPane(canvas);
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.show();
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        board = new Board(graphicsContext);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.B) {
                    board.addBall();
                }
                if (event.getCode() == KeyCode.N) {
                    board.addSquare();
                }
                if (event.getCode() == KeyCode.T) {
                    board.addTriangle();
                }
                if (event.getCode() == KeyCode.W) {
                    board.moveUP();
                }
                if (event.getCode() == KeyCode.S) {
                    board.moveDOWN();
                }
                if (event.getCode() == KeyCode.A) {
                    board.moveLEFT();
                }
                if (event.getCode() == KeyCode.D) {
                    board.moveRIGHT();
                }
                if (event.getCode() == KeyCode.UP) {
                    board.chooseNextElement();
                }
                if (event.getCode() == KeyCode.DOWN) {
                    board.choosePreviousElement();
                }
                if (event.getCode() == KeyCode.ENTER) {
                    board.increase();
                }
                if (event.getCode() == KeyCode.BACK_SPACE) {
                    board.decrease();
                }
                if (event.getCode() == KeyCode.DELETE) {
                    board.deleteObject();
                }
                if (event.getCode() == KeyCode.Q) {
                    board.saveObjectsListToFile();
                }
                if (event.getCode() == KeyCode.R) {
                    board.readObjectsFromFile();
                }
                board.draw();
            }
        });
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isShiftDown() && event.getButton() == MouseButton.PRIMARY) {
                    board.groupObjects(event.getX(), event.getY());
                }
            }
        });
    }
}

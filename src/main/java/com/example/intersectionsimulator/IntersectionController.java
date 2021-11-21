package com.example.intersectionsimulator;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class IntersectionController {
    private Intersection intersection;
    private AnchorPane pane;
    private Group group = new Group();
    private Timer timer = new Timer();

    public IntersectionController(Intersection intersection, AnchorPane pane) {
        this.intersection = intersection;
        this.pane = pane;
        generatePoints();
    }

    public  void run() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        intersection.run();
                        displayCars();
                    }
                });
            }
        }, 0, 100);

    }

    public void displayCars() {
        pane.getChildren().clear();
        group.getChildren().clear();

        for (IntersectionEntry entry : intersection.getEntries()) {
            IntersectionEntry ie = intersection.getEntryByName(entry.getName());
            Point point = ie.getPoints().get(("Start"));
            Rectangle carView = null;

            for (Car car : entry.getCars()) {
                if (ie.getName().equals("N")) {
                    carView = createCar(car);
                    carView.setRotate(90);
                    carView.setX(point.getX());
                    carView.setY(point.getY() + (300 - car.getDistance()));
                } else if (ie.getName().equals("E")) {
                    carView = createCar(car);
                    carView.setX(point.getX() - (300 - car.getDistance()));
                    carView.setY(point.getY());
                } else if (ie.getName().equals("S")) {
                    carView.setRotate(90);
                    carView = createCar(car);
                    carView.setX(point.getX() );
                    carView.setY(point.getY() - (300 - car.getDistance()));
                } else if (ie.getName().equals("W")) {
                    carView = createCar(car);
                    carView.setX(point.getX() + (300 - car.getDistance()));
                    carView.setY(point.getY());
                }

                group.getChildren().add(carView);
            }
        }
        pane.getChildren().add(group);
    }

    private Rectangle createCar(Car car) {
        Color color = Color.RED;

        if (car.getDirection() == Direction.RIGHT) {
            color = Color.GREEN;
        } else if (car.getDirection() == Direction.STRAIGHT) {
            color = Color.YELLOW;
        }

        final Rectangle rectangle = new Rectangle(50, 25, color);
        return rectangle;
    }

    private void generatePoints() {
        intersection.getEntries().get(0).addPoint("Start", new Point("N", 350, 0));
        intersection.getEntries().get(0).addPoint("Intersection", new Point("N", 350, 320));
        intersection.getEntries().get(0).addPoint("RightTurn", new Point("N", 311, 360));
        intersection.getEntries().get(0).addPoint("Straight", new Point("N", 215, 512));
        intersection.getEntries().get(1).addPoint("Start", new Point("E", 800, 360));
        intersection.getEntries().get(1).addPoint("Intersection", new Point("E", 360, 215));
        intersection.getEntries().get(1).addPoint("RightTurn", new Point("E", 295, 180));
        intersection.getEntries().get(1).addPoint("Straight", new Point("E", 0, 215));
        intersection.getEntries().get(2).addPoint("Start", new Point("S", 430, 800));
        intersection.getEntries().get(2).addPoint("Intersection", new Point("S", 295, 360));
        intersection.getEntries().get(2).addPoint("RightTurn", new Point("S", 335, 300));
        intersection.getEntries().get(2).addPoint("Straight", new Point("S", 295, 0));
        intersection.getEntries().get(3).addPoint("Start", new Point("W", 0, 450));
        intersection.getEntries().get(3).addPoint("Intersection", new Point("W", 150, 295));
        intersection.getEntries().get(3).addPoint("RightTurn", new Point("W", 215, 340));
        intersection.getEntries().get(3).addPoint("Straight", new Point("W", 512, 300));
    }
}

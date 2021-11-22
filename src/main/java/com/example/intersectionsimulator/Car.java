package com.example.intersectionsimulator;

import java.util.Random;

public class Car {
    private Direction direction;
    private double distance;
    private int speed;

    public Car() {
        Random randomNbr = new Random();
        this.direction = Direction.fromNumber(randomNbr.nextInt(3));
        this.distance = 300;
        this.speed = 10;
    }

    public Car(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void updateDistance(double time) {
        if (distance != -1) {
            this.distance -= (speed * time);
        }
    }

    public void adjustSpeed(double distanceDiff) {
        if (distanceDiff < 70) {
            speed = 0;
        } else {
            speed = 10;
        }
    }

    public double getDistance() {
        return distance;
    }

    public int getSpeed() {
        return speed;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "distance= " + distance +
                '}';
    }
}

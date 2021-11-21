package com.example.intersectionsimulator;

public enum Direction {
    RIGHT(1),
    LEFT(2),
    STRAIGHT(0);

    private final int number;

    Direction(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static Direction fromNumber(int number) {
        Direction[] values = values();
        for (Direction direction : values) {
            if(direction.getNumber() == number) {
                return direction;
            }
        }
        return null;
    }
}

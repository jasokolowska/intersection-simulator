package com.example.intersectionsimulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionEntry {
    private final int id;
    private final String name;
    private List<Car> cars = new ArrayList<>();
    private Map<Direction, String> exits = new HashMap<>();
    private Map<String, Point> points = new HashMap<>();

    public IntersectionEntry(int id, String name) {
        this.id = id;
        this.name = name;
//        this.cars.add(new Car());
    }

    public void addExit(Direction direction, String name) {
        this.exits.put(direction, name);
    }

    public void addPoint(String name, Point point) {
        this.points.put(name, point);
    }

    public String getName() {
        return name;
    }

    public Map<Direction, String> getExits() {
        return exits;
    }

    public double getDistanceFromNextCar(int i) {
        double nextCarDistance = cars.get(i-1).getDistance();
        double actualCarDistance = cars.get(i).getDistance();
        return actualCarDistance - nextCarDistance;
    }

    public Map<String, Point> getPoints() {
        return points;
    }

    public int getNextEntryId() {
        if (this.id == 3) {
            return 0;
        }
        return id+1;
    }

    public int getOppositeEntryId() {
        if (this.id == 3) {
            return 1;
        } else if (this.id == 2) {
            return 0;
        }

        return id+2;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeFirstCar() {
        cars.remove(0);
    }

    public List<Car> getCars() {
        return cars;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "IntersectionEntry{" +
                "name='" + name + '\'' +
                ", cars=" + cars +
                ", exits=" + exits +
                "}\n";
    }
}

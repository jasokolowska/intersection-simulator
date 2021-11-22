package com.example.intersectionsimulator;

import java.util.*;

public class Intersection {
    private static final Random random = new Random();
    private static final Timer timer = new Timer();
    public static double timeUnit = 1;

    private List<IntersectionEntry> entries;

    public Intersection() {
        this.entries = new LinkedList<>();
        loadIntersection();
    }

    public List<IntersectionEntry> getEntries() {
        return entries;
    }

    public void run() {
        move();

        if (isCarOnIntersection()) {
            driveThroughIntersection();
            placeCarOnIntersection();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(entries);
    }

    private boolean isCarOnIntersection() {
        for (IntersectionEntry entry : entries) {
            for (Car car : entry.getCars()) {
                if (car.getDistance() == 0) {
                    return true;
                }
            }
        }
        return false;
    }


    public TimerTask updateCarsPositions() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                move();
            }
        };
        return task;
    }

    private void move() {
        for (IntersectionEntry entry : entries) {
            for (int i = 0; i < entry.getCars().size(); i++) {
                Car car = entry.getCars().get(i);
                if (i > 0) {
                    car.adjustSpeed(entry.getDistanceFromNextCar(i));
                } else if (car.getDistance() == 0){
                    car.setSpeed(0);
                } else if (car.getDistance() == -1) {
                    car.setSpeed(1);
                } else if (i == 0 && car.getDistance() != 0) {
                    car.setSpeed(10);
                }
                car.updateDistance(0.5);
            }
        }
    }

    public void driveThroughIntersection() {
        int counter = 0;

        for (IntersectionEntry entry : entries) {
            if (entry.getCars().size() > 0) {
                Direction direction = entry.getCars().get(0).getDirection();
                String rightEntryName = entry.getExits().get(Direction.RIGHT);
                String frontEntryName = entry.getExits().get(Direction.STRAIGHT);

                IntersectionEntry rightEntry = entries.stream()
                        .filter(intersectionEntry -> intersectionEntry.getName().equals(rightEntryName))
                        .toList()
                        .get(0);

                IntersectionEntry frontEntry = entries.stream()
                        .filter(intersectionEntry -> intersectionEntry.getName().equals(frontEntryName))
                        .toList()
                        .get(0);


                if (direction.equals(Direction.RIGHT)) {
                    entry.getCars().get(0).setDistance(-1);
//                    entry.getCars().remove(0);
                    System.out.println("Car removed RIGHT");
                } else if (direction.equals(Direction.STRAIGHT) && rightEntry.getCars().size() == 0) {
                    entry.getCars().get(0).setDistance(-1);
//                    entry.getCars().remove(0);
                    System.out.println("Car removed STRAIGHT");
                } else if (direction.equals(Direction.LEFT) && rightEntry.getCars().size() == 0
                        && frontEntry.getCars().size() == 0) {
                    entry.getCars().get(0).setDistance(-1);
//                    entry.getCars().remove(0);
                    System.out.println("Car removed LEFT");
                } else if (counter == 0) {
                    counter++;
                } else {
                    if (entry.getCars().size() > 0) {
                        entry.getCars().get(0).setDistance(-1);
//                        entry.getCars().remove(0);
                        System.out.println("Randomly picked car removed");
                    }
                }
            }
        }
    }

    public void placeCarOnIntersection() {
        int randomNbr = random.nextInt(3);
        this.entries.get(randomNbr).getCars().add(new Car());
    }

    public void loadIntersection() {
        this.entries.add(new IntersectionEntry(0, "N"));
        this.entries.add(new IntersectionEntry(1, "E"));
        this.entries.add(new IntersectionEntry(2, "S"));
        this.entries.add(new IntersectionEntry(3, "W"));

        this.entries.get(0).getCars().add(new Car());

        loadExits();
    }

    public void loadExits() {
        this.entries.get(0).addExit(Direction.RIGHT, "W");
        this.entries.get(0).addExit(Direction.LEFT, "E");
        this.entries.get(0).addExit(Direction.STRAIGHT, "S");

        this.entries.get(1).addExit(Direction.RIGHT, "N");
        this.entries.get(1).addExit(Direction.LEFT, "S");
        this.entries.get(1).addExit(Direction.STRAIGHT, "W");

        this.entries.get(2).addExit(Direction.RIGHT, "W");
        this.entries.get(2).addExit(Direction.LEFT, "E");
        this.entries.get(2).addExit(Direction.STRAIGHT, "N");

        this.entries.get(3).addExit(Direction.LEFT, "N");
        this.entries.get(3).addExit(Direction.RIGHT, "S");
        this.entries.get(3).addExit(Direction.STRAIGHT, "E");
    }

    public IntersectionEntry getEntryByName(String name) {
        List<IntersectionEntry> list = entries.stream()
                .filter(intersectionEntry -> intersectionEntry.getName().equals(name))
                .toList();
        return list.get(0);
    }
}

package com.example.locations;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {
    enum LocationType { FRIENDLY, NEUTRAL, ENEMY }

    static class Location implements Comparable<Location> {
        private final String name;
        private final LocationType type;

        Location(String name, LocationType type) {
            this.name = name;
            this.type = type;
        }

        public String getName() { return name; }
        public LocationType getType() { return type; }

        @Override
        public int compareTo(Location other) {
            return this.name.compareTo(other.name);
        }

        @Override
        public String toString() {
            return name + " (" + type + ")";
        }
    }

    public static void main(String[] args) {
        Location[] locations = {
                new Location("Springfield", LocationType.FRIENDLY),
                new Location("Black Forest", LocationType.ENEMY),
                new Location("Outpost 1", LocationType.ENEMY),
                new Location("Central Base", LocationType.FRIENDLY),
                new Location("Enemy Village", LocationType.ENEMY),
                new Location("Mountain City", LocationType.NEUTRAL)
        };

        // Process friendly locations
        TreeSet<Location> friendlyLocations = Arrays.stream(locations)
                .filter(l -> l.getType() == LocationType.FRIENDLY)
                .collect(Collectors.toCollection(TreeSet::new));

        System.out.println("Friendly locations sorted by natural order:");
        friendlyLocations.forEach(System.out::println);

        // Process enemy locations
        LinkedList<Location> enemyLocations = Arrays.stream(locations)
                .filter(l -> l.getType() == LocationType.ENEMY)
                .sorted(Comparator.comparing(Location::getType)
                        .thenComparing(Location::getName))
                .collect(Collectors.toCollection(LinkedList::new));

        System.out.println("\nEnemy locations sorted by type and name:");
        enemyLocations.forEach(System.out::println);
    }
}
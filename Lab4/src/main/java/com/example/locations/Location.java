package com.example.locations;

public class Location implements Comparable<Location> {
    private String name;
    private locationType type;

    public Location(String name, locationType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public locationType getType() {
        return type;
    }

    @Override
    public int compareTo(Location other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}
package com.example.locations;
import com.github.javafaker.Faker;

import org.jgrapht.*;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.*;
import org.jgrapht.alg.shortestpath.*;

import java.util.*;
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
        Faker faker = new Faker();
        Location[] locations = {
                new Location(faker.yoda().quote(), LocationType.FRIENDLY),
                new Location(faker.space().planet(), LocationType.ENEMY),
                new Location(faker.space().planet(), LocationType.ENEMY),
                new Location(faker.space().planet(), LocationType.FRIENDLY),
                new Location(faker.space().planet(), LocationType.ENEMY),
                new Location(faker.space().planet(), LocationType.NEUTRAL)
        };


        TreeSet<Location> friendlyLocations = Arrays.stream(locations)
                .filter(l -> l.getType() == LocationType.FRIENDLY)
                .collect(Collectors.toCollection(TreeSet::new));

        System.out.println("Friendly locations sorted by natural order:");
        friendlyLocations.forEach(System.out::println);


        LinkedList<Location> enemyLocations = Arrays.stream(locations)
                .filter(l -> l.getType() == LocationType.ENEMY)
                .sorted(Comparator.comparing(Location::getType)
                        .thenComparing(Location::getName))
                .collect(Collectors.toCollection(LinkedList::new));


        System.out.println("\nEnemy locations sorted by type and name:");
        enemyLocations.forEach(System.out::println);

        Graph<Location, DefaultWeightedEdge> g =new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        g.addVertex(locations[0]);
        g.addVertex(locations[1]);
        g.addVertex(locations[2]);
        g.addVertex(locations[3]);
        g.addVertex(locations[4]);
        g.addVertex(locations[5]);
        g.addEdge(locations[0], locations[1]);
        g.setEdgeWeight(g.getEdge(locations[0], locations[1]), 20);
        g.addEdge(locations[1], locations[2]);
        g.setEdgeWeight(g.getEdge(locations[1], locations[2]), 30);
        g.addEdge(locations[2], locations[3]);
        g.setEdgeWeight(g.getEdge(locations[2], locations[3]), 40);
        g.addEdge(locations[3], locations[4]);
        g.setEdgeWeight(g.getEdge(locations[3], locations[4]), 50);
        g.addEdge(locations[4], locations[5]);
        g.setEdgeWeight(g.getEdge(locations[5], locations[4]), 60);
        System.out.println();
        Iterator<Location> edges = new DepthFirstIterator<>(g, locations[0]);
        while (edges.hasNext()) {
            System.out.println(edges.next());
        }
        System.out.println("\nShortest path from "+locations[0]+" to "+locations[4]);
        DijkstraShortestPath<Location, DefaultWeightedEdge> dijkstraBaNebunule = new DijkstraShortestPath<>(g);
        var shortestPath = dijkstraBaNebunule.getPath(locations[0], locations[4]);
        System.out.println(shortestPath);
    }
}
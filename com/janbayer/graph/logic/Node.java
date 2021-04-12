package com.janbayer.graph.logic;

public class Node {
    private String name;
    private boolean visited;

    public Node(String name) {
        this.name = name;
        visited = false;
    }

    public String getName() {
        return name;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean getVisited() {
        return visited;
    }
}

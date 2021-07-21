package com.janbayer.graph.logic;

public class Node {
    private String name;
    private boolean visited;
    private int distance;
    private Node prev;

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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return "[Node: name=" + name + " visited=" + visited + "]";
    }
}

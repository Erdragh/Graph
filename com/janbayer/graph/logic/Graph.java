package com.janbayer.graph.logic;

public class Graph {
    private int[][] edges; // Die Adjazenzmatrix
    private Node[] nodes; // Das Feld mit den Knoten hier als Strings der Ortsnamen
    private int nodeCount; // sinnvolles Attribut

    public Graph(int maxCount) {
        edges = new int[maxCount][maxCount];
        nodes = new Node[maxCount];
        nodeCount = 0;
        for(int i=0; i < maxCount; i++) {// Mit dieser For-Schleife werden alle Eintr�ge in der Matrix vorausgef�llt
            for(int j=0; j < maxCount; j++) {
                if(i == j) {
                    edges[i][j] = 0;
                } else {
                    edges[i][j] = -1;
                }
            }
        }
    }
    
    public void addNode(String name) {
        if (nodeCount < nodes.length) {
            Node node = new Node(name);
            nodes[nodeCount++] = node;
        } else {
            System.err.println("No place left for additional Nodes!");
        }
    }

    public void remNode(String name) {
        //-----Removing the Node out of the nodes array
        int index = getIndex(name);
        if (index <= -1) {
            System.err.println("This node doesn't exist!");
            return;
        }
        for (int i = index; i < (nodes.length-1); i++) {
            nodes[i] = nodes[i+1];
        }
        //--------Setting the last one specifically, since there would be an IndexOutOfBoundsException elseways
        nodes[nodes.length-1] = null;
        nodeCount--;
        //-----Removing the Node out of the edges matrix
        for (int i = index; i < (edges.length-1); i++) {
            for (int j = 0; j < edges[i].length; j++) {
                edges[i][j] = edges[i+1][j];
                edges[j][i] = edges[j][i+1];
            }
        }
        //--------Setting the last row and column specifically, since there would be an IndexOutOfBoundsException elseways
        for (int i = 0; i < edges[edges.length-1].length; i++) {
            edges[edges.length-1][i] = -1;
            edges[i][edges.length-1] = -1;
            if (edges.length-1 == i) {
                edges[edges.length-1][i] = 0;
            }
        }
    }
    
    private int getIndex(String name) {
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].getName().equals(name)) return i;
        }
        return -1;
    }
    
    public void addEdge(String name1, String name2, int weight) {
        int index1, index2; // Zwischenspeicher f�r Index der Orte "name1" und "name2"
        index1 = getIndex(name1);
        index2 = getIndex(name2);
        if (index1 <= -1 || index2 <= -1) {
            System.err.println("The node doesn't exist");
            return;
        }
        edges[index1][index2] = weight;
        edges[index2][index1] = weight;
    }

    public void remEdge(String name1, String name2) {
        int index1, index2; // Zwischenspeicher f�r Index der Orte "name1" und "name2"
        index1 = getIndex(name1);
        index2 = getIndex(name2);
        if (index1 <= -1 || index2 <= -1) {
            System.err.println("The node doesn't exist");
            return;
        }
        edges[index1][index2] = -1;
        edges[index2][index1] = -1;
    }

    public String getEdges(String name) {
        int index = getIndex(name);
        if (index <= -1) {
            System.err.println("The node doesn't exist");
            return "ERROR, node does not exist";
        }
        String out = "";
        for (int i = 0; i < edges.length; i++) {
            System.out.println(edges[index][i]);
            out += edges[index][i] + ", " ;
        }

        return out;
    }

    public void initDepthFirstSearch(String start) {
        int index = getIndex(start);
        if (index <= -1) {
            System.err.println("The node doesn't exist");
            return;
        }
        for (int i = 0; i < nodes.length; i++) nodes[i].setVisited(false);
        depthFirstSearch(index);
    }

    private void depthFirstSearch(int index) {
        nodes[index].setVisited(true);
        System.out.println("Visiting " + nodes[index]);
        for (int i = 0; i < nodeCount; i++) {
            if (edges[index][i] > 0 && !nodes[i].getVisited()) {
                depthFirstSearch(i);
            }
        }
    }

    public void printEdges() {
        System.out.println("Table view of the Edges:");
        int length = 8;
        System.out.print(addSpacesToMatchLength("", length) + "| ");
        for (int i = 0; i < nodeCount; i++) {
            System.out.print(addSpacesToMatchLength(nodes[i].getName(), length) + "| ");
        }
        System.out.println();
        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount+1; j++) {
                for (int k = 0; k < length+2; k++) {
                    System.out.print("-");
                }
            }
            System.out.println();
            System.out.print(addSpacesToMatchLength(nodes[i].getName(), length) + "| ");
            for (int j = 0; j < nodeCount; j++) {
                System.out.print(addSpacesToMatchLength(edges[i][j] + "", length) + "| ");
            }
            System.out.println();
        }
    }

    private String addSpacesToMatchLength(String in, int length) {
        int initialLength = in.length();
        int spacesLeft = length - initialLength;
        for (int i = 0; i < spacesLeft; i++) {
            in = in + " ";
        }
        return in;
    }
}

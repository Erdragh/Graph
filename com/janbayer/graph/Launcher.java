package com.janbayer.graph;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.janbayer.graph.logic.Graph;

public class Launcher {

    private static Graph highways;
    private static int size = 12;

    public static void main(String[] args) {

        highways = new Graph(size);
        fillGraph();

        createGUI();

    }

    private static void fillGraph() {
        highways.addNode("Altd");
        highways.addNode("BA");
        highways.addNode("BT");
        highways.addNode("ER");
        highways.addNode("N");
        highways.addNode("N/Ost");
        highways.addNode("N/S�d");
        highways.addNode("OPfW");
        highways.addNode("R");
        highways.addNode("SW");
        highways.addNode("W�");

        highways.addEdge("Altd", "N", 8);
        highways.addEdge("Altd", "N/Ost", 7);
        highways.addEdge("Altd", "OPfW", 74);
        highways.addEdge("Altd", "R", 80);
        highways.addEdge("BA", "BT", 53);
        highways.addEdge("BA", "ER", 47);
        highways.addEdge("BA", "SW", 70);
        highways.addEdge("BT", "N", 77);
        highways.addEdge("ER", "N", 23);
        highways.addEdge("ER", "N/S�d", 25);
        highways.addEdge("ER", "W�", 81);
        highways.addEdge("N", "N/Ost", 8);
        highways.addEdge("N/Ost", "N/S�d", 5);
        highways.addEdge("OPfW", "R", 79);
        highways.addEdge("SW", "W�", 30);
    }

    private static void createGUI() {
        JFrame frame = new JFrame("Graph User Interface for Testing");
        frame.setLayout(new BorderLayout(10, 10));

        frame.setPreferredSize(new Dimension(600,100));
        frame.pack();

        JButton fillButton = new JButton("Fill the Graph");
        JButton addNodeButton = new JButton("Add a Node");
        JButton addEdgeButton = new JButton("Add an Edge");

        JButton getEdgesButton = new JButton("Get Edges of Node");

        JButton printEdgesButton = new JButton("Print a Edges");

        JPanel panel = new JPanel();
        JPanel buttonPanel = new JPanel();

        class ButtonListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                Object source = e.getSource();

                if (source.equals(fillButton)) {
                    fillGraph();
                } else if (source.equals(addNodeButton)) {
                    String name = JOptionPane.showInputDialog(frame, "Input the name for the new node.\nPress Cancel or leave empty and press enter to cancel.");
                    if (name == null) return;
                    if (name.equals("")) return;
                    highways.addNode(name);
                } else if (source.equals(addEdgeButton)) {
                    String name1 = JOptionPane.showInputDialog(frame, "Input the name for the first node.\nPress Cancel or leave empty and press enter to cancel.");
                    if (name1 == null) return;
                    if (name1.equals("")) return;
                    String name2 = JOptionPane.showInputDialog(frame, "Input the name for the second node.\nPress Cancel or leave empty and press enter to cancel.");
                    if (name2 == null) return;
                    if (name2.equals("")) return;
                    String weightInput = JOptionPane.showInputDialog(frame, "Input the weight of the edge.\nPress Cancel or leave empty and press enter to cancel.");
                    if (weightInput == null) return;
                    if (weightInput.equals("")) return;
                    if (!weightInput.matches("^\\d+$")) {
                        JOptionPane.showMessageDialog(frame, "The Number you entered is not a valid integer.\nAdding the edge was stopped.\nPlease try again.", "Not A Number", JOptionPane.ERROR_MESSAGE, null);
                        return;
                    }
                    int weight = Integer.parseInt(weightInput);

                    highways.addEdge(name1, name2, weight);
                } else if (source.equals(getEdgesButton)) {
                    String name = JOptionPane.showInputDialog(frame, "Input the name of the node.\nPress Cancel or leave empty and press enter to cancel.");
                    if (name == null) return;
                    if (name.equals("")) return;
                    String edges = highways.getEdges(name);
                    JOptionPane.showMessageDialog(frame, "The Edges the Node '" + name + "' is connected to are:\n" + edges, "Output of the Edges", JOptionPane.INFORMATION_MESSAGE);
                } else if (source.equals(printEdgesButton)) {
                    highways.printEdges();
                }

            }

        }

        ActionListener l = new ButtonListener();

        fillButton.addActionListener(l);
        addNodeButton.addActionListener(l);
        addEdgeButton.addActionListener(l);

        getEdgesButton.addActionListener(l);

        printEdgesButton.addActionListener(l);

        JLabel label = new JLabel("This is a GUI only for testing purposes. You may experiment with adding nodes and edges");

        panel.add(label, BorderLayout.NORTH);

        buttonPanel.add(fillButton);
        buttonPanel.add(addNodeButton);
        buttonPanel.add(addEdgeButton);
        
        buttonPanel.add(getEdgesButton);

        buttonPanel.add(printEdgesButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

    }

}

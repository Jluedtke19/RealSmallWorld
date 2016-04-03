package smallworld;

import edu.princeton.cs.In;
import edu.princeton.cs.StdIn;
import edu.princeton.cs.StdOut;
import java.util.Stack;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Runs breadth first search algorithm from source s on a graph G. After
 * preprocessing the graph, can process shortest path queries from s to any
 * vertex t.
 *
 */
public class PathFinder{

    // prev[v] = previous vertex on shortest path from s to v
    // dist[v] = length of shortest path from s to v
    private ST<String, String> prev = new ST<String, String>();
    private ST<String, Integer> dist = new ST<String, Integer>();
    
    private JPanel p;       //Panel
    private JFrame f;       //Frame
    public static String start;    //The start station
    public static String fin;   //The station going to

    /**
     * Put in the main method to start the process of making the panel and frame
     */
    public PathFinder(){
        gui();
    }
    /**
     * Creates frame and a panel
     */
    public void gui(){
        f = new JFrame();
        f.setVisible(true);
        f.setSize(600,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        p = new JPanel();
        p.setBackground(Color.WHITE);
        
        f.add(p);
        
    }
    private void TextField(){
        final JTextField start = new JTextField(6);
        final JTextField end = new JTextField(6);
        
        JButton finalize = new JButton("Start");
        finalize.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String data = start.getText();
                String fin = end.getText();
            }
        });
        
        p.add(start);
        p.add(end);
        p.add(finalize);
        f.setVisible(true);
        
    }
    /**
     * Runs BFS in the graph from a vertex by putting the source in a queue string
     * then removes the vertex v from the queue and inserts it's neighbors.
     * @param G The text file to be looked at
     * @param s The location where the function finds to paths to
     */
    // run BFS in graph G from given source vertex s
    public PathFinder(Graph G, String s) {

        // put source on the queue
        Queue<String> q = new Queue<String>();
        q.enqueue(PathFinder.fin);
        dist.put(PathFinder.fin, 0);

        // repeated remove next vertex v from queue and insert
        // all its neighbors, provided they haven't yet been visited
        while (!q.isEmpty()) {
            String v = q.dequeue();
            for (String w : G.adjacentTo(v)) {
                if (!dist.contains(w)) {
                    q.enqueue(w);
                    dist.put(w, 1 + dist.get(v));
                    prev.put(w, v);
                }
            }
        }
    }
/**
 * Sees if v is reachable from the source s
 * @param v The location to be tested
 * @return True or false if it connects to it
 */
    // is v reachable from the source s?
    public boolean hasPathTo(String v) {
        return dist.contains(v);
    }
/**
 * Returns the shortest path from v to s
 * @param v The location to be tested
 * @return The shortest path from v to s
 */
    // return the length of the shortest path from v to s
    public int distanceTo(String v) {
        if (!hasPathTo(v)) {
            return Integer.MAX_VALUE;
        }
        return dist.get(v);
    }
/**
 * Shortest path as an Iterable
 * @param v The location being tested
 * @return Distance as an Iterable
 */
    // return the shortest path from v to s as an Iterable
    public Iterable<String> pathTo(String v) {
        Stack<String> path = new Stack<String>();
        while (v != null && dist.contains(v)) {
            path.push(v);
            v = prev.get(v);
        }
        return path;
    }
    /**
     * Function will be called in the gui to start the process there.
     */
    public static void startProcess(){
        In in = new In("train.txt");
        Graph G = GraphGenerator.read(in, " ");
//        String s = args[2];
        
        PathFinder pf = new PathFinder(G, PathFinder.fin);
        pf.report(pf.start);
        //PathFinder pathFinder = new PathFinder();
        //pathFinder.TextField();
    }

/**
 * Computes the smallest distance between two places
 * @param args Filename, delimiter, and location to go to
 */
    public static void main(String[] args) {
        Guismall guismall = new Guismall();
        guismall.setVisible(true);
    } // main( String [] )
    
/**
 * Function to find a path to a location and gives the distance
 * @param airport The name of the location
 */
    private void report(String airport) {

        for (String v : this.pathTo(airport)) {
            StdOut.println("   " + v);
        }
        StdOut.println("distance " + this.distanceTo(airport));
    } // report( PathFinder, String )
    
} // PathFinder

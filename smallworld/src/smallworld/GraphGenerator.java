package smallworld;

import edu.princeton.cs.In;
import edu.princeton.cs.StdOut;

/******************************************************************************
 *  Supplies the method to construct a graph of points that are connected to 
 * each other.
 *
 ******************************************************************************/

public class GraphGenerator {

    public static Graph read(In in, String delimiter) {
        Graph G = new Graph();
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] names = line.split(delimiter);
            String movie = names[0];
            for (int i = 1; i < names.length; i++) {
                G.addEdge(movie, names[i]);
            }
        }
        return G;
    }

    public static void main(String[] args) {
        String filename  = args[0];
        String delimiter = args[1];
        In in = new In(filename);
        Graph G = GraphGenerator.read(in, delimiter);
        StdOut.println(G);
    }

}

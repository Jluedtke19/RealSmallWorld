package smallworld;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Output extends JPanel {
    
    public ArrayList<String> station = new ArrayList<String>();
    
//    public void PathFinder(Output pathfinder){
//        this.PathFinder(pathfinder);
//    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);


        g.setColor(Color.RED);
        for(String o: station) g.drawString(o, 25, 120);
            
        }

    }


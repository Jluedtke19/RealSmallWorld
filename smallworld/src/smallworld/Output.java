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
        int count = 10;
        for(String o: PathFinder.out){
            g.drawString(o, (10), (10+ count));
            count += 20;
            
        }
            
        }

    }


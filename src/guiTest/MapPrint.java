/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTest;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sengthavongphilavong
 */
public class MapPrint extends JPanel{

    public MapPrint() {
        
        JLabel[] labels = new JLabel[25];
        
        String stars = "";
        
        for (int i = 0; i < 40; i++) {
            stars += "* ";
        }
        
        for (int i = 0; i < 25; i++) {
            labels[i] = new JLabel(stars);
            add(labels[i]);
        }
        
        setPreferredSize(new Dimension(420, 540));
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        frame.getContentPane().add(new MapPrint());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

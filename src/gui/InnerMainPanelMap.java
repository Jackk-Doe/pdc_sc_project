/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sengthavongphilavong
 */
public class InnerMainPanelMap extends JPanel{
    
    public JLabel[] labels;

    public InnerMainPanelMap() {
        
        setBorder(BorderFactory.createTitledBorder("Map: "));
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        labels = new JLabel[25];
        
        String stars = "";
        
        for (int i = 0; i < 40; i++) {
            stars += "* ";
        }
        
        for (int i = 0; i < 25; i++) {
            labels[i] = new JLabel(stars);
            add(labels[i]);
        }
        
//        setPreferredSize(new Dimension(410, 405));
//        setSize(410, 405);
        
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        frame.getContentPane().add(new InnerMainPanelMap());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

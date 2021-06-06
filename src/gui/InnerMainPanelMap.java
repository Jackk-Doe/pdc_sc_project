/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import game.GameModel;
import java.awt.Dimension;
import map.Map;
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

    private Map dungeonMap;
    private GameModel gameModel;

    public InnerMainPanelMap(GameModel model) {
        
        setLayout(null);
        
        this.gameModel = model;
        this.dungeonMap = gameModel.getMap();
        
        setBorder(BorderFactory.createTitledBorder("Map: "));
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        labels = new JLabel[25];
        
        for (int y = 0; y < 25; y++) {
            
            String newStringMap = "";
            
            for (int x = 0; x < 40; x++) {
                newStringMap += dungeonMap.dunMap[x][y];
            }
            
            labels[y] = new JLabel(newStringMap);
            labels[y].setSize(420, 16);
            labels[y].setLocation(1, y * 17);
            add(labels[y]);
            newStringMap = "";
        }
        
//        setPreferredSize(new Dimension(420, 425));
//        setSize(420, 425);
        
    }

    // For testing
    public InnerMainPanelMap(Map inputMap) {
        setLayout(null);
        
        this.dungeonMap = inputMap;
        
        setBorder(BorderFactory.createTitledBorder("Map: "));
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        labels = new JLabel[25];
        
        for (int y = 0; y < 25; y++) {
            
            String newStringMap = "";
            
            for (int x = 0; x < 40; x++) {
                newStringMap += dungeonMap.dunMap[x][y];
            }
            
            System.out.println(newStringMap);
            
            labels[y] = new JLabel(newStringMap);
            labels[y].setSize(420, 16);
            labels[y].setLocation(1, y * 17);
            add(labels[y]);
            newStringMap = "";
        }
        
    }
    
    public void updateMap() {
        for (int y = 0; y < 25; y++) {
            
            String newStringMap = "";
            
            for (int x = 0; x < 40; x++) {
                newStringMap += gameModel.map.dunMap[x][y];
            }
            
            labels[y].setText(newStringMap);
            newStringMap = "";
        }
        
        this.revalidate();
        this.repaint();
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        frame.getContentPane().add(new InnerMainPanelMap(new Map()));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import game.GameModel;
import map.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sengthavongphilavong
 * 
 * This Class used to Set and Draw Map GUI through Map.dungeonMap value
 * 
 * Update every time when Player move in each turn
 * 
 * This Class is located inside InnerPanel
 */
public class InnerMainPanelMap extends JPanel{
    
    public JLabel[] labels;

    private Map dungeonMap;
    private GameModel gameModel;

    // Set and draw Map though values from outer-class : Map.dungeonMap
    public InnerMainPanelMap(GameModel model) {
        
        setLayout(null);
        
        this.gameModel = model;
        this.dungeonMap = gameModel.getMap();
        
        setBorder(BorderFactory.createTitledBorder("Map: "));
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        labels = new JLabel[25];
        
        for (int y = 0; y < 25; y++) {
            
            // Used this string to store each row string from Map.dungeonMap
            String newStringMap = "";
            
            for (int x = 0; x < 40; x++) {
                newStringMap += dungeonMap.dunMap[x][y];
            }
            
            // Set each labels to each string row
            labels[y] = new JLabel(newStringMap);
            labels[y].setSize(420, 16);
            labels[y].setLocation(1, y * 17);
            
            add(labels[y]);
            
            // Reset String
            newStringMap = "";
        }
        
//        setPreferredSize(new Dimension(420, 425));
//        setSize(420, 425);
        
    }

    // UPdate Map GUI after Player Moving each time
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
    
}

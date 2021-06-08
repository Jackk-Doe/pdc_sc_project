/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Adjustable;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author sengthavongphilavong
 * 
 * This Class GUI used to print out and update events(String) happening in Game
 * 
 * This Class is located as part of InnerPanel
 */
public class ActionListPanel extends JPanel{
    
    private ArrayList<String> arrayList;
    private JList<String> actionList;
    private JScrollPane scrollPane;

    public ActionListPanel() {
        
        setBorder(BorderFactory.createTitledBorder("Action Lists:"));
        
        arrayList = new ArrayList<>();
        actionList = new JList<>();
        
        scrollPane = new JScrollPane(actionList,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(210, 200));
        
        // Case Study:
        // This AdjustmentListener 
        // Automaticly move the scrollPane down every time when new item is added
        // Problem : Can't scroll UP (Fixed)
        // Fixed! read code below
        // Solution : actionList.ensureIndexIsVisible(newList.length - 1);
//        scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
//            @Override
//            public void adjustmentValueChanged(AdjustmentEvent e) {
//                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
//            }
//        });
//        scrollPane.setSize(210, 200);
        
        add(scrollPane);
        
    }
    
    // Add new action string to the list, then update UI
    public void addTextToList(String newString) {
        
        newString = "> " + newString;
        
        /*
        If the income string is too long (does not fit panel),
        cut string with the below private method
        */
        if (newString.length() > 28) {
            splitString(newString);
        }
        else {
            arrayList.add(newString);
        }
        
        String[] newList = new String[arrayList.size()];
        
        for (int i = 0; i < arrayList.size(); i++) {
            newList[i] = arrayList.get(i);
        }
        
        actionList.setListData(newList);
        
        // Fixed scrollPane problems
        actionList.ensureIndexIsVisible(newList.length - 1);
    }
    
    // Print all Player's commands and Game Events to file
    // and console after Game end
    public void printEnd() {
        
        PrintWriter pw = null;
        
        try {
            pw = new PrintWriter(new FileOutputStream("./IOresources/gameevents.txt"));
            
            for (String string : arrayList) {
                
                pw.println(string);
                
                System.out.println(string);
            }
            
            pw.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // Split and cut the incoming String
    private void splitString(String inString) {
        
        int startSplit = 0;
        int endSplit = 28;
        
        arrayList.add(inString.substring(startSplit, endSplit) + "..");
        
        do {            
            startSplit = endSplit;
            endSplit += 28;
            
            if (endSplit >= inString.length()) {
                endSplit = inString.length();
                arrayList.add(inString.substring(startSplit, endSplit));
                break;
            }
            
            arrayList.add(inString.substring(startSplit, endSplit) + "..");
            
        } while (endSplit < inString.length());
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Dimension;
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
 */
public class ActionListPanel extends JPanel{
    
    ArrayList<String> arrayList;
    JList<String> actionList;

    public ActionListPanel() {
        
        setBorder(BorderFactory.createTitledBorder("Action Lists:"));
        
        arrayList = new ArrayList<>();
        actionList = new JList<>();
        
        JScrollPane scrollPane = new JScrollPane(actionList,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(210, 200));
//        scrollPane.setSize(210, 200);
        
        add(scrollPane);
        
    }
    
    // Add new action string to the list, then update UI
    public void addTextToList(String newString) {
        
        newString = "> " + newString;
        
        /*
        If the income string is too long (does not fit panel),
        cut string with private method
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
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        ActionListPanel actionListPanel = new ActionListPanel();
        frame.add(actionListPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
        while (true) {
            System.out.print("> ");
            Scanner scan = new Scanner(System.in);
            String text = scan.nextLine();
            actionListPanel.addTextToList(text);
        }
    }
}

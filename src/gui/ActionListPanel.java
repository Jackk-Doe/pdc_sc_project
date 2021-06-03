/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;
import java.awt.Color;
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
//        scrollPane.setSize(160, 295);
        scrollPane.setPreferredSize(new Dimension(210, 200));
        
        add(scrollPane);
        
//        setPreferredSize(new Dimension(230, 375));
//        setSize(230, 220);
    }
    
    public void updateList(String newString) {
        
        arrayList.add(newString);
        
        String[] newList = new String[arrayList.size()];
        
        for (int i = 0; i < arrayList.size(); i++) {
            newList[i] = arrayList.get(i);
        }
        
        actionList.setListData(newList);
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
            actionListPanel.updateList(text);
        }
    }
}

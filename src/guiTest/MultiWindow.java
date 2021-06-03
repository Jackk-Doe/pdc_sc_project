/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTest;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author sengthavongphilavong
 */
public class MultiWindow extends JFrame{
    
    FirstFrame firstFrame;
    SecondFrame secondFrame;

    public MultiWindow() throws HeadlessException {
        super("MultiWindowTest");
        
        JButton button1 = new JButton("Open First Frame");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstFrame = new FirstFrame();
                firstFrame.setVisible(true);
            }
        });
        
        JButton button2 = new JButton("Or Open Second Frame");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondFrame = new SecondFrame();
                secondFrame.setVisible(true);
            }
        });
        
        JButton button3 = new JButton("JOptionPane");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = JOptionPane.showInputDialog("Input Please: ");
                if (text != null) {
                    changeButtonText(text, (JButton)e.getSource());
                }
            }
        });
        
        JPanel panel = new JPanel();
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        
        getContentPane().add(panel);
        
        pack();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void changeButtonText(String text, JButton button) {
        button.setText(text);
        repaint();
    }
    
    public static void main(String[] args) {
        JFrame frame = new MultiWindow();
        frame.setVisible(true);
    }
}

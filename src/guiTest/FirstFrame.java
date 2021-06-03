/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTest;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author sengthavongphilavong
 */
public class FirstFrame extends JFrame{
    public FirstFrame() throws HeadlessException {
        super("First Frame");
        JLabel label = new JLabel("Hello First");
        getContentPane().add(label);
        pack();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
    
}

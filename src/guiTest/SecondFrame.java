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
public class SecondFrame extends JFrame{
    public SecondFrame() throws HeadlessException {
        super("Second Frame");
        JLabel label = new JLabel("Hello Second");
        getContentPane().add(label);
        pack();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
}

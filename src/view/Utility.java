/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author JAMEO
 */
public class Utility {
     //Center the form
    public static int rate = 0;
    public static void centerFrame(JFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Determine the new location of the window
        int w = frame.getSize().width;
        int h = frame.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;
        // Move the window
        frame.setLocation(x, y);
    }
    //Set icon to forms
    public static void setFormIcon(JFrame frame){
        ImageIcon icon = new ImageIcon("D:\\Programming\\SHOPING CART\\imagee.jpg");
        frame.setIconImage(icon.getImage());
    }
     public static void themeLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new SyntheticaClassyLookAndFeel());
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            e.printStackTrace();
        }
    }
   
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import javax.swing.UIManager;

/**
 *
 * @author mohamed shabana
 */
public class Shared {
     public static void themeLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

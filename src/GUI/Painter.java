/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import javax.swing.JFrame;
/**
 *
 *
 */
public class Painter {
    public static void main(String[] args) {
        Canvas canvasGUI = Canvas.getInstance();
        canvasGUI.setVisible(true);
        canvasGUI.setExtendedState(JFrame.MAXIMIZED_BOTH);   
    }
}

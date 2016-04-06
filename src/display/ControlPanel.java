/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

/**
 *
 * @author saqua
 */
//The main Control which sets up the game as an applet to be applied to websites and stuff
public class ControlPanel extends JApplet{
    
    
    private GameFrame display = new GameFrame();
    //set up the layout for the canvas
    public void init(){
        setLayout(new BorderLayout());
        setLayout(new FlowLayout());
        add(display);
    }
    //start method
    public void start(){
        display.start();
        
    }
    // stop method
    public void stop(){
        display.stop();
    }

   /* @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
    
}

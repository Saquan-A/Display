/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import State.StateMachine;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;

/**
 *
 * @author saqua
 */
// Obviously the Main for the program where the canvas and runnable are set up
//some stuff actually isnt need i was just to lazy to take them out cause i was trying different stuff you know how that goes
public class GameFrame extends Canvas implements Runnable{

    /* JFrame sFrame = new JFrame();
     JButton start = new JButton("Start Game");
        JButton rStart = new JButton("Restart Game");
        JButton exit = new JButton("Exit");
        
        JPanel sControl = new JPanel();*/
    
    
    /**
     * @param args the command line arguments
     */
   // main method where my Canvas is called to be displayed and set up
    // is also where i set and declare my Jpanel for buttons
    public static void main(String[] args) {
        // TODO code application logic here
        
        JButton start = new JButton("Start Game");
        JButton rStart = new JButton("Restart Game");
        JButton exit = new JButton("Exit");
        
        
        
        JPanel sControl = new JPanel();
        
        sControl.add(start);
        sControl.add(rStart);
        sControl.add(exit);
        //Anonymous Action preformed method for the buttons
        // i kept getting static on non static error so i had to do my actionPerformed this way
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
               System.exit(0);
                
            }
        });
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
             GameFrame display = new GameFrame();
                JFrame frame = new JFrame();
                frame.add(display);
                frame.pack();
                frame.setTitle("Space Invaders");
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setVisible(true);
                display.start();
                
            }
        });
        //I know you can use the dispose() method to get rid of a canvas
        // however since everything was done in the main i had no way of getting those instance variables to dispose
        //so i got to create a new one each time
        rStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                GameFrame display = new GameFrame();
                JFrame frame = new JFrame();
                frame.add(display);
                frame.pack();
                frame.setTitle("Space Invaders");
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setVisible(true);
                display.start();
            }
        });
       
       
        //Basic canvas display for the game
         GameFrame display = new GameFrame();
         JFrame frame = new JFrame();
         frame.add(sControl, BorderLayout.SOUTH);
         frame.add(display);
         frame.pack();
         frame.setTitle("Space Invaders");
         frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
         frame.setResizable(false);
         frame.setVisible(true);
         
//        display.start();
//        
        
    }
    //some really uneeded items
    private boolean running = false;
    private Thread thread;
    private Object Bolor;
    
    public synchronized void start(){
        if(running)
            return;
          
          running = true;
          
          thread = new Thread(this);
          thread.start();
    }
    
    public synchronized void stop() {
        if(!running)
            return;
        
        running = false;
      try {
        thread.join();
      } catch (InterruptedException e) {e.printStackTrace();}
    }
    //sets the Canvas width and height
    public static int WIDTH = 800, HEIGHT = 600;
    public int FPS; //for figureing out how many frames per second however i didnt ever add in the system.out.println for it
    
    public static StateMachine state;
    
    //basically just sets the size and will only move while it is in focus
    public GameFrame(){
       
        
        //this.add(rStart);
        this.setSize(WIDTH, HEIGHT);
        this.setFocusable(true);
        //bufferstrategy
        state = new StateMachine(this);
        state.setState((byte) 0);
    }
    @Override
    //All that extra timer stuff is just getting the frames for second and also how smooth i want 
    //the overall game to be
    public void run() {
        long timer = System.currentTimeMillis();
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        int frames = 0;
        
        this.createBufferStrategy(3);
        BufferStrategy bs = this.getBufferStrategy();
       while(running){
           long now = System.nanoTime();
           long updateLength = now - lastLoopTime;
           lastLoopTime =  now;
           double delta = updateLength / ((double) OPTIMAL_TIME);
           
           frames++;
           if(System.currentTimeMillis() - timer > 1000){
               timer += 1000;
               FPS = frames;
               frames = 0;
           }
           
           draw(bs);
           update(delta);
           try{
               Thread.sleep(((lastLoopTime - System.nanoTime()) + OPTIMAL_TIME) / 1000000);
               
           }catch(Exception e){};
           
       }
    }
    //draw bufferStrategy basically canvas's version of repainting
    public void draw(BufferStrategy bs){
        do{
       
            do{
                Graphics2D g = (Graphics2D) bs.getDrawGraphics();
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, WIDTH + 50, HEIGHT + 50);
                
                state.draw(g);
                
                g.dispose();
        
            }while(bs.contentsRestored());
            bs.show();
        }while(bs.contentsLost());
    }
    public void update(double delta){
        state.update(delta);
    }
//uneeded test
   /* @Override
    public void actionPerformed(ActionEvent ae) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

   
}

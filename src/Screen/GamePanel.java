/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screen;


import State.SuperStateMachine;
import display.GameFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import levels.StatusPanel;


/**
 *
 * @author saqua
 */
//GamePanel the thing that the user sees or whatever
public class GamePanel implements SuperStateMachine{

    private LaserCanon player;
   private StatusPanel level;
 
   
   public static int SCORE = 0; //keeping score
   
   private Font gameScreen = new Font("Arial", Font.PLAIN, 68); //displays the score
    
    public GamePanel(){
        
        player = new LaserCanon(GameFrame.WIDTH/2 - 50, GameFrame.HEIGHT - 75, 50, 50);
      
        level = new StatusPanel(player);
    }
    @Override
    public void update(double delta) {
        player.update(delta);
        level.update(delta);
    }

    @Override
    public void draw(Graphics2D g) {
       g.setColor(Color.white);
       g.drawString("Score: " + SCORE, 5, 15); //draws everything to the canvas
        
        player.draw(g);
       level.draw(g);
       
       
    }

    @Override
    public void init(Canvas canvas) {
        canvas.addKeyListener(player);
    }
    
}

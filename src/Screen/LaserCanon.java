/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screen;

import display.GameFrame;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author saqua
 */
//Player classes my base station, and draws the image.
public class LaserCanon implements KeyListener{
   private final double speed = 5.0;
    
    private BufferedImage pSprite;
    private Rectangle rect;
    private double xPos, yPos;
    private int width, height;
    
    
    private boolean left = false, right = false, shot = false;//sets up the booleans for movment and shoting
    
    public PlayerWeapons playerWeapons;
    //all the methods from drawing to getting the posistion.
    public LaserCanon(double xPos, double yPos, int width, int height){
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        
        rect = new Rectangle((int) xPos, (int) yPos, width, height);
        
        try{
            URL url = this.getClass().getResource("/Images/Ship.png");
            pSprite = ImageIO.read(url);
        }catch(IOException e){};
        
        playerWeapons = new PlayerWeapons();
    }
    
    public void draw(Graphics2D g){
        g.drawImage(pSprite, (int) xPos, (int) yPos, width, height, null);
        playerWeapons.draw(g);
        
    }
    
    public void update(double delta){
        if(right && !left && xPos < GameFrame.WIDTH - width){
            xPos += speed * delta;
            rect.x = (int) xPos;
        }if(!right && left && xPos > 10){
            xPos -= speed * delta;
            rect.x = (int) xPos;
            
        }
        playerWeapons.update(delta);
        
        if(shot){
            playerWeapons.shootBullet(xPos, yPos, 5, 5);
        }
    }
// key events needed for movment and shooting.
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();
        
        if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
           
            right = true;
            
        } else if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
          
            left = true;
        }
        if (key == KeyEvent.VK_SPACE){
            shot = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int key = ke.getKeyCode();
        if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
           
            right = false;
        } else if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
           
            left = false;
        }
        if (key == KeyEvent.VK_SPACE){
            shot = false;
        }
    }
    
}

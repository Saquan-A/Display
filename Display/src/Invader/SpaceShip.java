/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Invader;


import Screen.GamePanel;
import Screen.LaserCanon;
import Sprite.SpaceShipAnimation;
import Sprite.Squadron;
import display.GameFrame;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Random;

import Timer.Timer;






/**
 *
 * @author saqua
 */
//Spaceship class that sets up my red space ship and all that jazz
public class SpaceShip implements EnemyType{
  private double speed = 3.0d;
    
    private Rectangle rect;
    private SpaceShipAnimation enemySprite;
    
    
    private Random rand = new Random();
    int random = rand.nextInt(300);
    
    private int shootTime;
    private Timer shootTimer;
    public SpaceShip(double xPos, double yPos, int rows, int columns) {
        
        enemySprite = new SpaceShipAnimation(xPos, yPos, 200, "/Images/SpaceShip.png");
        enemySprite.setWidth(45);
        enemySprite.setHeight(45);
        enemySprite.setLimit(2);
        
        this.setRect(new Rectangle((int) enemySprite.getxPos(), (int) enemySprite.getyPos(), enemySprite.getWidth(), enemySprite.getHeight()) );
      enemySprite.setLoop(true);
        
       shootTimer = new Timer();
        shootTime = new Random().nextInt(9999999);
        
    }

    

    @Override
    public void draw(Graphics2D g) {
        enemySprite.draw(g);
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(double delta, LaserCanon player) {
         enemySprite.update(delta);
        
        enemySprite.setxPos(enemySprite.getxPos() - (delta * speed));
        this.getRect().x = (int) enemySprite.getxPos();
        
       
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changeDirection(double delta) {
        speed *= -1.15d;
        enemySprite.setxPos(enemySprite.getxPos() - (delta * speed ));
       this.getRect().x = (int) enemySprite.getxPos();
        
        enemySprite.setyPos(enemySprite.getyPos() + (delta * 15));
        this.getRect().y = (int) enemySprite.getyPos();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deathScene() {
        if(!enemySprite.isPlay())
            return false;
        
        if(enemySprite.isSpriteAnimDestroyed()) {
            return true;
        }
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  @Override
    public boolean collide(int i, LaserCanon player, ArrayList<EnemyType> enemys) {
            if(enemySprite.isPlay()){
            if(enemys.get(i).deathScene()){
                enemys.remove(i);
            
        }
        return false;
        }
      for(int w = 0; w < player.playerWeapons.weapons.size(); w++) {
          if(enemys != null && player.playerWeapons.weapons.get(w).collisionRect(((SpaceShip) enemys.get(i)).getRect())){
              enemySprite.resetLimit();
              enemySprite.setAnimationSpeed(120);
              enemySprite.setPlay(true, true);
              
              GamePanel.SCORE += (random + 50);
             return true;
              
              
          }
      }  
      return false;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
 

    @Override
    public boolean isOutOfBounds() {
        if(rect.x > 0 && rect.x < GameFrame.WIDTH - rect.width)
          return false;
      
      return true;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Rectangle getRect() {
        return rect;
    }

    /**
     * @param rect the rect to set
     */
    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

  
    
    
}

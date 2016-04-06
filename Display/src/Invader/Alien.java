/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Invader;


import Screen.GamePanel;
import Screen.LaserCanon;
import Sprite.Squadron;
import Timer.Timer;
import display.GameFrame;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author saqua
 */
//same as spaceship class basically sets everything up
public class Alien implements EnemyType{
    
    private double speed = 1.0d;
    
    private Rectangle rect;
    private Squadron enemySprite;
    
    private int shootTime;
    private Timer shootTimer;
    private Random rand = new Random();
    int random = rand.nextInt(20);

    public Alien(double xPos, double yPos, int rows, int columns){
        
        enemySprite = new Squadron(xPos, yPos, rows, columns, 300, "/Images/Invaders3.png");
        enemySprite.setWidth(25);//size of aliens that i wanted them to be on the 
        enemySprite.setHeight(25);
        enemySprite.setLimit(2);
        
        this.setRect(new Rectangle((int) enemySprite.getxPos(), (int) enemySprite.getyPos(), enemySprite.getWidth(), enemySprite.getHeight()) );
      enemySprite.setLoop(true);
        shootTimer = new Timer();
      shootTime = new Random().nextInt(12000);
      
      
    }
    
    @Override
    public void draw(Graphics2D g) {
        enemySprite.draw(g);
    }

    @Override
    public void update(double delta, LaserCanon player) {
        enemySprite.update(delta);
        
        enemySprite.setxPos(enemySprite.getxPos() - (delta * speed));
        this.getRect().x = (int) enemySprite.getxPos();
        
        
        
    }

    @Override
    public void changeDirection(double delta) {
        speed *= -1.15d;
        enemySprite.setxPos(enemySprite.getxPos() - (delta * speed ));
        this.getRect().x = (int) enemySprite.getxPos();
        
        enemySprite.setyPos(enemySprite.getyPos() + (delta * 15));
        this.getRect().y = (int) enemySprite.getyPos();
    }

    @Override
    public boolean deathScene() {
        if(!enemySprite.isPlay())
            return false;
        
        if(enemySprite.isSpriteAnimDestroyed()) {
            return true;
        }
        return false;
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
          if(enemys != null && player.playerWeapons.weapons.get(w).collisionRect(((Alien) enemys.get(i)).getRect())){
              enemySprite.resetLimit();
              enemySprite.setAnimationSpeed(120);
              enemySprite.setPlay(true, true);
              
              GamePanel.SCORE += (random + 10);
             return true;
              
              
          }
      }  
      return false;
    }

    @Override
    public boolean isOutOfBounds() {
      if(rect.x > 0 && rect.x < GameFrame.WIDTH - rect.width)
          return false;
      
      return true;
    }

    /**
     * @return the rect
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     * @param rect the rect to set
     */
    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    /**
     * @return the enemySprite
     */
    public Squadron getEnemySprite() {
        return enemySprite;
    }

    /**
     * @param enemySprite the enemySprite to set
     */
    public void setEnemySprite(Squadron enemySprite) {
        this.enemySprite = enemySprite;
    }

    
    
}

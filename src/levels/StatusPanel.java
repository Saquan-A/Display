/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels;


import Screen.LaserCanon;
import Invader.EnemyType;
import Invader.Alien;
import Invader.SpaceShip;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author saqua
 */
// class where basically the whole level is manageged aka the adding fo spirtes, draw/redraw methods everything that
// makes the game a game and show up is here
public class StatusPanel implements SuperLevel{
    
    private LaserCanon player;
    // array list for the sprite animations for aliens
    private ArrayList<EnemyType> enemies = new ArrayList<EnemyType>();
    private ArrayList<EnemyType> spaceShip = new ArrayList<EnemyType>();
 
    
    public StatusPanel(LaserCanon player){
        this.player = player;
        
        addEnemies();
//        for(int y = 0; y < 5; y++) {
//            for(int x = 0; x < 10; x++) {
//            
//           addEnemies();
//        }
//    }
    }

    @Override
    public void draw(Graphics2D g) {
       if(spaceShip == null)
           return;
       for(int i = 0; i < spaceShip.size(); i++){
       spaceShip.get(i).draw(g);
       }
        
        if(enemies == null)
            return;
        
        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }
        
    }

    @Override 
    public void update(double delta) {
        
        if(spaceShip == null)
            return;
        
        for(int i = 0; i < spaceShip.size(); i++){
            spaceShip.get(i).update(delta, player);
            
        }
        
        for(int g = 0; g < spaceShip.size(); g++){
            spaceShip.get(g).collide(g, player, spaceShip);
    }
        
        
        if(enemies == null)
            return;
        
        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).update(delta, player);
        }
        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).collide(i, player, enemies);
        }
        hasDirectionChange(delta);
        
        
    }

    @Override
    public void hasDirectionChange(double delta) {
//       if(spaceShip == null)
//           return;
//       
//       for(int i = 0; i < spaceShip.size(); i++){
//           if(spaceShip.get(i).isOutOfBounds()){
//               changeDirectionAllEnemys(delta);
//           }
//       }
        
        
        if(enemies == null)
            return;
        for(int i = 0; i < enemies.size(); i++){
            if(enemies.get(i).isOutOfBounds()){
                changeDirectionAllEnemys(delta);
            }
        }
    }

    @Override
    public void changeDirectionAllEnemys(double delta) {
       if (spaceShip == null)
           return;
       
       for(int i =0; i < spaceShip.size(); i++){
           if(spaceShip.get(i).isOutOfBounds()){
               spaceShip.get(i).changeDirection(delta);
           }
       }
        
        
        if(enemies == null)
            return;
        
        for(int i = 0; i < enemies.size(); i++){
            if(enemies.get(i).isOutOfBounds()){
                 DropDirectionAll(delta);
            }
      }
    }
    public void DropDirectionAll(double delta){
//        for(int i = 0; i <  spaceShip.size(); i++) {
//            spaceShip.get(i).changeDirection(delta);
//        }
        
        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).changeDirection(delta);
        }
    }
    
     @Override
    public boolean isGameOver() {
        return false;
        
    }

    @Override
    public void destory() {
    }

    @Override
    public void reset() {
    }
  //call the methods form my enemy classes and sets them up
    public void addEnemies() {
      
        EnemyType s = new SpaceShip(700, 25, 1, 1);
        spaceShip.add(s);
        
    
        
        for( int y = 0; y < 5; y++){
            for(int x = 0; x < 10; x++){
                EnemyType e = new Alien(150 + (x * 40), 25 + (y * 40), 1, 3);
                enemies.add(e);
            }
            
            
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Invader;


import Screen.LaserCanon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author saqua
 */
//set up all the methods for my aliens
public interface EnemyType {
    
    
     void draw(Graphics2D g);
     void update(double delta, LaserCanon player);
     void changeDirection(double delta);
    
     boolean deathScene();
     boolean collide(int i, LaserCanon player, ArrayList<EnemyType> enemys);
     boolean isOutOfBounds();

    
    
    
}

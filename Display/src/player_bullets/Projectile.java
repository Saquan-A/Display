/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player_bullets;

import display.GameFrame;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

/**
 *
 * @author saqua
 */
//Projectile class to shoot bullets form lasercanon
public class Projectile extends ProjectileWeaponType {

    private Rectangle bullet;
    private final double speed = 20.54;
    //speed of the bullet
    public Projectile(double xPos, double yPos, int width, int height){
        this.setxPos(xPos);
        this.setyPos(yPos);
        this.setWidth(width);
        this.setHeight(height);
        
        this.bullet = new Rectangle((int) getxPos(), (int) getyPos(), getWidth(), getHeight());
    }
    @Override
    public void draw(Graphics2D g) {
       if(bullet == null)
           return;
       
       g.setColor(Color.WHITE);
       g.fill(bullet);
    }

    @Override
    public void update(double delta) {
        if(bullet == null)
           return;
        
        this.setyPos(getyPos() - (delta * speed));
        bullet.y = (int) this.getyPos();
        isOutofBounds();
        
    }

    @Override//method for when the bullet hits something
    public boolean collisionRect(Rectangle rect) {
        if(this.bullet == null)
            return false;
        
        if(this.bullet.intersects(rect)){
           this.bullet = null;
            return true;
        }
        return false;
    }

    @Override
    public boolean collisionPoly(Polygon poly) {
        return false;
        
    }

    @Override
    public boolean destroy() {
        if(bullet == null)
            return true;
        
        return false;
    }

    @Override
    protected void isOutofBounds() {
       if(this.bullet == null)
           return;
       
       if(bullet.y < 0 || bullet.y > GameFrame.HEIGHT || bullet.x < 0 || bullet.x > GameFrame.WIDTH) {
          bullet = null; 
       }
    }
    
}

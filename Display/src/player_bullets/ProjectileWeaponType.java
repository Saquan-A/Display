/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player_bullets;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

/**
 *
 * @author saqua
 */
//class that sets up the projectile methods its going to inherit from there
public abstract class ProjectileWeaponType {
    
    private double xPos;
    private double yPos;
    private int width;
    private int height;

    public abstract void draw(Graphics2D g);
    public abstract void update(double delta);
    public abstract boolean collisionRect(Rectangle rect);
    public abstract boolean collisionPoly(Polygon poly);
    public abstract boolean destroy();
    
    protected abstract void isOutofBounds();
    /**
     * @return the xPos
     */
    public double getxPos() {
        return xPos;
    }

    /**
     * @param xPos the xPos to set
     */
    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    /**
     * @return the yPos
     */
    public double getyPos() {
        return yPos;
    }

    /**
     * @param yPos the yPos to set
     */
    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    
    
}

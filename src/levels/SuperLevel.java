/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels;

import java.awt.Graphics2D;

/**
 *
 * @author saqua
 */
//interface for the level methods
public interface SuperLevel {
    
    void draw(Graphics2D g);
    void update(double delta);
    void hasDirectionChange(double delta);
    void changeDirectionAllEnemys(double delta);
    
    boolean isGameOver();
    
    void destory();
    void reset();
    
}

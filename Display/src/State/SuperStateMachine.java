/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package State;

import java.awt.Canvas;
import java.awt.Graphics2D;

/**
 *
 * @author saqua
 */
// interface for statmachine
public abstract interface SuperStateMachine {
    
    public void update(double delta);
    public void draw(Graphics2D g);
    public void init(Canvas canvas);
    
}

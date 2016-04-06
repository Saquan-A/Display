/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screen;

import Timer.Timer;
import java.awt.Graphics2D;
import java.util.ArrayList;
import player_bullets.Projectile;
import player_bullets.ProjectileWeaponType;

/**
 *
 * @author saqua
 */
//Class thats actuall supposed to setup multiple weapons but you know time contrants had to cut
//a lot of more stuff i wanted to do to this class honestly
public class PlayerWeapons {
    
    private Timer timer;
    public ArrayList<ProjectileWeaponType> weapons = new ArrayList<ProjectileWeaponType>();
    
    public PlayerWeapons(){
        timer = new Timer();
    }
    public void draw(Graphics2D g){
        for(int i = 0; i < weapons.size(); i++){
            weapons.get(i).draw(g);
        }
    }
    public void update(double delta) {
         for(int i = 0; i < weapons.size(); i++){
            weapons.get(i).update(delta);
            if(weapons.get(i).destroy())
                weapons.remove(i);
        }
    }
    public void shootBullet(double xPos, double yPos, int width, int height){
       if(timer.timerEvent(250) && weapons.size() < 1) {
        weapons.add(new Projectile(xPos + 22, yPos +15, width, height));
    }
    }
}

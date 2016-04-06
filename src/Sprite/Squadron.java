/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprite;

import Timer.Timer;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author saqua
 */
//Squadron class set up the squadron and the animation
public class Squadron {
    private ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
    private byte currentSprite;
    
    private boolean loop = false;
    private boolean play = false;
    private boolean destroyAfterAnim = false;
    
    private Timer timer;
    private int animationSpeed;
    private double xPos, yPos;
    private int width, height;
    private int limit;
 
    //Literally Every method from sprite death to sprite movement to looping and playing animations, names are pretty self explaintory
    public Squadron(double xPos, double yPos, int rows, int columns, int animationSpeed, String imgPath){
        this.animationSpeed = animationSpeed;
        this.xPos = xPos;
        this.yPos = yPos;
        
        
        
        try{
            URL url = this.getClass().getResource(imgPath);                   
            BufferedImage pSprite = ImageIO.read(url);
            int spriteWidth = pSprite.getWidth() / columns;
            int spriteHeight = pSprite.getHeight() / rows;
            for(int y = 0; y < rows; y++) {
                for(int x = 0; x < columns; x++){
                    addSprite(pSprite, 0+(x * spriteWidth), 0 + (y * spriteHeight), spriteWidth, spriteHeight);
                }
            }
        }catch(IOException e){};
        timer = new Timer();
        limit = sprites.size() - 1;
    }
    
    public void draw(Graphics2D g){
        if(isSpriteAnimDestroyed())
            return;
        
        g.drawImage(sprites.get(currentSprite), (int) getxPos(), (int) getyPos(), width, height,  null);
    }
    
    public void update(double delta){
        if(isSpriteAnimDestroyed())
            return;
        
        if(loop && !play)
            loopAnimation();
        if(play && !loop)
            playAnimation();
            
    }
    
    public void stopAnimation(){
        loop = false;
        play = false;
                
    }
    
    public void resetSprite(){
        loop = true;
        play = true;
        currentSprite = 0;
    }
    
    private void loopAnimation(){
        if(timer.isTimerReady(animationSpeed) && currentSprite == limit){
            currentSprite = 0;
            timer.resetTimer();
        } else if(timer.timerEvent(animationSpeed) && currentSprite != limit){
            currentSprite++;
        }
        
    }
    
    private void playAnimation(){
        if(timer.isTimerReady(animationSpeed) && currentSprite != limit && !isDestroyAfterAnim()){
            play = false;
            currentSprite = 0;
        } else if(timer.isTimerReady(animationSpeed) && currentSprite == limit && isDestroyAfterAnim()){
            sprites = null;
        } else if(timer.timerEvent(animationSpeed) && currentSprite != limit){
            currentSprite++;
        }
    }
    
    
    public void addSprite(BufferedImage spriteMap, int xPos, int yPos, int width, int height){
        sprites.add(spriteMap.getSubimage(xPos, yPos, width, height));
    }
    
    public void PlayerAnimation(boolean play, boolean destroyAfterAnim){
        if(loop){
            loop = false;
        }
        
        this.play = play;
        this.setDestroyAfterAnim(destroyAfterAnim);
    }

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
     * @return the currentSprite
     */
    public byte getCurrentSprite() {
        return currentSprite;
    }

    /**
     * @param currentSprite the currentSprite to set
     */
    public void setCurrentSprite(byte currentSprite) {
        this.currentSprite = currentSprite;
    }

    /**
     * @return the loop
     */
    public boolean isLoop() {
        return loop;
    }

    /**
     * @param loop the loop to set
     */
    public void setLoop(boolean loop) {
        this.loop = loop;
    }
    
   public int getLimit(){
       return limit;
   }
   
   public void setLimit(int limit) {
       if(limit > 0) {
           this.limit = limit - 1;
       } else {
           this.limit = limit;
       }
   }
   public void resetLimit(){
       limit = sprites.size()-1;
   }
   public int getWidth(){
       return width;
   }
   public void setWidth(int width){
       this.width = width;
   }
   
   public int getHeight() {
       return height;
   }
   
   public void setHeight(int height) {
       this.height = height;
   }
   public boolean isPlay(){
       return play;
   }
   public int getAnimationSpeed() {
       return animationSpeed;
   }
   public void setAnimationSpeed(int animationSpeed) {
       this.animationSpeed = animationSpeed;
   }
   public void setPlay(boolean play, boolean destroyAfterAnim){
       if(loop){
           loop = false;
       }
       this.play = play;
       this.setDestroyAfterAnim(destroyAfterAnim);
   }
   public boolean isDestroyAfterAnim(){
        return destroyAfterAnim;
    }
    public void setDestroyAfterAnim(boolean destroyAfterAnim) {
        this.destroyAfterAnim = destroyAfterAnim;
        
    }
    public boolean isSpriteAnimDestroyed(){
        if(sprites == null)
            return true;
        
        return false;
        
    }
  
}

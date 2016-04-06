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
//This is the class to animate the space ship that flies back and forth across the screen
public class SpaceShipAnimation {
    //Varibles i was using for this class i think the names are pretty self explaintory 
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
    
    //method for when i make my spaceship how fast i want it to go across the screen and where i want it to be at etc etc
    public SpaceShipAnimation(double xPos, double yPos, int animationSpeed, String imgPath) {
        this.animationSpeed = animationSpeed;
        this.xPos = xPos;
        this.yPos = yPos;
        
        try{
            //try catch for getting the image/sprite of the spaceship and buffering it into the canvas
            URL url = this.getClass().getResource(imgPath);                   
            BufferedImage pSprite = ImageIO.read(url);

                    addSprite(pSprite, 0, 0, pSprite.getWidth(), pSprite.getHeight());
           
        }catch(IOException e){};
        timer = new Timer();
        
    }    
    //All the mthods that i was using for my program again i think the names are decent enough
    //so that each method is pretty self explaintory
     public void draw(Graphics2D g){
        if(isSpriteAnimDestroyed())
            return;
        
        g.drawImage(sprites.get(0), (int) getxPos(), (int) getyPos(), width, height,  null);
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

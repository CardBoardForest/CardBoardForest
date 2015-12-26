package gameWithAlex; 

// @author Colin Wakefield
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;

import javax.imageio.ImageIO;

public class MainCharacter implements Character
{
    // Rectangles for the player
     private Rectangle deathBox;
     private Rectangle collisiontop;
     private Rectangle collisionbottom;
     private Rectangle collisionleft;
     private Rectangle collisionright;
    // Character x Character y 
     private double x ;
     private double y  ;
    // animations for animators of the Character
     private Animaton a;
    // Moment variables
     private boolean MoveU;
     private boolean MoveD;
     private boolean MoveL;
     private boolean MoveR;
    // move speed
     final double movespeed = 3;
    //player scale
     final int PLAYER_SCALE = 64;
    // tile map import
    TileMap map;
   public MainCharacter(TileMap tm)
   {
       // set animation so no error
       a = new Animaton();
       //sets map
       map = tm;
       //seting the x and y 
       x = PLAYER_SCALE;
       y = PLAYER_SCALE;
       // set init so there are no errors
       deathBox = new Rectangle();
       collisiontop = new Rectangle();
       collisionbottom = new Rectangle();
       collisionleft = new Rectangle();
       collisionright = new Rectangle();
   }
   // a lot of set and get methods that do a lot of different things
   public boolean living(){return true;}
  
   public void moveup(boolean moveU ){this.MoveU=moveU;}
   public void movedown(boolean moveD ){this.MoveD=moveD;}
   public void moveleft(boolean moveL ){this.MoveL=moveL;}
   public void moveright(boolean moveR ){this.MoveR=moveR;}
   public boolean getmoveup(){return MoveU;}
   public boolean getmovedown(){return MoveD;}
   public boolean getmoveleft(){return MoveL;}
   public boolean getmoveright(){return MoveR;}
   public double getx(){return x;}
   public double gety(){return y;}
   public void setx(double x){this.x = x;}
   public void sety(double y){this.y = y;}
   
   public BufferedImage getImage()
   {
       try
       {
           if(MoveU)
           a.setFrames(getsprite("KidWalkBack.png"));
           else
                if(MoveD)
                    a.setFrames(getsprite("KidWalkFront.png"));
                else
                    if(MoveL)   
                        a.setFrames(getsprite("KidWalkLeft.png"));
                    else
                        if(MoveR)
                            a.setFrames(getsprite("KidWalkRight.png"));
                        else
                            return 
                                    getsprite("KidWalkFront.png")[0];
           a.setDelay(100);
           a.update();
           return a.getImage();
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return null;
       }
   }
   //takes spirits sheet and turns them in to spirits
    public BufferedImage[] getsprite(String s)
   {
       try
       {
       //BufferedImage bigImg = ImageIO.read(new File(s));
       BufferedImage bigImg = ImageIO.read(ResourceLoader.load(s));
       final int width = 32;
       final int height = 32;
       final int rows = 1;
       final int cols = 4;
       BufferedImage[] sprites = new BufferedImage[rows * cols];
       for (int i = 0; i < rows; i++)
       {
                for (int j = 0; j < cols; j++)
                {
                        sprites[(i * cols) + j] = bigImg.getSubimage(
                                    j * width,
                                    i * height,
                                    width,
                                    height
                                    );
                }
            }
       return sprites;
       }
       catch(IOException ex)
       {
           System.out.print("image not found");
           return null;
       }
       
    
   }
   public Rectangle getDeathbox()
   {
       return deathBox;
    }
    public void draw(Graphics g)
    {
        // gets the map offset
        int tx = map.getx();
        int ty =  map.gety();
        // draws the boy
        g.drawImage(getImage(), (int)((tx+x-PLAYER_SCALE/2)), (int)((ty+y-PLAYER_SCALE/2)), PLAYER_SCALE, PLAYER_SCALE, null);
        // draws the colition boxes
        Graphics2D g2 = (Graphics2D) g;
        g2.draw(collisiontop); 
        g2.draw(collisionright); 
        g2.draw(collisionbottom); 
        g2.draw(collisionleft); 
        g2.setColor(Color.red);
        g2.draw(deathBox); 
        
    }
    public void update()
    {
            // gets the map offset
            int tx = map.getx();
            int ty =  map.gety();
            
            // sets players character boxes 
            deathBox = new Rectangle(((int)(tx+x-PLAYER_SCALE/2))+8,(int)((ty+y-PLAYER_SCALE/2)+7), PLAYER_SCALE-18, PLAYER_SCALE-7);
            collisiontop = new Rectangle((int)((tx+x-PLAYER_SCALE/2))+9, (int)((ty+y-PLAYER_SCALE/2)+5),PLAYER_SCALE-(11+8),2);
            collisionright = new Rectangle((int)((tx+x-PLAYER_SCALE/2))+(PLAYER_SCALE-10),(int)((ty+y-PLAYER_SCALE/2))+8,2,PLAYER_SCALE-9);
            collisionbottom = new Rectangle((int)((tx+x-PLAYER_SCALE/2)+9), (int)((ty+y-PLAYER_SCALE/2))+PLAYER_SCALE,PLAYER_SCALE-(11+8),2);
            collisionleft = new Rectangle((int)((tx+x-PLAYER_SCALE/2)+6), (int)(ty+y-PLAYER_SCALE/2)+8,2,PLAYER_SCALE-9);
            
            //more boolean so i don't stop animations
            boolean moveup = getmoveup();
            boolean movedown = getmovedown() ;
            boolean moveleft = getmoveleft();
            boolean moveright= getmoveright();
                                    
            //check collision with the collision boxes
            for(int row = 0; row < map.getHeight(); row++)
            {
                for(int col = 0; col < map.getWidth(); col++)
                {
                        //collision check and tells if should move
                        if(collisiontop.intersects(map.getTile(row, col).getRectangle())&& map.getTile(row, col).getType().equals("cantMove"))
                        {
                            moveup = false;
                        }
                        if(collisionbottom.intersects(map.getTile(row, col).getRectangle())&& map.getTile(row, col).getType().equals("cantMove"))
                        {
                            movedown = false;
                        }   
                        if(collisionright.intersects(map.getTile(row, col).getRectangle())&& map.getTile(row, col).getType().equals("cantMove"))
                        {
                            moveright = false;
                        }
                        if(collisionleft.intersects(map.getTile(row, col).getRectangle())&& map.getTile(row, col).getType().equals("cantMove"))
                        {
                            moveleft = false;
                        }
                        //checkes colision for the kill blocks
                        if(deathBox.intersects(map.getTile(row, col).getRectangle())&& map.getTile(row, col).getType().equals("kill"))
                        {
                            //System.out.println("dead");
                        }
                }
            }
            // moves them 
            if(moveup){ y = y - movespeed;}
            if(movedown){y = y + movespeed;}
            if(moveleft){x = x - movespeed;}
            if(moveright){x = x + movespeed;}
            

            // moves map 
            map.setx((int)(Main.WIDTH / 2 - x));
            map.sety((int)(Main.HEIGHT / 2 - y));
                
        }
    
}



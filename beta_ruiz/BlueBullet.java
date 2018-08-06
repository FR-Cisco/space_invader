import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.geom.*;
import sun.audio.*;

/** 
this is the defender's bullet 
@Author Francisco Ruiz
@Version 5/12/2016
*/
public class BlueBullet extends JPanel
{
   /** defender ship */
   private Defender base;
   
   /** neccessary for sound */
   private InputStream inputStream;
   
    /** ship as buffered image */
   private BufferedImage myImage_bluebullet;
   
   /** app pixel width */
   public static final int APP_WIDTH = 1300;
   
   /** app pixel height */
   public static final int APP_HEIGHT = 800;
   
   /** draw flag */
   private boolean draw_b;
   
   /** fire flag */
   private boolean canFire;
   
   /** x pos of the bullet */
   private int point_x;
   
   /** y pos of the bullet */
   private int point_y;
   
   /** 
   initializes a bullet off screen
   @param bb this is the defending ship
   */
   public BlueBullet(Defender bb)  
   {
      base = bb;
      draw_b = false;
      canFire = true;
      point_x = APP_WIDTH / 2;
      point_y = 1000;
      
      try
      {
         myImage_bluebullet = ImageIO.read(new File("BlueBullet.png"));
      }
      catch(IOException ioe)
      {
      
      }
          
      myImage_bluebullet = getScaledImage(myImage_bluebullet,50,50);
   }
    
   /**
   draws the bullet where the x and y pos are at
   @param g2 this is the drawing param
   */  
   public void drawMe(Graphics2D g2)
   {
      g2.drawImage(myImage_bluebullet, point_x, point_y, null);
   }
   
   /** sets the position of the bullet */
   public void fire()
   {
      if (canFire == true)
      {
         point_x = base.get_X() + 7;
         point_y = base.get_Y() -15;
         shootSound();
         canFire = false;
      }
   }
   
   /** moves the bullet up */
   public void move()
   {
      point_y = point_y - 5;
   }
   
   /** resets the bullet if it goes out of bounds */
   public void checkBounds()
   {
      if( canFire == false && point_y <= 80)
      {
         reset();
      }  
   }
   
   /** sets the bullet back offscreen */
   public void reset()
   {
      point_x = APP_WIDTH / 2;
      point_y = 1000;
      canFire = true;
   }
   
   /** gets x pos */
   public int get_X()
   {
      return point_x;
   }
   
   /** gets the y pos */
   public int get_Y()
   {
      return point_y;
   }
   
   /** get the time left before next fire */
   public boolean getCF()
   {
      return canFire;
   }
   
   public void shootSound() 
   {
      try 
      {
         inputStream = getClass().getResourceAsStream("shoot.wav");
         AudioStream audioStream = new AudioStream(inputStream);
         //AudioPlayer.player.start(audioStream);
      }
      catch (Exception e)
      {
      }
   }
   
   /**
   resizes buffered images
   @param src This is the desired image to be resized
   @param w This is the desired width
   @param h This is the desired height
   */
   private BufferedImage getScaledImage(BufferedImage src, int w, int h)
   {
      int finalw = w;
      int finalh = h;
      double factor = 1.0d;
      
      if(src.getWidth() > src.getHeight())
      {
         factor = ((double)src.getHeight()/(double)src.getWidth());
         finalh = (int)(finalw * factor);                
      }
      else
      {
         factor = ((double)src.getWidth()/(double)src.getHeight());
         finalw = (int)(finalh * factor);
      }   
   
      BufferedImage resizedImg = new BufferedImage(finalw, finalh, 
         BufferedImage.TRANSLUCENT);
      Graphics2D g2 = resizedImg.createGraphics();
      
      g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
         RenderingHints.VALUE_INTERPOLATION_BILINEAR);
      g2.drawImage(src, 0, 0, finalw, finalh, null);
      g2.dispose();
      
      return resizedImg;
   }
}
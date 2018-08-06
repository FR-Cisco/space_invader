import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.geom.*;


public class EnemyBullet
{
   
   private int point_x;
   private int point_y;
   
   private boolean canShoot;
   
    /** ship as buffered image */
   private BufferedImage myImage_enemybullet;
   
   /** app pixel width */
   public static int APP_WIDTH = 1300;
   
   /** app pixel height */
   public static int APP_HEIGHT = 800;
   
   public EnemyBullet(int type)
   {
      point_x = -100;
      point_y = 500;
      
      if(type == 1)
      {
         try
         {
            myImage_enemybullet = ImageIO.read(new File("BlueBullet.png"));
         }
         catch(IOException ioe)
         {
         
         }
         myImage_enemybullet = getScaledImage(myImage_enemybullet, 40,40);
      }
      else if(type == 2)
      {
         try
         {
            myImage_enemybullet = ImageIO.read(new File("MediumBullet.png"));
         }
         catch(IOException ioe)
         {
         
         }
         myImage_enemybullet = getScaledImage(myImage_enemybullet, 50,50);
      }
      else  //if(type == 3)
      {
         try
         {
            myImage_enemybullet = ImageIO.read(new File("HardBullet.png"));
         }
         catch(IOException ioe)
         {
         
         }
         myImage_enemybullet = getScaledImage(myImage_enemybullet, 60,60);
      }
   }
   
   public void drawMe(Graphics2D g2)
   {
      g2.drawImage(myImage_enemybullet, point_x, point_y, null);
      //g2.setStroke(new BasicStroke(1));
      //g2.draw(new Rectangle2D.Double(point_x+21, point_y+17, 12, 17));
   }
   
   public void move()
   {
      point_y = point_y + 5;
   }
   
   public void reset()
   {
      point_x = -100;
      point_y = 500;
   }
   
   public int get_X()
   {
      return point_x;
   }
   
   public int get_Y()
   {
      return point_y;
   }
   
   public boolean getCS()
   {
      return canShoot;
   }
   
   public void checkBounds()
   {
      if(point_y >= 620)
      {
         reset();
      }  
   }
   
   public void fire(int x, int y)
   {
      point_x = x + 7;
      point_y = y - 15;
   }
   
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
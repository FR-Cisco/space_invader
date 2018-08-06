import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

public class RedBullet extends JPanel
{
   private RedEnemy RE;
   
   private int point_x;
   
   private int point_y;
   
    /** ship as buffered image */
   private BufferedImage myImage_redbullet;
   
   /** app pixel width */
   public static int APP_WIDTH = 1300;
   
   /** app pixel height */
   public static int APP_HEIGHT = 800;
   
   private boolean canFire;


   public RedBullet(RedEnemy red)
   {
      RE =  red;
      point_x = RE.get_X();
      point_y = RE.get_Y();
      
      try
      {
         myImage_redbullet = ImageIO.read(new File("RedBullet.png"));
      }
      catch(IOException ioe)
      {
      
      }
          
      myImage_redbullet = getScaledImage(myImage_redbullet, 10,10);
   }
   
   public void drawMe(Graphics2D g2)
   {
      g2.drawImage(myImage_redbullet, point_x, point_y, null);
   }
   
   public void move()
   {
      point_y = point_y + 5;
   }
   
   public void reset()
   {
      point_x = RE.get_X();
      point_y = RE.get_Y();
   }
   
   public int get_X()
   {
      return point_x;
   }
   
   public int get_Y()
   {
      return point_y;
   }
   
   public void checkBounds()
   {
      if(point_y >= 620)
      {
         reset();
      }  
   }
   
   public boolean getCF()
   {
      return canFire;
   }
   
   public void fire()
   {
      point_x = RE.get_X() + 7;
      point_y = RE.get_Y() -15;
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
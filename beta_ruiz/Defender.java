import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.geom.*;

/** defender ship class */
public class Defender extends JPanel
{
   /** background object */
   private Background BFrame;
   
   /** ship as buffered image */
   private BufferedImage myImage_base;
   
   /** app pixel width */
   public static int APP_WIDTH = 1300;
   
   /** app pixel height */
   public static int APP_HEIGHT = 800;
   
   private int point_x;
   
   private int point_y;
   
   /**
   loads image and converts it to a JLabel and initializes keybindings
   @param bkg This is the background object
   */
   public Defender(Background bkg)
   {
      BFrame = bkg;
      
      point_x = APP_WIDTH / 6;
      point_y = 600;
      
      try
      {
         myImage_base = ImageIO.read(new File("Base-Ship.png"));
      }
      catch(IOException ioe)
      {
      
      }
      
      myImage_base = getScaledImage(myImage_base,70,70);
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
   
   public void drawMe(Graphics2D g2)
   {
      g2.drawImage(myImage_base, point_x, point_y, null);
      //g2.setStroke(new BasicStroke(1));
      //g2.draw(new Rectangle2D.Double(point_x + 10, point_y+10, 50, 40));
   }
   
   public void drawLives(Graphics2D g2, int val)
   {
      if (val > -1)
      {
         for (int x = 0; x < val; x++)
         {
            g2.drawImage(myImage_base, 950 + 70*x , 10, null);
         }
      }
   }
   
   public int get_X()
   {
      return point_x;
   }
   
   public int get_Y()
   {
      return point_y;
   }
   
   public void changeX(int val)
   {
      point_x = point_x + val;
   }
   
   public void checkBounce()
   {
      if(this.point_x > APP_WIDTH - 80)
      {
         point_x = 5;
      }
      else if (this.point_x < -5)
      {
         point_x = APP_WIDTH - 80;
      }
   }
   
   public void gameAction(BlueBullet b, boolean r, boolean l, boolean s)
   {
      if (r == true && s == true)
      {
         changeX(10);
         b.fire();
      }
      
      else if(l == true && s == true)
      { 
         changeX(-10);
         b.fire();
      }
      
      else if (r == true)
      {
         changeX(10);
      }
      
      else if(l == true)
      {
         changeX(-10);
      }
      else if(s == true)
      {
         b.fire();
      }
   }
   
   public void reset()
   {
      point_x = APP_WIDTH / 2;
      point_y = 600;
   }
}
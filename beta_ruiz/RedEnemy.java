import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.geom.*;
import sun.audio.*;

public class RedEnemy extends JPanel implements Enemy
{
   /** background object */
   private Background BFrame;
   private InputStream inputStream;
   
   /** ship as buffered image */
   private BufferedImage myImage_mystery;
   
   /** app pixel width */
   public static int APP_WIDTH = 1300;
   
   /** app pixel height */
   public static int APP_HEIGHT = 800;
   
   private boolean canMove;
   
   private int point_x;
   private int point_y;
   private int countdown;
   
   private Rectangle2D.Double temp;
   private Rectangle2D.Double temp_other;

   public RedEnemy(Background bkg)
   {
      BFrame = bkg;
      point_x = 0;
      point_y = 100;
      countdown = 0;
      canMove = true;
      
      try
      {
         myImage_mystery = ImageIO.read(new File("Mystery-Ship.png"));
      }
      catch(IOException ioe)
      {
      
      }
      
      myImage_mystery = getScaledImage(myImage_mystery,70,70);
   }
   
   public void drawMe(Graphics2D g2)
   {
      g2.drawImage(myImage_mystery, point_x, point_y, null);
      //g2.draw(new Rectangle2D.Double(point_x, point_y, 60, 30));
   }
   
   public int get_X()
   {
      return point_x;
   }
   
   public int get_Y()
   {
      return point_y;
   }
   
   public void changeX()
   {
      point_x = point_x + 15;
   }
   
   public void bounceX()
   {
   }
   
   public void bounceY()
   {
   }
   
   public void checkForCollision(Enemy e)
   {
   }
   
   public void checkBounds()
   {
      if (point_x > APP_WIDTH)
      {
         canMove = false;
         reset();
      }
   }
   
   public void reset()
   {
      point_y = 100;
      point_x = -70;
      countdown = 10;
      canMove = true;
   }
   
   public int getCD()
   {
      return countdown;
   }
   
   public void changeCD(int val)
   {
      countdown = countdown + val;
   }
   
   public int checkIfHit(BlueBullet b)
   {
      temp = 
         new Rectangle2D.Double(point_x, point_y, 60, 30);
    
      temp_other =
         new Rectangle2D.Double(b.get_X(), b.get_Y(), 10, 10);
         
      if(temp.intersects(temp_other))
      {
         this.reset();
         b.reset();
         redSounds(1);
         return 1;
      }
      return 0;
   }
   
   public int registerKill(RedBullet rb, Defender d)
   {
      temp = 
         new Rectangle2D.Double(rb.get_X(), rb.get_Y(), 0.5, 0.5);
         
      temp_other =
         new Rectangle2D.Double(d.get_X() +10, d.get_Y(), 50, 50);
       
      if(temp.intersects(temp_other))
      {
         d.reset();
         rb.reset();
         redSounds(2);
         return -1;
      }
      return 0;
   }
   
   public void redSounds(int val) 
   {
      try 
      {
         if(val == 1)
         {
            inputStream = getClass()
               .getResourceAsStream("ufo_highpitch.wav");
         }
         if(val == 2)
         {
            inputStream = getClass()
               .getResourceAsStream("explosion.wav");
         }
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
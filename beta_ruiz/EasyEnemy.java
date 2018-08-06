import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.geom.*;
import sun.audio.*;

public class EasyEnemy extends JPanel implements Enemy
{
   /** background object */
   private Background BFrame;
   private InputStream inputStream;
   
   /** ship as buffered image */
   private BufferedImage myImage_10Y;
   
   /** app pixel width */
   public static final int APP_WIDTH = 1300;
   
   /** app pixel height */
   public static final int APP_HEIGHT = 800;
   
   public static final int STARTY = 350;
   public static final int STARTX = 320;
   public static final int SPEED = 8;
   
   private boolean invade;
   
   private int point_x;
   private int point_y;
   private int mySpeedX;
   private int mySpeedY;
   private int ID;
   private int type;
   
   private Rectangle2D.Double temp;
   private Rectangle2D.Double temp_other;

   
   public EasyEnemy(Background bkg, int valx, int valy, int id, int typ)
   {
      BFrame = bkg;
      point_x = STARTX + valx;
      point_y = STARTY + valy;
      mySpeedX = SPEED;
      mySpeedY = SPEED;
      ID = id;
      type =  typ;
      invade = false;
      
      try
      {
         myImage_10Y = ImageIO.read(new File("10Y-Ship.png"));
      }
      catch(IOException ioe)
      {
      
      }
      
      myImage_10Y = getScaledImage(myImage_10Y,50,50);
   }
   
   public void drawMe(Graphics2D g2)
   {
      g2.drawImage(myImage_10Y, point_x, point_y, null);
   }
   
   public int getID()
   {
      return ID;
   }
   
   public int get_X()
   {
      return point_x;
   }
   
   public int get_Y()
   {
      return point_y;
   }
   
   public int getType()
   {
      return type;
   }
   
   public void move()
   {
      if(type == 3)
      {
         point_x = point_x + mySpeedX - mySpeedX / 2;
      }
      else
      {
         point_x = point_x + mySpeedX;
         if(type == 4)
         {
            point_y = point_y + mySpeedY;
         }
      }
   }
   
   public void bounceX()
   {
   }
   public void bounceY()
   {
   }

   public void checkBounds()
   {
      if (point_x >= APP_WIDTH -120)
      {
         mySpeedX =  -SPEED;
         if(type == 3)
         {
            point_y = point_y + 2;
         }
      }
      else if(point_x <= 40)
      {
         mySpeedX = SPEED;
      }
      if(type  == 4)
      {
         if (point_y >= APP_HEIGHT -400)
         {
            mySpeedY =  -SPEED;
         }
         else if(point_y <= 40)
         {
            mySpeedY = SPEED;
         }
      }
   }
   
   public void gameAction(EnemyBullet eb, int x, int y)
   {
      eb.fire(x, y);
   }
   
   public int checkIfHit(BlueBullet b)
   {
      temp = 
         new Rectangle2D.Double(point_x +10, point_y, 35, 35);
    
      temp_other =
         new Rectangle2D.Double(b.get_X() + 21, b.get_Y() + 12, 10, 25);
         
      if(temp.intersects(temp_other))
      {
         b.reset();
         easySounds(1);
         return 10;
      }
      else
      {
         return 0;
      }
   }
   
   public int registerKill(EnemyBullet eb, Defender d)
   {
      temp = 
         new Rectangle2D.Double(eb.get_X() + 21, eb.get_Y() + 17, 12, 17);
         
      temp_other =
         new Rectangle2D.Double(d.get_X() +10, d.get_Y() + 10, 50, 40);
       
      if(temp.intersects(temp_other))
      {
         d.reset();
         eb.reset();
         easySounds(2);
         return -1;
      }
      return 0;
   }
   
   public void checkForCollision(Enemy e)
   {
      temp = new Rectangle2D.Double(point_x +10, point_y, 
         100, 100);
      temp_other = new Rectangle2D.Double(e.get_X(), e.get_Y(),
         100, 100);
      if(temp.intersects(temp_other))
      {
      }
   }
      
   
   public void easySounds(int val) 
   {
      try 
      {
         if(val == 1)
         {
            inputStream = getClass()
               .getResourceAsStream("invaderkilled.wav");
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
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

/** home screen panel */
public class HomeScreen  extends JPanel
{
   private Background BFrame;
   
   public static int APP_WIDTH = 1300;
   public static int APP_HEIGHT = 800;
   
   public static final int BUTTON_WIDTH = 200;
   public static final int BUTTON_HEIGHT = 50;
   
   private Rectangle2D.Double playButton;
   private Rectangle2D.Double highScoresButton;
   
   private BufferedImage myImage_10;
   private BufferedImage myImage_20;
   private BufferedImage myImage_40;
   private BufferedImage myImage_Mystery;
   
   /**
   loads in the images
   @param bkg this is the the baground object 
   */
   public HomeScreen(Background bkg)
   {  
      BFrame = bkg;
      addMouseListener(new MyButtonListener());
      
      try
      {
         myImage_10 = ImageIO.read(new File("10-Ship.png"));
         myImage_20 = ImageIO.read(new File("20-Ship.png"));
         myImage_40 = ImageIO.read(new File("40-Ship.png"));
         myImage_Mystery = ImageIO.read(new File("Mystery-Ship.png"));
      }
      catch(IOException ioe)
      {
      
      } 
   }
   
   /**
   paints the screen black along with all the title, point values, 
   buttons, and designer
   @param g is the paintComponent variable
   */
   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      
      Rectangle2D.Double back = 
         new Rectangle2D.Double(0,0,APP_WIDTH, APP_HEIGHT);
      g2.setColor(Color.BLACK);
      g2.fill(back);
      
      g2.setColor(Color.WHITE);
      g2.setFont(new Font("Arial", Font.BOLD, 130));
      g2.drawString("SPACE", APP_WIDTH / 3,200);
      
      g2.setColor(Color.GREEN);
      g2.setFont(new Font("Arial", Font.BOLD, 90));
      g2.drawString("INVADERS", APP_WIDTH / 3,300);
       
      g2.setColor(Color.WHITE);
      g2.setFont(new Font("Arial", Font.BOLD, 35));
      
      g2.drawString("         = 10 PTS", APP_WIDTH / 3 + APP_WIDTH / 15, 400);
      
      g2.drawString("         = 20 PTS", APP_WIDTH / 3 + APP_WIDTH / 15, 440);
      
      g2.drawString("         = 30 PTS", APP_WIDTH / 3 + APP_WIDTH / 15, 480);
      
      g2.drawString("         = 50 PTS", APP_WIDTH / 3 + APP_WIDTH / 18, 520);
      
      int buttonX = (APP_WIDTH / 2) - (BUTTON_WIDTH / 2);
      int buttonY = 600;
      
      // playButton = new Rectangle2D.Double(buttonX, buttonY + 25, BUTTON_WIDTH + 15, 
         // BUTTON_HEIGHT + 15);
      // g2.setColor(Color.BLACK);
      // g2.draw(playButton);
      
      g2.setColor(Color.WHITE);
      //g2.drawString("High Scorers", buttonX, buttonY + 55);
      
      highScoresButton = new Rectangle2D.Double(buttonX, buttonY - 45, 
         BUTTON_WIDTH + 15, BUTTON_HEIGHT + 10);
      g2.setColor(Color.BLACK);
      g2.draw(highScoresButton);
      
      g2.setColor(Color.WHITE);
      g2.drawString("Play Game", buttonX + 20, buttonY);
      
      g2.setFont(new Font("Arial", Font.BOLD, 15));
      g2.setColor(Color.GREEN);
      g2.drawString("By Francisco Ruiz", buttonX + 50, buttonY + 90);
      
      g2.drawImage(getScaledImage(myImage_10,50,50), 
         APP_WIDTH / 3 + APP_WIDTH / 10 - 7, 370, null);     
      
      g2.drawImage(getScaledImage(myImage_20,50,50), 
         APP_WIDTH / 3 + APP_WIDTH / 10 - 7, 410, null);
         
      g2.drawImage(getScaledImage(myImage_40,70,70),
         APP_WIDTH / 3 + APP_WIDTH / 11, 450, null);
         
      g2.drawImage(getScaledImage(myImage_Mystery,70,70), 
         APP_WIDTH / 3 + APP_WIDTH / 15 + 10, 490, null);
   } 
   
   /**
   handles the mouse commands for the buttons
   */   
   private class MyButtonListener implements MouseListener
   {
      /** 
      turns the play and high scores into buttons with actions
      @param e this is the mouse event parameter
      */
      public void mousePressed(MouseEvent e)
      {
         int mouseX = e.getX();
         int mouseY = e.getY();
         
         if(highScoresButton.contains(mouseX, mouseY))
         {
            BFrame.switchScreen(Background.S_ALL);
         }
         else if(playButton.contains(mouseX, mouseY))
         {
            //BFrame.switchScreen(Background.L_Board);
         }
      }
      
      /** 
      not used
      @param e This is the mouse event parameter
      */
      public void mouseReleased(MouseEvent e)
      {
      
      }
      
      /** 
      not used
      @param e This is the mouse event parameter
      */
      public void mouseClicked(MouseEvent e)
      {
      
      }
      
      /** 
      not used
      @param e This is the mouse event parameter
      */
      public void mouseEntered(MouseEvent e)
      {
      
      }
      
      /** 
      not used
      @param e This is the mouse event parameter
      */
      public void mouseExited(MouseEvent e)
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
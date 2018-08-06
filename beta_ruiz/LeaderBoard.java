import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;

/** the setting for the first level level **/
public class LeaderBoard extends JPanel
{
   
   private Background BFrame;
   
   private Rectangle2D.Double NewGameButton;
   private Rectangle2D.Double NextTenButton;
   
   public static int APP_WIDTH = 1300;
   public static int APP_HEIGHT = 800;
   
   public static final int BUTTON_WIDTH = 200;
   public static final int BUTTON_HEIGHT = 50;
   
   /**
   Sets up all the buttons and adds it to a frame near the bottom as well as draws the 
   current leader board
   */
   public LeaderBoard(Background bkg)
   {   
      BFrame = bkg;
      addMouseListener(new MyButtonListener());
   }
   
   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
   
      Rectangle2D.Double back = 
         new Rectangle2D.Double(0,0,APP_WIDTH, APP_HEIGHT);
      g2.setColor(Color.BLACK);
      g2.fill(back);
        
      int buttonX = (APP_WIDTH / 2) - (BUTTON_WIDTH / 2);
      int buttonY = 600;
      
      g2.setColor(Color.WHITE);
      g2.setFont(new Font("Arial", Font.BOLD, 35));
      g2.drawString("Return to Main Menu", buttonX -55, buttonY +45);
      
      NewGameButton = new Rectangle2D.Double(buttonX -65, buttonY, 
         BUTTON_WIDTH + 170, BUTTON_HEIGHT + 15);
      g2.setColor(Color.BLACK);
      g2.draw(NewGameButton);  
   }
   
   private class MyButtonListener implements MouseListener
   {
      public void mousePressed(MouseEvent e)
      {
         int mouseX = e.getX();
         int mouseY = e.getY();
         
         if(NewGameButton.contains(mouseX, mouseY))
         {
            BFrame.switchScreen(Background.main);
         }
         //else if(NextTenButton.contains(mouseX, mouseY))
         {
            // BFrame.switchScreen(Background.L2_Board);
         }
      }
      
      public void mouseReleased(MouseEvent e)
      {
      
      }
      
      public void mouseClicked(MouseEvent e)
      {
      
      }
      
      public void mouseEntered(MouseEvent e)
      {
      
      }
      
      public void mouseExited(MouseEvent e)
      {
      
      }
   
   }

}
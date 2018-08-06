import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class LevelChooser extends JPanel
{
   private Background BFrame;
   
   public static int APP_WIDTH = 1300;
   public static int APP_HEIGHT = 800;
   public static final int BUTTON_WIDTH = 200;
   public static final int BUTTON_HEIGHT = 50;
   public static final int buttonX = APP_WIDTH / 2;
   public static final int buttonY = APP_HEIGHT / 5;
   
   private Rectangle2D.Double easyButton;
   private Rectangle2D.Double mediumButton;
   private Rectangle2D.Double hardButton;
   private Rectangle2D.Double beyondHopeButton;
   
   public LevelChooser(Background bkg)
   {
      BFrame = bkg;
      addMouseListener(new MyButtonListener());
   }
   
   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      drawBackground(g2);
      drawEButton(g2);
      drawMButton(g2);
      drawHButton(g2);
      drawBHButton(g2);
   }
   
   public void drawBHButton(Graphics2D g2)
   {
      beyondHopeButton = new Rectangle2D.Double(buttonX -130 , buttonY + 440, 
         BUTTON_WIDTH + 50, BUTTON_HEIGHT + 10);
      g2.setColor(Color.BLACK);
      g2.draw(beyondHopeButton);
      g2.setColor(Color.RED);
      g2.drawString("Beyond Hope", buttonX -120, buttonY*4);
      g2.drawString("Goodluck", 
         buttonX -80, buttonY*4 + 40); 
   }
   
   public void drawHButton(Graphics2D g2)
   {
      hardButton = new Rectangle2D.Double(buttonX -80 , buttonY + 280, 
         BUTTON_WIDTH - 50, BUTTON_HEIGHT + 10);
      g2.setColor(Color.BLACK);
      g2.draw(hardButton);
      g2.setColor(Color.YELLOW);
      g2.drawString("Hard", buttonX -50, buttonY*3);
      g2.drawString("More difficult enemies", 
         buttonX -190, buttonY*3 + 40);
   }
   
   public void drawMButton(Graphics2D g2)
   {
      mediumButton = new Rectangle2D.Double(buttonX -80 , buttonY + 120, 
         BUTTON_WIDTH - 50, BUTTON_HEIGHT + 10);
      g2.setColor(Color.BLACK);
      g2.draw(mediumButton);
      g2.setColor(Color.GREEN);
      g2.drawString("Medium", buttonX - 70, buttonY*2);
      g2.drawString("Same easy enemies but now play for as long as possible", 
         buttonX -440, buttonY*2 + 40);
   }
   
   public void drawEButton( Graphics2D g2)
   {
      easyButton = new Rectangle2D.Double(buttonX -80 , buttonY-50, 
         BUTTON_WIDTH - 50, BUTTON_HEIGHT + 10);
      g2.setColor(Color.BLACK);
      g2.draw(easyButton);
      g2.setFont(new Font("Arial", Font.BOLD, 35));
      g2.setColor(Color.WHITE);
      g2.drawString("Easy", buttonX -50, buttonY);
      g2.drawString("Defeat enemies as quickly as possible", buttonX -325, 
         buttonY + 45);
   }
   
   public void drawBackground(Graphics2D g2)
   {
      Rectangle2D.Double back = 
         new Rectangle2D.Double(0,0,APP_WIDTH, APP_HEIGHT);
      g2.setColor(Color.BLACK);
      g2.fill(back);
      g2.setFont(new Font("Arial", Font.BOLD, 20));
      g2.setColor(Color.WHITE);
      g2.drawString("Level Select", buttonX -70, 20);
   }
   
   private class PopUp extends JPopupMenu implements ActionListener
   {
      private Background BFrame;
      private JMenuItem B1;
      private JMenuItem B2;
      
      public PopUp(Background bkg)
      {
         BFrame = bkg;
         
         B1 = new JMenuItem("Quit Game");
         add(B1);
         B1.addActionListener(this);
         addSeparator();
         B2 = new JMenuItem("Mute Game Sound"); 
         B2.addActionListener(this); 
         add(B2);
      }
      
      public void actionPerformed(ActionEvent e) 
      {
         if (e.getSource() == B1)
         {
            BFrame.switchScreen(Background.main);
         }
      }
      
      public void mouseDragged(MouseEvent e){}
   }
   
   private class MyButtonListener implements MouseListener
   {
      public void mousePressed(MouseEvent e)
      {
         int mouseX = e.getX();
         int mouseY = e.getY();
         
         if(easyButton.contains(mouseX, mouseY))
         {
            Space_1.beginGame = true;
            Space_1.pause = false;
            BFrame.switchScreen(Background.S_One);
         }
         
         if(mediumButton.contains(mouseX, mouseY))
         {
            Space_2.beginGame = true;
            Space_2.pause =  false;
            BFrame.switchScreen(Background.S_Two);
         }
         
         if(hardButton.contains(mouseX, mouseY))
         {
            Space_3.beginGame = true;
            Space_3.pause =  false;
            BFrame.switchScreen(Background.S_Three);
         }
         
         if(beyondHopeButton.contains(mouseX, mouseY))
         {
            Space_4.beginGame = true;
            Space_4.pause =  false;
            BFrame.switchScreen(Background.S_Four);
         }
         
         if(e.getButton() == MouseEvent.BUTTON3)
         {
            doPop(e);
         }
      }
      
      private void doPop(MouseEvent e)
      {
         PopUp menu = new PopUp(BFrame);
         menu.show(e.getComponent(), e.getX(), e.getY());
      }
      
      public void mouseReleased(MouseEvent e){}
      public void mouseClicked(MouseEvent e){}
      public void mouseEntered(MouseEvent e){}
      public void mouseExited(MouseEvent e){}
   }
}
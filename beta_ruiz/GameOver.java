import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;


/** the setting for the first level level **/
public class GameOver extends JPanel
{  
   public static int APP_WIDTH = 1300;
   public static int APP_HEIGHT = 800;
   
   private String s;
   private int index;
   
   /**
   Sets up all the buttons and adds it to a frame near the bottom as well as draws the 
   current leader board
   */
   public GameOver(String S, int idx)
   {  
      s = S;
      index = idx;
   }
   
   /**
   Should paint the current leader board
   @param g this is the drawing component
   */
   public void drawText(Graphics2D g2, int centerX, int centerY)
   {
      Rectangle2D.Double back = 
         new Rectangle2D.Double(0,0,APP_WIDTH, APP_HEIGHT);
      g2.setColor(Color.BLACK);
      g2.fill(back); 
      g2.setColor(Color.GREEN);
      g2.drawString("GAME OVER", centerX - 150 , centerY - 100);
      
      if(index == 00)
      {
         g2.drawString("You Lose", centerX - 150 , centerY - 140);
      }
      else if(index == 01)
      {
         g2.drawString("Time: " + s, centerX - 130, centerY + 20);
         if(checkBestTime() == true)
         {
            //g2.drawString("New best time!", centerX - 160, centerY + 150);
         }
      }
      else if(index == 10)
      {
         g2.drawString("Score: " + s, centerX - 130, centerY + 20);
         if(checkBestScore() == true)
         {
            //g2.drawString("New best score!", centerX - 160, centerY + 150);
         }
      }
      else if(index == 3)
      {
      }
      else if(index == 4)
      {
      }
   }
   
   public void gameSound()
   {
      if (checkBestTime() == true)
      {
            //SoundEffects.getInstance().startHighScoreSound();
      }
      else
      {
            //SoundEffects.getInstance().startGameOverSound();
      }
   }
   
   public String getBestTime()
   {
      //retrieve the best time from a file  
      return s;
   }
   
   public String getBestScore()
   {
      //retrieve the best score from a file
      return s;
   }
   
   public boolean  checkBestTime()
   {
      if (s.compareTo(getBestTime()) != 0)
      {
         return false;
      }
      else
      {
         return true;
      }
   }
   
   public boolean  checkBestScore()
   {
      if (s.compareTo(getBestScore()) != 0)
      {
         return false;
      }
      else
      {
         return true;
      }
   }
}
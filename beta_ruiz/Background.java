import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
background panel where all panes come to rest
@Author Francisco Ruiz
@Version 5/12/16
*/
public class Background 
{  
   /** background frame */
   private JFrame BFrame;
   
   /** background panel */
   private JPanel BPanel;
   
   /** homescreen panel */
   private HomeScreen myHome;
   
   /** level chooser panel */
   private LevelChooser AllSpace;
   
   /** level 1 panel */
   private Space_1 FirstSpace;
   
   /** level 2 panel */
   private Space_2 SecondSpace;
   
   /** level 3 panel */
   private Space_3 ThirdSpace;
   
   /** level 4 panel */
   private Space_4 FourthSpace;
   
   /** leader board panel*/
   private LeaderBoard LB;
   
   /** app pixel width */
   private static int APP_WIDTH = 1300;
   
   /** app pixel height */
   private static int APP_HEIGHT = 800;
   
   /** state of the thread */
   public static boolean isPaused;
   
   /** trigger to switch to the home screen panel */
   public static final String main = "MAIN";
   
   /** trigger to switch to the level chooser */
   public static final String S_ALL = "ALL";
   
   /** trigger to switch to the first level panel */
   public static final String S_One = "S1";
   
   /** trigger to switch to the second level panel */
   public static final String S_Two = "S2";
   
   /** trigger to switch to the third level panel */
   public static final String S_Three = "S3";
   
   /** trigger to switch to the fourth level panel */
   public static final String S_Four = "S4";
   
   /** trigger to switch to the leder board */
   public static final String L_Board = "LB";
   
   /**
   This sets up the background frame that all other panels will eventually 
   come too
   */
   public void setUpFrame()
   { 
      BFrame = new JFrame();
      BFrame.setSize(APP_WIDTH,APP_HEIGHT);
      
      myHome = new HomeScreen(this);
      myHome.setFocusable(true);
      
      FirstSpace = new Space_1(this);
      FirstSpace.setFocusable(true);
      
      SecondSpace = new Space_2(this);
      SecondSpace.setFocusable(true);
      
      ThirdSpace = new Space_3(this);
      ThirdSpace.setFocusable(true);
      
      FourthSpace = new Space_4(this);
      FourthSpace.setFocusable(true);
      
      LB = new LeaderBoard(this);
      LB.setFocusable(true);
      
      AllSpace = new LevelChooser(this);
      AllSpace.setFocusable(true);
      
      BPanel = new JPanel(new CardLayout());
      BPanel.add(myHome, main);
      BPanel.add(FirstSpace, S_One);
      BPanel.add(SecondSpace, S_Two);
      BPanel.add(ThirdSpace, S_Three);
      BPanel.add(FourthSpace, S_Four);
      BPanel.add(L_Board, LB);
      BPanel.add(AllSpace, S_ALL);
      
      CardLayout layout = (CardLayout) BPanel.getLayout();
      layout.show(BPanel, main);
      
      BFrame.add(BPanel);
      BFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      BFrame.setVisible(true);
   }

   /** checks whether the thread is paused */
   public static boolean isPaused()
   {
      return isPaused;
   }
   
   /** 
   sets the value of the boolean
   @param flag desired value of the boolean
    */
   public void setPaused(boolean flag)
   {
      isPaused = flag;
   }
   
   /** runs the game by making sure the thread is paused */
   public void run()
   {
      isPaused = true;
      setUpFrame();
   }
   
   /** switches to the desired panel */
   public void switchScreen(String whichScreen)
   {
      CardLayout layout = (CardLayout) BPanel.getLayout();
      layout.show(BPanel, whichScreen);
      
      if(whichScreen == S_One)
      {
         FirstSpace.requestFocusInWindow();
      }
   }
 
   public static void main ( String args[] ) 
   {  
      Background game = new Background();
      game.run();
   } 
}

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;


public class Space_4 extends JPanel
{
   private Background BFrame;
   private GameOver go;
   private Defender base;
   private RedEnemy special;
   private BlueBullet blueBomb;
   private RedBullet redBomb;
   private EnemyBullet easyBomb;
   private EnemyBullet mediumBomb;
   private EnemyBullet hardBomb;
   private Rectangle2D.Double temp;
   
   public static final int APP_WIDTH = 1300;
   public static final int APP_HEIGHT = 800;
   public static final int DELAY = 80;
 
   public static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
   public static final String RIGHT_D = "right (pressed down)";
   public static final String RIGHT_U = "right (not pressed)";
   public static final String LEFT_D = "left (pressed down)";
   public static final String LEFT_U = "left (not pressed)";
   public static final String SPACE_D = "space (pressed down)";
   public static final String SPACE_U = "space (not pressed)";
   
   private ArrayList<Rectangle2D.Double> block_1;
   private ArrayList<Rectangle2D.Double> block_2;
   private ArrayList<Rectangle2D.Double> block_3;
   private ArrayList<Rectangle2D.Double> block_4;
   
   private ArrayList<Integer> x_x;
   private ArrayList<Integer> y_x;
   private ArrayList<Integer> z_x;
   private ArrayList<Integer> g_x;
   private ArrayList<Integer> y;
   
   private Enemy[][] BEA;
   
   private EasyEnemy easyArray[];
   private int easyID;
   private int easyTimer;
   
   private MediumEnemy mediumArray[];
   private int mediumID;
   private int mediumTimer;
   
   private HardEnemy hardArray[];
   private int hardID;
   public int hardTimer;
   
   private boolean moveR;
   private boolean moveL;
   public static boolean pause;
   private boolean shoot;
   private boolean gameDone;
   public static boolean beginGame;
   
   private int lives;
   private int enemyNum;
   private int score;
   
   public Space_4(Background bkg)
   {
      BFrame = bkg;
      base = new Defender(BFrame);
      special = new RedEnemy(BFrame);
      
      blueBomb = new BlueBullet(base);
      redBomb = new RedBullet(special);
      easyBomb = new EnemyBullet(1);
      mediumBomb = new EnemyBullet(2);
      hardBomb = new EnemyBullet(3);
      
      lives = 3;
      enemyNum = 55;
      score = 0;
      
      easyTimer = 1;
      mediumTimer = 1;
      hardTimer = 1; 
      
      moveR = false;
      moveL = false;
      pause = false;
      shoot = false;
      gameDone = false;
      beginGame = false;
      
      block_1 = new ArrayList<Rectangle2D.Double>();
      block_2 = new ArrayList<Rectangle2D.Double>();
      block_3 = new ArrayList<Rectangle2D.Double>();
      block_4 = new ArrayList<Rectangle2D.Double>();
      
      x_x = new ArrayList<Integer>();
      y_x = new ArrayList<Integer>();
      z_x = new ArrayList<Integer>();
      g_x = new ArrayList<Integer>();
      y = new ArrayList<Integer>();
      
      easyArray = new EasyEnemy[22];
      mediumArray = new MediumEnemy[22];
      hardArray = new HardEnemy[11];
      
      BEA = new Enemy[3][23];
      
      this.addMouseListener(new MyButtonListener());
      MyKeyBindings();
      fillArrays();
      fillBottomEnemies();
      fillHardArray();
      fillBigEnemyArray();
      run(); 
   }
   
   public void setPause(boolean val)
   {
      pause = val;
   }  

   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      drawBackground(g2);
      drawArrays(g2);
      drawEasyArray(g2);
      drawMediumArray(g2);
      drawHardArray(g2);
      base.drawMe(g2);
      base.drawLives(g2, lives);
      blueBomb.drawMe(g2);
      special.drawMe(g2);
      redBomb.drawMe(g2);
      easyBomb.drawMe(g2);
      mediumBomb.drawMe(g2);
      hardBomb.drawMe(g2);
      if (gameDone == true)
      {
         go.drawText(g2, APP_WIDTH / 2, APP_HEIGHT / 2);
      }
   }
   
   public void drawBackground(Graphics2D g2)
   {
      Rectangle2D.Double back = 
         new Rectangle2D.Double(0,0,APP_WIDTH, APP_HEIGHT);
      g2.setColor(Color.BLACK);
      g2.fill(back);
      
      g2.setColor(Color.GREEN);
      g2.setFont(new Font("Arial", Font.BOLD, 40));
      g2.drawString("Lives:", 800,50);
      g2.drawString("Score " + score, 400, 50);
      g2.setStroke(new BasicStroke(10));
      g2.draw(new Line2D.Double(0, 700, 1300, 700));
   }
   
   public void drawArrays(Graphics2D g2)
   {
      for(int x = 0; x < block_1.size(); x++)
      {
         g2.fill(block_1.get(x));
      }
      for(int x = 0; x < block_2.size(); x++)
      {
         g2.fill(block_2.get(x));
      }
      for(int x = 0; x < block_3.size(); x++)
      {
         g2.fill(block_3.get(x));
      }
      for(int x = 0; x < block_4.size(); x++)
      {
         g2.fill(block_4.get(x));
      }
   } 
      
   public void fillArrays()
   {
      int x = APP_WIDTH / 11;
      for (int q = x; q < 130; q++)
      {
         x_x.add(q*3 + 7*(q-x) - 150);
         y_x.add(q*5 + 5*(q-x) - 150);
         z_x.add(q*7 + 3*(q-x) - 150);
         g_x.add(q*9 + (q-x) - 150);
      }
      for (int f = 500; f < 525; f++)
      {
         y.add(f);
      }
      for (int k = 1; k < 11; k++)
      {
         for (int l = 1; l < 24; l++)
         {
            Rectangle2D.Double shape = new Rectangle2D.Double(x_x.get(k), y.get(l), 
               10, 10);
            block_1.add(shape);
         }
      }
      for (int k = 1; k < 11; k++)
      {
         for (int l = 1; l < 24; l++)
         {
            Rectangle2D.Double shape = new Rectangle2D.Double(y_x.get(k), y.get(l), 
               10, 10);
            block_2.add(shape);   
         }
      }
      for (int k = 1; k < 11; k++)
      {
         for (int l = 1; l < 24; l++)
         {
            Rectangle2D.Double shape = new Rectangle2D.Double(z_x.get(k), y.get(l), 
               10, 10);
            block_3.add(shape);
         }
      }
      for (int k = 1; k < 11; k++)
      {
         for (int l = 1; l < 24; l++)
         {
            Rectangle2D.Double shape = new Rectangle2D.Double(g_x.get(k), y.get(l), 
               10, 10);
            block_4.add(shape);
         }
      }
   }
   
   public void BarrierCollisions(BlueBullet bb)
   {
      temp = 
         new Rectangle2D.Double(bb.get_X() + 21, bb.get_Y() + 12, 10, 25);
         
      if (block_1.size() > 0 || block_2.size() > 0 
         || block_3.size() > 0 || block_4.size() > 0)
      {
         for(int x = 0; x < block_1.size(); x++)
         {
            if (temp.intersects(block_1.get(x)))
            {
               block_1.remove(x);
               blueBomb.reset();
            }
         }
         for(int x = 0; x < block_2.size(); x++)
         {
            if (temp.intersects(block_2.get(x)))
            {
               block_2.remove(x);
               blueBomb.reset();
            }
         }
         for(int x = 0; x < block_3.size(); x++)
         {
            if (temp.intersects(block_3.get(x)))
            {
               block_3.remove(x);
               blueBomb.reset();
            }
         }
         for(int x = 0; x < block_4.size(); x++)
         {
            if (temp.intersects(block_4.get(x)))
            {
               block_4.remove(x);
               blueBomb.reset();
            }
         }
      }
      repaint();
   }
   
   public void BarrierCollisions(RedBullet rb)
   {
      temp = 
         new Rectangle2D.Double(rb.get_X() + 20, rb.get_Y(), 10, 10);
         
      if (block_1.size() > 0 || block_2.size() > 0 
         || block_3.size() > 0 || block_4.size() > 0)
      {
         for(int x = 0; x < block_1.size(); x++)
         {
            if (temp.intersects(block_1.get(x)))
            {
               block_1.remove(x);
               redBomb.reset();
            }
         }
         for(int x = 0; x < block_2.size(); x++)
         {
            if (temp.intersects(block_2.get(x)))
            {
               block_2.remove(x);
               redBomb.reset();
            }
         }
         for(int x = 0; x < block_3.size(); x++)
         {
            if (temp.intersects(block_3.get(x)))
            {
               block_3.remove(x);
               redBomb.reset();
            }
         }
      }
      if (block_4.size() > 0)
      {
         for(int x = 0; x < block_4.size(); x++)
         {
            if (temp.intersects(block_4.get(x)))
            {
               block_4.remove(x);
               redBomb.reset();
            }
         }
      }
      repaint();
   }
   
   public void BarrierCollisions(EnemyBullet eb)
   {
      temp = 
         new Rectangle2D.Double(eb.get_X() + 20, eb.get_Y(), 10, 10);
         
      if (block_1.size() > 0)
      {
         for(int x = 0; x < block_1.size(); x++)
         {
            if (temp.intersects(block_1.get(x)))
            {
               block_1.remove(x);
               eb.reset();
            }
         }
      }
      if (block_2.size() > 0)
      {
         for(int x = 0; x < block_2.size(); x++)
         {
            if (temp.intersects(block_2.get(x)))
            {
               block_2.remove(x);
               eb.reset();
            }
         }
      }
      if (block_3.size() > 0)
      {
         for(int x = 0; x < block_3.size(); x++)
         {
            if (temp.intersects(block_3.get(x)))
            {
               block_3.remove(x);
               eb.reset();
            }
         }
      }
      if (block_4.size() > 0)
      {
         for(int x = 0; x < block_4.size(); x++)
         {
            if (temp.intersects(block_4.get(x)))
            {
               block_4.remove(x);
               eb.reset();
            }
         }
      }
      repaint();
   }
   
   public void fillBottomEnemies()
   {
      for (int x = 0; x < easyArray.length; x++)
      {
         if(x < 11)
         {
            easyArray[x] = new EasyEnemy(BFrame, 80*x, 0, x, 4);
            mediumArray[x] = new MediumEnemy(BFrame, 80*x, 0, x, 4);
         }
         else
         {
            easyArray[x] = new EasyEnemy(BFrame, 80*x + 45, 45,x, 4);
            mediumArray[x] = new MediumEnemy(BFrame, 80*x + 45, -45, x, 4);
         }
      }
   }
   
   public void fillHardArray()
   {
      for (int x = 0; x < hardArray.length; x++)
      {
         hardArray[x] = new HardEnemy(BFrame, 80*x, -45, x, 4);
      }      
   }
   
   public void drawEasyArray(Graphics2D g2)
   {
      for (int x = 0; x < easyArray.length; x++)
      {
         if (easyArray[x] != null)
         {
            easyArray[x].drawMe(g2);
         }
      }
   } 
   
   public void drawMediumArray(Graphics2D g2)
   {
      for (int x = 0; x < mediumArray.length; x++)
      {
         if (mediumArray[x] != null)
         {
            mediumArray[x].drawMe(g2);
         }
      }
   }
   
   public void drawHardArray(Graphics2D g2)
   {
      for (int x = 0; x < hardArray.length; x++)
      {
         if (hardArray[x] != null)
         {
            hardArray[x].drawMe(g2);
         }
      }
   }
   
   public void checkEasyArray()
   {
      for(int x = 0; x < easyArray.length; x++)
      {
         if (easyArray[x] != null)
         {
            easyArray[x].checkBounds();
            if(easyArray[x].checkIfHit(blueBomb) > 0)
            {
               easyID = easyArray[x].getID();
               easyArray[easyID] = null;
               score = score + 10;
               enemyNum--;
            }
         }
         if (easyArray[x] != null)
         {
            if(easyArray[x].registerKill(easyBomb, base) == -1)
            {
               lives--;
            }
         }
      }
   }
   
   public void checkMediumArray()
   {
      for(int x = 0; x < mediumArray.length; x++)
      {
         if (mediumArray[x] != null)
         {
            mediumArray[x].checkBounds();
            if(mediumArray[x].checkIfHit(blueBomb) > 0)
            {
               mediumID = mediumArray[x].getID();
               mediumArray[mediumID] = null;
               score = score + 20;
               enemyNum--;
            }
         }
         if (mediumArray[x] != null)
         {
            if(mediumArray[x].registerKill(mediumBomb, base) == -1)
            {
               lives--;
            }
         }
      }
   }
   
   public void checkHardArray()
   {
      for(int x = 0; x < hardArray.length; x++)
      {
         if (hardArray[x] != null)
         {
            hardArray[x].checkBounds();
            if(hardArray[x].checkIfHit(blueBomb) > 0)
            {
               hardID = hardArray[x].getID();
               hardArray[hardID] = null;
               score = score + 30;
               enemyNum--;
            }
         }
         if (hardArray[x] != null)
         {
            if(hardArray[x].registerKill(hardBomb, base) == -1)
            {
               lives--;
            }
         }
      }
   }
      
   public void roundComplete()
   {
      if (enemyNum ==  0)
      {
         enemyNum = 55;
         moveR = false;
         moveL = false;
         redBomb.reset();
         easyBomb.reset();
         mediumBomb.reset();
         hardBomb.reset();
         fillBottomEnemies();
         fillHardArray();
         repaint();
      }
   }
   
   public void fillBigEnemyArray()
   {
      for(int x = 0; x < easyArray.length; x++)
      {
         if (easyArray[x] != null)
         {
            BEA[0][x] = (easyArray[x]);
         }
         if(mediumArray[x] != null)
         {
            BEA[1][x] = (mediumArray[x]);
         }
      }
      for(int x = 0; x < hardArray.length; x++)
      {
         if (hardArray[x] != null)
         {
            BEA[2][x] = (hardArray[x]);
         }
      }
   }
   
   public void checkEnemyCollisions()
   {
      for(int x = 0; x < 3; x++)
      {
         for (int y = 0; y < 21; y++)
         {
            if ((BEA[x][y] != null) && (BEA[x][y+1] != null))
            {
               BEA[x][y].checkForCollision(BEA[x][y+1]);
            }
         }
      }
   }
   
   public void checkLives()
   {
      if(lives <= 0)
      {
         setPause(true);
         go = new GameOver("" + score, 10);
         gameDone = true;
      }
   }   
   public void reset()
   {
      lives = 3;
      enemyNum = 55;
      score = 0;
      moveR = false;
      moveL = false;
      pause = true;
      shoot = false;
      gameDone = false;
      base.reset();
      special.reset();
      blueBomb.reset();
      redBomb.reset();
      easyBomb.reset();
      mediumBomb.reset();
      hardBomb.reset();
      fillArrays();
      fillBottomEnemies();
      fillHardArray();
      fillBigEnemyArray();
      repaint();
   }
   
   public void run()
   {
      Timer BT = new Timer();
      BT.scheduleAtFixedRate(new BulletUpdater(), 0, 10);
      Timer RE = new Timer();
      RE.scheduleAtFixedRate(new RedEnemyUpdater(), 0, 50);
      Timer EE = new Timer();
      EE.scheduleAtFixedRate(new EasyEnemyUpdater(), 0, 20);
      Timer ME = new Timer();
      ME.scheduleAtFixedRate(new MediumEnemyUpdater(), 0, 20);
      Timer HE = new Timer();
      HE.scheduleAtFixedRate(new HardEnemyUpdater(), 0, 20);
      
      Timer clock = new Timer();
      clock.scheduleAtFixedRate(new SpecialThread(), 0, 800);
      Timer clock_two = new Timer();
      clock_two.scheduleAtFixedRate(new EasyThread(), 0, 700);
      Timer clock_three = new Timer();
      clock_three.scheduleAtFixedRate(new MediumThread(), 0, 700);
      Timer clock_four = new Timer();
      clock_four.scheduleAtFixedRate(new HardThread(), 0, 700);
      
      Checker peep = new Checker();
      peep.start();
   }
   
   private class EasyThread extends TimerTask
   {
      public void run()
      {
         if (pause == false && beginGame == true)
         {
            if(easyTimer > 0)
            {
               easyTimer--;
            }
            
            if(easyTimer == 0)
            {
               int num = (int) (Math.random() * 22);
               if (easyArray[num] != null)
               {
                  easyArray[num].gameAction(easyBomb, easyArray[num].get_X(), 
                     easyArray[num].get_Y());
                  easyTimer = 0;
                  repaint();
               }
            }
         }
      }
   }
   
   private class MediumThread extends TimerTask
   {
      public void run()
      {
         if (pause == false && beginGame == true)
         {
            if(mediumTimer > 0)
            {
               mediumTimer--;
            }
            
            if(mediumTimer == 0)
            {
               int num = (int) (Math.random() * 22);
               if (mediumArray[num] != null)
               {
                  mediumArray[num].gameAction(mediumBomb, mediumArray[num].get_X(), 
                     mediumArray[num].get_Y());
                  mediumTimer = 0;
                  repaint();
               }
            }
         }
      }
   }
   
   private class HardThread extends TimerTask
   {
      public void run()
      {
         if (pause == false && beginGame == true)
         {
            if(hardTimer > 0)
            {
               hardTimer--;
            }
            
            if(hardTimer == 0)
            {
               int num = (int) (Math.random() * 11);
               if (hardArray[num] != null)
               {
                  hardArray[num].gameAction(hardBomb, hardArray[num].get_X(), 
                     hardArray[num].get_Y());
                  hardTimer = 0;
                  repaint();
               }
            }
         }
      }
   }

   
   private class SpecialThread extends TimerTask
   {
      public void run()
      {
         if (pause == false && beginGame == true)
         {
            if(special.getCD() > 0)
            {
               special.changeCD(-1);
            }
            repaint();
         }
      }
   }
      
   private class BulletUpdater extends TimerTask
   {
      public void run()
      {
         if (pause == false && beginGame == true)
         {  
            redBomb.move();
            easyBomb.move();
            mediumBomb.move();
            hardBomb.move();
               
            if (blueBomb.getCF() == false)
            {
               blueBomb.move();
            }
            repaint();
         }
      }
   }
   
   private class RedEnemyUpdater extends TimerTask
   {
      public void run()
      {
         if (pause == false && beginGame == true && special.getCD() == 0)
         {
            special.changeX();
            repaint();
         }
      }
   }
   
   private class EasyEnemyUpdater extends TimerTask
   {
      public void run()
      {
         if(pause == false && beginGame == true)
         {
            for(int x = 0; x < easyArray.length; x++)
            {
               if (easyArray[x] != null)
               {
                  easyArray[x].move();
               }
            }
            repaint();
         }
      }
   }
   
   private class MediumEnemyUpdater extends TimerTask
   {
      public void run()
      {
         if(pause == false && beginGame == true)
         {
            for(int x = 0; x < mediumArray.length; x++)
            {
               if (mediumArray[x] != null)
               {
                  mediumArray[x].move();
               }
            }
            repaint();
         }
      }
   }
   
   private class HardEnemyUpdater extends TimerTask
   {
      public void run()
      {
         if(pause == false && beginGame == true)
         {
            for(int x = 0; x < hardArray.length; x++)
            {
               if (hardArray[x] != null)
               {
                  hardArray[x].move();
               }
            }
            repaint();
         }
      }
   }

   private class Checker extends Thread
   {
      
      public void run()
      {
         while(true)
         {
            BarrierCollisions(blueBomb);
            BarrierCollisions(redBomb);
            BarrierCollisions(easyBomb);
            BarrierCollisions(mediumBomb);
            BarrierCollisions(hardBomb);
            
            blueBomb.checkBounds();
            redBomb.checkBounds();
            easyBomb.checkBounds();
            mediumBomb.checkBounds();
            hardBomb.checkBounds();
            
            base.checkBounce();
            special.checkBounds();
         
            checkEasyArray();
            checkMediumArray();
            checkHardArray();
            
            lives = lives + special.checkIfHit(blueBomb);
            lives = lives + special.registerKill(redBomb, base);
            
            checkLives();
            checkEnemyCollisions();
            roundComplete();
            
            try 
            {
               sleep(1);
            }
            catch (InterruptedException ie)
            {
               
            } 
         }
      }
   }
  /////////////////////////////////////////////////////////////////////////
   
      /** sets up pop up menu when user right-clicks */
   private class PopUp extends JPopupMenu implements ActionListener
   {
      /** background object */
      private Background BFrame;
      /** Quit option */
      JMenuItem B1;
      /** Mute option */
      JMenuItem B2;
      
      JMenuItem B3;
      
      JMenuItem B4;
      
      /** 
      pop-up menu
      @param bkg This is the background object
      */
      public PopUp(Background bkg)
      {
         BFrame = bkg;
         
         B1 = new JMenuItem("Quit Game");
         this.add(B1);
         B1.addActionListener(this);
         this.addSeparator();
         B4 = new JMenuItem("Pause Game");
         this.add(B4);
         B4.addActionListener(this);
         this.addSeparator();
         B2 = new JMenuItem("Mute Game Sound"); 
         B2.addActionListener(this); 
         this.add(B2);
         this.addSeparator();
         B3 = new JMenuItem("Return To Game");
         B3.addActionListener(this); 
         this.add(B3);
      }
      
      /** 
      when quit is clicked the pane switches to the home screen
      @param e This is the action parameter 
      */
      public void actionPerformed(ActionEvent e) 
      {
         if (e.getSource() == B1)
         {
            reset();
            BFrame.switchScreen(Background.main);
         }
         else if(e.getSource() == B3)
         {
            setPause(false);
         }
         else if(e.getSource() == B4)
         {
            setPause(true);
         }
         else if(e.getSource() == B2)
         {
            //mute game sound
         }
      }
      
      /** 
      not used
      @param e This is the mouse event parameter
      */
      public void mouseDragged(MouseEvent e){} 
   }
   
   /** handles the pop-up menu behavior */
   private class MyButtonListener implements MouseListener
   {
     /** 
     does pop-up when the button area is right-clicked
     @param e This is the mouse event parameter
     */
      public void mousePressed(MouseEvent e)
      {
         int mouseX = e.getX();
         int mouseY = e.getY();
         if(e.getButton() == MouseEvent.BUTTON3)
         {
            BFrame.setPaused(true);
            doPop(e);
         }
      }
      
      /**
      shows the pop-up menu
      @param e This is the mouse event parameter
      */
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
   
   public void MyKeyBindings()
   {
      this.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 
         0, false), RIGHT_D);
      this.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 
         0, true), RIGHT_U); 
      this.getActionMap().put(RIGHT_D, new RightActionD());
      this.getActionMap().put(RIGHT_U, new RightActionU());
      this.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 
         0, false), LEFT_D);
      this.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 
         0, true), LEFT_U);  
      this.getActionMap().put(LEFT_D, new LeftActionD());
      this.getActionMap().put(LEFT_U, new LeftActionU());
      this.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 
         0, false), SPACE_D);
      this.getInputMap(IFW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 
         0, true), SPACE_U); 
      this.getActionMap().put(SPACE_D, new SpaceActionD());
      this.getActionMap().put(SPACE_U, new SpaceActionU());
   }
   
   /** left arrow key action */
   private class RightActionD extends AbstractAction 
   {   
      /**
      moves the ship
      @param e This is the action event parameter
      */
      public void actionPerformed(ActionEvent e) 
      {
         if (!(score < 0) && pause != true)
         {
            moveR = true;
            base.gameAction(blueBomb, moveR, moveL, shoot);
            repaint();
         }
      }
   }
   
    /** left arrow key action */
   private class LeftActionD extends AbstractAction 
   {   
      /**
      moves the ship
      @param e This is the action event parameter
      */
      public void actionPerformed(ActionEvent e) 
      {
         if (!(score < 0) && pause != true)
         {
            moveL = true;
            base.gameAction(blueBomb, moveR, moveL, shoot);
            repaint();
         }
      }
   }
   
   /** left arrow key action */
   private class SpaceActionD extends AbstractAction 
   {   
      /**
      moves the ship
      @param e This is the action event parameter
      */
      public void actionPerformed(ActionEvent e) 
      {
         if (!(score < 0) && pause != true)
         {
            shoot = true;
            base.gameAction(blueBomb, moveR, moveL, shoot);
            repaint();
         }
      }
   }

   /** right arrow key action */
   private class RightActionU extends AbstractAction 
   {  
      /**
      moves the ship
      @param e This is the action event parameter
      */
      public void actionPerformed(ActionEvent e) 
      {
         if (!(score < 0) && pause != true)
         {
            moveR = false;
            base.gameAction(blueBomb, moveR, moveL, shoot);
            repaint(); 
         }
      }
   }
   
   /** left arrow key action */
   private class LeftActionU extends AbstractAction 
   {   
      /**
      moves the ship
      @param e This is the action event parameter
      */
      public void actionPerformed(ActionEvent e) 
      {
         if (!(score < 0) && pause != true)
         {
            moveL = false;
            base.gameAction(blueBomb, moveR, moveL, shoot);
            repaint();
         }
      }
   }
   
   /** left arrow key action */
   private class SpaceActionU extends AbstractAction 
   {   
      /**
      moves the ship
      @param e This is the action event parameter
      */
      public void actionPerformed(ActionEvent e) 
      {
         if (!(score < 0) && pause != true)
         {
            shoot = false;
            base.gameAction(blueBomb, moveR, moveL, shoot);
            repaint();
         }
      }
   }
}
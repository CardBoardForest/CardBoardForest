// @author Colin Wakefield
 package gameWithAlex;
 
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
@SuppressWarnings("serial")
public class Main extends JPanel implements ActionListener , KeyListener
{
    //double buffer stuff
    private Image dbImage;
    private Graphics dbg;
    //timer that holds the refresh rate
    private final Timer timer = new Timer( 33, this);
    //Different scales if i want to change them 
    @SuppressWarnings("unused")
	private final int Scale = 16;
    @SuppressWarnings("unused")
	private final int Scale32 = 32;
    @SuppressWarnings("unused")
	private final int Scale64 = 64;
    
    public  static int WIDTH; 
    public  static int HEIGHT;
    JFrame frame;
    // gets the and other classes
    MapChanger maps;
    TileMap  map ;
    MainCharacter boy;
    ItemHandler itemhandler;
    //testing variable

    //main
    public static void main(String args[])
    {
        @SuppressWarnings("unused")
		Main frame = new Main("BulbBoy");
        
    }

   // this is the main method that creates that window
    public Main(String titile) 
    {
        // sets up variables for the frame and ya
        frame = new JFrame();
        this.init();
    }
    // paints the stuff
    @Override
    public void paintComponent(Graphics g)
    { 
        // double buffer that i think works so yea just keep it 
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, getWidth(), getHeight());
        dbg.setColor(getForeground());
        g.drawImage(dbImage, 0, 0, this);
        // draws map
        map.draw(g);
        //draws boy 
        boy.draw(g);
        //draws item handler
        itemhandler.draw(g);
     }

    // refreshes the stuff and is the timer for the game clock
    @Override
   public void actionPerformed(ActionEvent e) 
   {
        //updates
        this.update();
        //refreshes screen
        this.revalidate();
        //draws scream
        this.repaint();

   }
   //updates all the stuff
   public void update()
   {
	   //sets the map
	   map = maps.getmap();
       // update map
       map.update();
       //sets map so it does not crash
       boy.setMap(map);
       //update the boy
       boy.update();
       //deals with changing of the maps
	   MapChanger.setchanged(false);
       //sets the map for the itemHandler so it does not crash
       itemhandler.setMap(map);
       //updates itemHandler
       itemhandler.update();
       
   }
   public void init()
   {
	   //loades sheet
	   ResourceLoader.loadSheet();
	   //sets the screen
       frame.setUndecorated(true);
       frame.setSize((600),(600));
       frame.setVisible(true);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setResizable(false);
       frame.setLocationRelativeTo(null);
       frame.addKeyListener(this);
       timer.start();
       frame.add(this);
       // sets the back ground to black
       this.setBackground(Color.black);
       //instantiates the stuffs
       WIDTH = frame.getWidth();
       HEIGHT = frame.getHeight();
       maps = new MapChanger();
       boy = new MainCharacter();
       itemhandler = new ItemHandler(WIDTH,HEIGHT,boy);
       MapChanger.setmap(2);
       
   }
   @Override
   //Moment for the player and other key events
    public void keyPressed(KeyEvent e) 
   {
       int keyCode = e.getKeyCode();
       switch(keyCode)
       {
       case KeyEvent.VK_W :boy.moveup(true);break ;
       case KeyEvent.VK_A :boy.moveleft(true);break ;
       case KeyEvent.VK_S :boy.movedown(true);break ;
       case KeyEvent.VK_D :boy.moveright(true);break ;
       case KeyEvent.VK_UP :boy.moveup(true);break ;
       case KeyEvent.VK_LEFT :boy.moveleft(true);break ;
       case KeyEvent.VK_DOWN :boy.movedown(true);break ;
       case KeyEvent.VK_RIGHT :boy.moveright(true);break ;
       }
   }

   @Override
 //Moment for the player
   public void keyReleased(KeyEvent e) 
   {
       int keyCode = e.getKeyCode();
       switch(keyCode)
       {
       case KeyEvent.VK_W :boy.moveup(false); break ;
       case KeyEvent.VK_A :boy.moveleft(false);break ;
       case KeyEvent.VK_S :boy.movedown(false);break ;
       case KeyEvent.VK_D :boy.moveright(false);break ;
       case KeyEvent.VK_UP :boy.moveup(false);break ;
       case KeyEvent.VK_LEFT :boy.moveleft(false);break ;
       case KeyEvent.VK_DOWN :boy.movedown(false);break ;
       case KeyEvent.VK_RIGHT :boy.moveright(false);break ;
       //chagnes map
       case KeyEvent.VK_P :MapChanger.setmap(2);break ;
       // kills the program 
       case KeyEvent.VK_ESCAPE:timer.stop(); frame.dispose();;break ;
       }
   }

   @Override
   public void keyTyped(KeyEvent e) 
   {
    
   }
}
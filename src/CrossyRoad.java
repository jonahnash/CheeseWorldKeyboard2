//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.*;

/***
 * Step 0 for keyboard control - Import
 */
import java.awt.event.*;

/***
 * Step 1 for keyboard control - implements KeyListener
 */
public class CrossyRoad implements Runnable, KeyListener {

    //Variable Definition Section

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    public static int HEIGHT = 600;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    //Declare the variables needed for images
    public Image defenderPic;
    public Image mousePic;
    public Image offenderPic;
    public Image backgroundPic;

    //Declare the character objects
    public Mouse mouse1;
    public Defender theDefender;
    public Defender[] defenders;
    public Player user;
    public boolean isMoving;

    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        CrossyRoad myApp = new CrossyRoad();   //creates a new instance of the game
        new Thread(myApp).start();               //creates a threads & starts up the code in the run( ) method
    }

    // Constructor Method - setup portion of the program
    // Initialize your variables and construct your program objects here.
    public CrossyRoad() {
        defenders = new Defender[3];
        setUpGraphics();

        /***
         * Step 2 for keyboard control - addKeyListener(this) to the canvas
         */
        canvas.addKeyListener(this);

        //load images
        backgroundPic = Toolkit.getDefaultToolkit().getImage("football field.jpg");
        defenderPic = Toolkit.getDefaultToolkit().getImage("defense.png");
        mousePic = Toolkit.getDefaultToolkit().getImage("jerry.gif");
        offenderPic = Toolkit.getDefaultToolkit().getImage("offense.png");

        //create (construct) the objects needed for the game
        mouse1 = new Mouse(200, 300, 2, 0, mousePic);
        theDefender = new Defender(400, 300, -5, 0, defenderPic);
        defenders[0] = new Defender(400, 300, -5, 0, defenderPic);
        defenders[1] = new Defender(400, 300, -5, 0, defenderPic);
        defenders[2] = new Defender(400, 300, -5, 0, defenderPic);

        user = new Player(0, 250, 0, 0, offenderPic);

    } // CheeseWorld()


//*******************************************************************************
//User Method Section

    // main thread
    // this is the code that plays the game after you set things up
    public void moveThings() {
        mouse1.move();
        theDefender.move();
        for (Defender d:defenders) {
            d.move();
        }
        user.move();
        if(user.xpos>900){
            theDefender.dx = (-theDefender.dx) - 1;
        }
        if(user.xpos<25){
            theDefender.dx = -(theDefender.dx) + 1;
        }
    }

    public void checkIntersections() {

    }

    public void run() {
        while (true) {
            moveThings();           //move all the game objects
            checkIntersections();   // check character crashes
            render();               // paint the graphics
            pause(20);         // sleep for 20 ms
        }
    }

    //paints things on the screen using bufferStrategy
    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw characters to the screen
        g.drawImage(backgroundPic,0,0,WIDTH,HEIGHT,null);
        g.drawImage(mouse1.pic, mouse1.xpos, mouse1.ypos, mouse1.width, mouse1.height, null);
        g.drawImage(theDefender.pic, theDefender.xpos, theDefender.ypos, theDefender.width, theDefender.height, null);
        for (Defender d:defenders) {
            g.drawImage(d.pic, d.xpos, d.ypos, d.width, d.height, null);
        }
        g.drawImage(user.pic, user.xpos, user.ypos, user.width, user.height, null);

        g.dispose();
        bufferStrategy.show();
    }

    /***
     * Step 3 for keyboard control - add required methods
     * You need to have all 3 even if you aren't going to use them all
     */
    public void keyPressed(KeyEvent event) {
        //This method will do something whenever any key is pressed down.
        //Put if( ) statements here
        char key = event.getKeyChar();     //gets the character of the key pressed
        int keyCode = event.getKeyCode();  //gets the keyCode (an integer) of the key pressed
        System.out.println("Key Pressed: " + key + "  Code: " + keyCode);

        if (keyCode == 68 && isMoving == false) { // d
            user.xpos =user.xpos+50 ;
            isMoving = true;
        }
        if (keyCode == 65 && isMoving == false) { // a
            user.xpos = user.xpos-50;
            isMoving = true;
        }
//        if (keyCode == 32) { // space bar
//            user.dy = -15;
//        }
        if (keyCode == 83 && isMoving == false) { // s
            user.ypos = user.ypos+50;
            isMoving = true;
        }
        if (keyCode == 87 && isMoving == false) { // w
            user.ypos = user.ypos-50;
            isMoving = true;
        }
    }//keyPressed()

    public void keyReleased(KeyEvent event) {
        char key = event.getKeyChar();
        int keyCode = event.getKeyCode();
        //This method will do something when a key is released
        if (keyCode == 68) { // d
            user.right = false;
            isMoving = false;
        }
        if (keyCode == 65) { // a
            user.left = false;
            isMoving = false;
        }
        if (keyCode == 83) { // s
            user.down = false;
            isMoving = false;
        }
        if (keyCode == 87) { // w
            user.up = false;
            isMoving = false;
        }

    }//keyReleased()

    public void keyTyped(KeyEvent event) {
        // handles a press of a character key (any key that can be printed but not keys like SHIFT)
        // we won't be using this method, but it still needs to be in your program
    }//keyTyped()


    //Graphics setup method
    public void setUpGraphics() {
        frame = new JFrame("CheeseWorld");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

}//class

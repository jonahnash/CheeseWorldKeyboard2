//Cheese class 
//Bounces

import java.awt.*;

public class Defender {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.

    public int xpos;                //the x position
    public int ypos;                //the y position
    public int width;
    public int height;
    public boolean movingLeft;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public Rectangle rec;
    public Image pic;
    public int hits;

    // METHOD DEFINITION SECTION

    //This is a constructor that takes 3 parameters.  This allows us to specify the object's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Defender(int pXpos, int pYpos) {

        xpos = pXpos;
        ypos = pYpos;
        width = 75;
        height = 75;
        dx = -5;
        dy = 0;
        isAlive = true;
        movingLeft=true;
        hits = 0;
        rec = new Rectangle(xpos, ypos, width, height);


    } // constructor


    public Defender(int pXpos, int pYpos, int dxParameter, int dyParameter, Image picParameter) {

        xpos = pXpos;
        ypos = pYpos;
        width = 75;
        height = 75;
        dx = dxParameter;
        dy = dyParameter;
        pic = picParameter;
        isAlive = true;
        hits = 0;
        rec = new Rectangle(xpos, ypos, width, height);


    } // constructor


    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void moveLeft() {
        xpos = xpos - dx;
        ypos = ypos + dy;

        if (xpos < -100) {
            xpos = (int)(Math.random()*100)+1000;
            ypos = (int)(Math.random()*(CrossyRoad.HEIGHT-height));
        }
        if (xpos > 1100) {
            xpos = (int)(Math.random()*100)*-1;
            ypos = (int)(Math.random()*(CrossyRoad.HEIGHT-height));
        }

        rec = new Rectangle(xpos, ypos, width, height);

    }
    public void moveRight() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (xpos < -100) {
            xpos = (int)(Math.random()*100)+1000;
            ypos = (int)(Math.random()*(CrossyRoad.HEIGHT-height));
        }
        if (xpos > 1100) {
            xpos = (int)(Math.random()*100)*-1;
            ypos = (int)(Math.random()*(CrossyRoad.HEIGHT-height));
        }

        rec = new Rectangle(xpos, ypos, width, height);

    }
    public void move(){
        if(movingLeft){
        xpos = xpos - dx;
        ypos = ypos + dy;}else{xpos = xpos + dx;}
        if (xpos < -100) {
            xpos = (int)(Math.random()*100)+1000;
            ypos = (int)(Math.random()*(CrossyRoad.HEIGHT-height));
        }
        if (xpos > 1100) {
            xpos = (int)(Math.random()*100)*-1;
            ypos = (int)(Math.random()*(CrossyRoad.HEIGHT-height));
        }

        rec = new Rectangle(xpos, ypos, width, height);

    }

} //end of the Cheese object class  definition


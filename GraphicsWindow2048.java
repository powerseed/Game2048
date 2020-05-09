/* This gives a graphics window for the "2048 puzzle" game,
 * using "real" Java graphics.
 * Running the main method will create a GraphicsWindow2048,
 * create a Game2048, and link the two objects together.
 * Then it will listen for characters typed on the keyboard.
 * (The window must have the "focus". Click in the window.)
 * It will call the tryMove(int) and undo() instance methods
 * in the Game2048 class when i,j,k,l,w,a,s,d, or z are pressed.
 * The following constants must be provided:
 *    Board2048.UP     int constants used to represent
 *    Board2048.DOWN   the 4 directions
 *    Board2048.LEFT   
 *    Board2048.RIGHT
 * Public instance methods provided are:
 *    displayBoard(int[][]) - displays the given matrix in the window
 *    displayMessage(String) - displays the given message as a
 *                             transparent overlay.
 *                             An empty string or null will remove it.
 * Everything else is up to the Game2048 and Board2048 classes.
 * There are a few constants at the top which may be adjusted.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*; //This is needed for the keyboard events

public class GraphicsWindow2048 extends JFrame { //JFrame means "window", in effect
  
  private static final long serialVersionUID = 1L; //eliminates warnings
  private static final int WINDOW_SIZE = 500;      //The initial size.
  //These 2 constants control the drawing. Change to suit.
  private final int SPACING = 15; //Pixels of space between tiles
  //If the numbers are too big or too small, adjust the factor below.
  private final double FONT_SCALE_FACTOR = 0.3;
  
  private JPanel wholeWindow; //A JPanel is the content within a window
  private int width, height;  //For convenience. The drawing area, in pixels.
  private int[][] dataMatrix; //A reference to the data to be drawn in the window.
  private Game2048 theGame; //The Game2048 object that will implement the game
  private String theMessage;
  
  //Constructor
  public GraphicsWindow2048() {
    setTitle("2048 Game");
    dataMatrix = new int[4][4]; //A normal 4x4 board, just to start with.
    setSize(WINDOW_SIZE,WINDOW_SIZE);
    wholeWindow = new GraphicsPanel(); //GraphicsPanel is defined below
    add(wholeWindow);
    wholeWindow.addKeyListener(new HandleKey());
    wholeWindow.setFocusable(true); //allows keyboard events to be seen
    theMessage = null; //No message, usually.
    setVisible(true);
  }//GraphicsWindow2048 constructor
  
  private void setGame(Game2048 game){
     theGame = game;
  }
  
  public static void main(String[] args){
    //Main program. All it has to do is create
    //a window, and a game, and then take the rest of the day off.
    //The execution of the program takes place in a separate
    //thread created by the window.
    GraphicsWindow2048 newWindow = new GraphicsWindow2048();
    Game2048 newGame = new Game2048(newWindow,4);
    newWindow.setGame(newGame); //the link needed 
  }//main
  
  public void displayBoard(int[][] matrix){
     dataMatrix = matrix;
     repaint();
  }
  
  public void displayMessage(String message){
     //Change empty strings into null, to be consistent.
     if(message!=null && message.length()==0)
        message=null;
     theMessage = message;
     repaint();
  }
  
  private class GraphicsPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    public void paintComponent(Graphics g){
      /* This is where all the drawing commands go.
       * Whenever the window needs to be drawn, or redrawn,
       * this method will automatically be called.
       */
      
      //Find out the size of the available area.
      Rectangle windowRect = g.getClipBounds();
      width = windowRect.width;
      height = windowRect.height;
      
      //How big will the individual "tiles" be?
      int rows = dataMatrix.length;
      int cols = dataMatrix[0].length;
      int tileWidth = (width-(SPACING*(cols+1)))/cols;
      int tileHeight = (height-(SPACING*(rows+1)))/rows;
      
      //Draw a the correct number of rows and columns of rectangles
      //and draw the numbers inside them.
      int fontSize = (int)(tileHeight*FONT_SCALE_FACTOR);    
      g.setFont(new Font("Helvetica",Font.BOLD,fontSize));
      FontMetrics myMetrics = g.getFontMetrics();
      g.setColor(Color.BLACK);
      for(int r=0; r<rows; r++){
         int topEdge=SPACING+r*(tileHeight+SPACING);
         for(int c=0; c<cols; c++){
            int leftEdge=SPACING+c*(tileWidth+SPACING);
            g.drawRect(leftEdge,topEdge,tileWidth,tileHeight);
            String theNumber = Integer.toString(dataMatrix[r][c]);
            if(dataMatrix[r][c]>0) //Don't draw zeroes
               g.drawString(theNumber,
                    leftEdge+tileWidth/2-myMetrics.stringWidth(theNumber)/2,
                    topEdge+tileHeight/2+myMetrics.getAscent()/2);
         }//for c
      }//for r

      if(theMessage!=null){
         //Draw a message, too.
         g.setFont(new Font("Helvetica",Font.BOLD,fontSize*2));
         myMetrics = g.getFontMetrics();
         g.setColor(new Color(128,128,128,128)); //transparent grey
         g.drawString(theMessage,
             width/2-myMetrics.stringWidth(theMessage)/2,
             height/2+myMetrics.getAscent()/2);
      }
      
     }//paintComponent method
  }//private inner class graphicsPanel
  
  private class HandleKey implements KeyListener {
    //The three standard methods are required.
    public void keyPressed(KeyEvent e){ /* Ignore */ }
    public void keyReleased(KeyEvent e){ /* Do nothing */ }
    //The only one we really want to pay attention to
    public void keyTyped(KeyEvent e){
       char c = Character.toLowerCase(e.getKeyChar());
       if(c=='i' || c=='w') theGame.tryMove(Board2048.UP);
       else if(c=='j' || c=='a') theGame.tryMove(Board2048.LEFT);
       else if(c=='k' || c=='s') theGame.tryMove(Board2048.DOWN);
       else if(c=='l' || c=='d') theGame.tryMove(Board2048.RIGHT);
       else if(c=='z') theGame.undo();
    }//keyTyped
  }//private inner class HandleKey
  
}//class GraphicsWindow2048




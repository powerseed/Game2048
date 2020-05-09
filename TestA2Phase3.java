import java.util.Scanner;
public class TestA2Phase3 {
   //Play the 2048 puzzle using a simple console interface
   
   static Scanner keyboard;
   final static int GAME_SIZE = 4;
   
   public static void main(String[] args) {
      keyboard = new Scanner(System.in);
      Board2048 theBoard = new Board2048(GAME_SIZE);
      theBoard.newTile(); //Place the first tile
      System.out.println("The board:\n"+theBoard);
      
      //Play until the game is over
      while(!theBoard.gameOver()){
         int direction = getMove(theBoard);
         theBoard = theBoard.shift(direction);
         theBoard.newTile();
         System.out.println("The board:\n"+theBoard);       
      }//while
      
      System.out.println("Game Over!");
      keyboard.close();
   }
  
   private static int getMove(Board2048 board){
      //Get a valid game move from the user, using console input.
      //It will keep prompting until a valid move is chosen.
      boolean valid = false;
      int dir = 0; //The direction that will be returned at the end
      do{
         System.out.print("Type i,j,k,l or w,a,s,d for up,left,down,right:");
         String line = keyboard.nextLine().toLowerCase();
         if(line.length()>0){
            char c = line.charAt(0);
            if(c=='i' || c=='j' || c=='k' || c=='l' ||
               c=='w' || c=='a' || c=='s' || c=='d' ){
               if(c=='i' || c=='w') dir=Board2048.UP;
               else if(c=='j' || c=='a') dir=Board2048.LEFT;
               else if(c=='k' || c=='s') dir=Board2048.DOWN;
               else if(c=='l' || c=='d') dir=Board2048.RIGHT;
            }
            valid = board.validMove(dir);
         }
         if(!valid)
            System.out.println("Invalid move.");
      }while(!valid); //Keep asking until a valid response is received.
      
      return dir;
   }//getMove
   
}//TestPhase3

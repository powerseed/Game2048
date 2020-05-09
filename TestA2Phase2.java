public class TestA2Phase2 {
   //Test the simple constructor, the toString method, and the shift method
   //The others are tested indirectly by these.
   
   public static void main(String[] args) { 
      //Start with the same test board.
      Board2048 test = new Board2048(new int[][]{{2,0,2,4},{0,0,0,8},{2,2,2,2},{0,8,0,8}});
      //Store the results of each shift in separate variables
      Board2048 test2,test3,test4,test5;
      //Test the constructor and also toString
      System.out.println("Blank 5x5 board:\n"+new Board2048(5));
      System.out.println("Blank 2x2 board:\n"+new Board2048(2));
      System.out.println("Test board is:\n"+test);
      //Test shift in all 4 directions
      System.out.println("After shifting LEFT:\n"+(test2=test.shift(Board2048.LEFT)));
      System.out.println("THEN shifting RIGHT:\n"+(test3=test2.shift(Board2048.RIGHT)));
      System.out.println("THEN shifting DOWN:\n"+(test4=test3.shift(Board2048.DOWN)));
      System.out.println("THEN shifting UP:\n"+(test5=test4.shift(Board2048.UP)));
      //Make sure the original remains untouched
      System.out.println("Original board should not have changed:\n"+test);
      
   }
   
}

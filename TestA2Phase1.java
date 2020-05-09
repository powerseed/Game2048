import java.util.Arrays;

public class TestA2Phase1 {
   //Test only the alterOneLine method in Phase 1 of Assignment 2.
   
   //The test inputs to be run through the alterOneLine method
   private static int[][] testCases =
       { {0,2,0,0,8,0,4},
         {0,0,0,0,8,2,4},
         {2,4,2,0,0,0,0},
         {2,0,2,4,0,0,4},
         {2,0,4,2,0,0,4},
         {2,0,2,2,0},
         {2,0,2,2,0,2},
         {2,0,2,4,0,8} };

   public static void main(String[] args) {
      //run all the tests 1 time
      for(int i=0; i<testCases.length-1; i++)
         runTest(testCases[i],1);
      //except the last one, which is run 4 times.
      runTest(testCases[testCases.length-1],4);
   }//main
 
   private static void runTest(int[] testCase, int nTimes){
      //Run nTimes tests on the given testCase
      for(int i=0; i<nTimes; i++){
         System.out.print("testing "+Arrays.toString(testCase)+" ");
         boolean result = Board2048.alterOneLine(testCase);
         System.out.println("gives "+result+" and "+Arrays.toString(testCase));
      }//for
   }//runTest
}//TestPhase1 class

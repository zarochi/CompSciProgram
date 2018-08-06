public class action
{
   int x;//where the move is on the x axis
   int y;//where the move is on the y axis
   
   public action(int X, int Y)  //creates an action with a specific move
   {
      x=X;
      y=Y;
   }
   
   public int[] getAction()   //returns the x,y coordinate of the action in array form. action[0]=x, action[1]=y
   {
      int[] action = new int[]{x,y};
      return action;
   }
   
   public void printAction()
   {
      System.out.println("("+x+","+y+")");
   }
   
   public static boolean isValid(String[][] board, action a)   //checks to see if the action is possible givin the current gamestate
   {
      int[] action = a.getAction();
      if (action[1]<3&&action[0]<3)
      {
         if (board[action[1]][action[0]].equalsIgnoreCase("O")||board[action[1]][action[0]].equalsIgnoreCase("X"))
            return false;
         else
            return true;
      }
      else
         return false;
   }
}
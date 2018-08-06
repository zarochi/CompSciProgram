public class game
{
   player player1;//this is the player character
   player player2;//this is the AI controlled player
   String[][] b = new String[3][3]; //represents the board
   
   public game(String[][] board) //creates the game object with a board initial state
   {
      for (int x=0;x<board.length;x++)
      {
         for (int y=0;y<board[x].length;y++)
         {
            b[x][y]=board[x][y];
         }
      }
      player1=new player(1);
      player2=new player(2);
   }
   
   public String[][] getBoard()
   {
      return b;
   }
   
   public boolean validMove(action a)
   {
      return action.isValid(b,a);
   }
   
   public boolean validMove(int x, int y) //takes the player's move and uses the action class to ensure it is valid
   {
      action a=new action(x,y);   //creates the action
      return action.isValid(b,a);   //returns whether the action is valid or not based on the current gamestate
   }
   
   public void setTurnPlayer(int t)//takes in an integer (either 1 or 2) to change the turn player
   {
      if (t==1)
      {
         player1.setTurn(true);
         player2.setTurn(false);
      }
      else if (t==2)
      {
         player2.setTurn(true);
         player1.setTurn(false);
      }
      else
         System.out.println("Invalid player");
   }
   
   public int getTurnPlayer()
   {
      if (player1.isTurn())
         return 1;
      else
         return 2;
   }
   
   public boolean applyChange(action a) //applies the action to the board
   {
      int[] action = a.getAction();
      if (validMove(action[0],action[1]))//ensures that the move is valid before changing the board
      {
         if (player1.isTurn())            //changes the board based on which player is currently the turn player
            b[action[1]][action[0]]="X";
         if (player2.isTurn())
            b[action[1]][action[0]]="O";
         return true;
      }
      else
      {
         //System.out.println("Invalid move, has not been applied");
         return false;
      }
   }
   
   public String[][] Result(action a)  //returns the action as applied to the board
   {
      int[] action = a.getAction();
      String[][] board=new String[3][3];
      for (int x=0;x<board.length;x++)
         for (int y=0;y<board.length;y++)
            board[x][y]=b[x][y];
      if (validMove(a))//ensures that the move is valid before changing the board
      {
         if (player1.isTurn())            //changes the board based on which player is currently the turn player
            board[action[1]][action[0]]="X";
         if (player2.isTurn())
            board[action[1]][action[0]]="O";
         return board;
      }
      else
      {
         //System.out.println("Invalid move, has not been applied");
         return b;
      }
   }
   
   public void printBoard()  //used to print out the board
   {
      System.out.println("-------------");
      for (int x=0;x<b.length;x++)
      {
         System.out.println("| "+b[x][0]+" | "+b[x][1]+" | "+b[x][2]+" |");
         System.out.println("-------------");
      }
   }
   
   public int isTerminal(String[][] change)   //checks to see if the board is a terminal condition. Returns 2 if the AI wins
   {                                         //-1 if the player wins and 0 if it is a tie. returns -2 if it is not terminal
      if (
      (change[0][0].equalsIgnoreCase("X")&&change[0][1].equalsIgnoreCase("X")&&change[0][2].equalsIgnoreCase("X"))||
      (change[1][0].equalsIgnoreCase("X")&&change[1][1].equalsIgnoreCase("X")&&change[1][2].equalsIgnoreCase("X"))||
      (change[2][0].equalsIgnoreCase("X")&&change[2][1].equalsIgnoreCase("X")&&change[2][2].equalsIgnoreCase("X"))||
      (change[0][0].equalsIgnoreCase("X")&&change[1][0].equalsIgnoreCase("X")&&change[2][0].equalsIgnoreCase("X"))||
      (change[0][1].equalsIgnoreCase("X")&&change[1][1].equalsIgnoreCase("X")&&change[2][1].equalsIgnoreCase("X"))||
      (change[0][2].equalsIgnoreCase("X")&&change[1][2].equalsIgnoreCase("X")&&change[2][2].equalsIgnoreCase("X"))||
      (change[0][0].equalsIgnoreCase("X")&&change[1][1].equalsIgnoreCase("X")&&change[2][2].equalsIgnoreCase("X"))||
      (change[0][2].equalsIgnoreCase("X")&&change[1][1].equalsIgnoreCase("X")&&change[2][0].equalsIgnoreCase("X"))
      )//checks to see if the AI has 3 in a row
         return 2;
      else if (
      (change[0][0].equalsIgnoreCase("O")&&change[0][1].equalsIgnoreCase("o")&&change[0][2].equalsIgnoreCase("o"))||
      (change[1][0].equalsIgnoreCase("o")&&change[1][1].equalsIgnoreCase("o")&&change[1][2].equalsIgnoreCase("o"))||
      (change[2][0].equalsIgnoreCase("o")&&change[2][1].equalsIgnoreCase("o")&&change[2][2].equalsIgnoreCase("o"))||
      (change[0][0].equalsIgnoreCase("o")&&change[1][0].equalsIgnoreCase("o")&&change[2][0].equalsIgnoreCase("o"))||
      (change[0][1].equalsIgnoreCase("o")&&change[1][1].equalsIgnoreCase("o")&&change[2][1].equalsIgnoreCase("o"))||
      (change[0][2].equalsIgnoreCase("o")&&change[1][2].equalsIgnoreCase("o")&&change[2][2].equalsIgnoreCase("o"))||
      (change[0][0].equalsIgnoreCase("o")&&change[1][1].equalsIgnoreCase("o")&&change[2][2].equalsIgnoreCase("o"))||
      (change[0][2].equalsIgnoreCase("o")&&change[1][1].equalsIgnoreCase("o")&&change[2][0].equalsIgnoreCase("o"))
      )//checks to see if the player has 3 in a row
         return -1;
      else if (
      (!change[0][0].equalsIgnoreCase(" "))&&
      (!change[0][1].equalsIgnoreCase(" "))&&
      (!change[0][2].equalsIgnoreCase(" "))&&
      (!change[1][0].equalsIgnoreCase(" "))&&
      (!change[1][1].equalsIgnoreCase(" "))&&
      (!change[1][2].equalsIgnoreCase(" "))&&
      (!change[2][0].equalsIgnoreCase(" "))&&
      (!change[2][1].equalsIgnoreCase(" "))&&
      (!change[2][2].equalsIgnoreCase(" "))
      )//checks to see if the board is full
         return 0;
      else
         return -2;
   }
   
   public int utility(action a)  //takes in an action and returns an integer for minmax
   {
      String[][] change=Result(a);
      int isTerminal=isTerminal(change);
      if (isTerminal==-2) //checks the case if the change is not a terminal case
      {
         if (
         (change[0][0].equalsIgnoreCase("o")&&change[0][1].equalsIgnoreCase("o")&&change[0][2].equalsIgnoreCase("X")&&validMove(2,0))|| //checks to see if it blocks the other player
         (change[0][1].equalsIgnoreCase("o")&&change[0][2].equalsIgnoreCase("o")&&change[0][0].equalsIgnoreCase("X")&&validMove(0,0))|| //horizontally
         (change[1][0].equalsIgnoreCase("o")&&change[1][1].equalsIgnoreCase("o")&&change[1][2].equalsIgnoreCase("X")&&validMove(2,1))||
         (change[1][1].equalsIgnoreCase("o")&&change[1][2].equalsIgnoreCase("o")&&change[1][0].equalsIgnoreCase("X")&&validMove(0,1))||
         (change[2][0].equalsIgnoreCase("o")&&change[2][1].equalsIgnoreCase("o")&&change[2][2].equalsIgnoreCase("X")&&validMove(2,2))||
         (change[2][1].equalsIgnoreCase("o")&&change[2][2].equalsIgnoreCase("o")&&change[2][0].equalsIgnoreCase("X")&&validMove(0,2))||
         
         (change[0][0].equalsIgnoreCase("o")&&change[1][0].equalsIgnoreCase("o")&&change[2][0].equalsIgnoreCase("X")&&validMove(0,2))|| //checks to see if it blocks the other player
         (change[1][0].equalsIgnoreCase("o")&&change[2][0].equalsIgnoreCase("o")&&change[0][0].equalsIgnoreCase("X")&&validMove(0,0))|| //vertically
         (change[0][1].equalsIgnoreCase("o")&&change[1][1].equalsIgnoreCase("o")&&change[2][1].equalsIgnoreCase("X")&&validMove(1,2))||
         (change[1][1].equalsIgnoreCase("o")&&change[2][1].equalsIgnoreCase("o")&&change[0][1].equalsIgnoreCase("X")&&validMove(1,0))||
         (change[0][2].equalsIgnoreCase("o")&&change[1][2].equalsIgnoreCase("o")&&change[2][2].equalsIgnoreCase("X")&&validMove(2,2))||
         (change[1][2].equalsIgnoreCase("o")&&change[2][2].equalsIgnoreCase("o")&&change[0][2].equalsIgnoreCase("X")&&validMove(2,0))||
         
         (change[1][1].equalsIgnoreCase("o")&&change[0][0].equalsIgnoreCase("o")&&change[2][2].equalsIgnoreCase("X")&&validMove(2,2))|| //checks to see if it blocks the other player
         (change[1][1].equalsIgnoreCase("o")&&change[2][0].equalsIgnoreCase("o")&&change[0][2].equalsIgnoreCase("X")&&validMove(2,0))|| //diagonally
         (change[1][1].equalsIgnoreCase("o")&&change[0][2].equalsIgnoreCase("o")&&change[2][0].equalsIgnoreCase("X")&&validMove(0,2))||
         (change[1][1].equalsIgnoreCase("o")&&change[2][2].equalsIgnoreCase("o")&&change[0][0].equalsIgnoreCase("X")&&validMove(0,0))||
         
         (change[0][0].equalsIgnoreCase("o")&&change[2][0].equalsIgnoreCase("o")&&change[1][0].equalsIgnoreCase("X")&&validMove(0,1))|| //checks to see if it blocks the potential
         (change[0][1].equalsIgnoreCase("o")&&change[2][1].equalsIgnoreCase("o")&&change[1][1].equalsIgnoreCase("X")&&validMove(1,1))|| //to make a move that could have the row closed
         (change[0][2].equalsIgnoreCase("o")&&change[2][2].equalsIgnoreCase("o")&&change[1][2].equalsIgnoreCase("X")&&validMove(2,1))|| //on the next move
         (change[0][0].equalsIgnoreCase("o")&&change[0][2].equalsIgnoreCase("o")&&change[0][1].equalsIgnoreCase("X")&&validMove(1,0))||
         (change[1][0].equalsIgnoreCase("o")&&change[1][2].equalsIgnoreCase("o")&&change[1][1].equalsIgnoreCase("X")&&validMove(1,1))||
         (change[2][0].equalsIgnoreCase("o")&&change[2][2].equalsIgnoreCase("o")&&change[2][1].equalsIgnoreCase("X")&&validMove(1,2))||
         (change[0][0].equalsIgnoreCase("O")&&change[2][2].equalsIgnoreCase("O")&&change[1][1].equalsIgnoreCase("X")&&validMove(1,1))||
         (change[0][2].equalsIgnoreCase("O")&&change[2][0].equalsIgnoreCase("O")&&change[1][1].equalsIgnoreCase("X")&&validMove(1,1))
         )
            return -1;
         else if (
         (change[0][0].equalsIgnoreCase("X")&&change[0][1].equalsIgnoreCase("X"))|| //checks to see if it gives the AI 2 in a row
         (change[0][1].equalsIgnoreCase("X")&&change[0][2].equalsIgnoreCase("X"))|| //horizontally
         (change[1][0].equalsIgnoreCase("X")&&change[1][1].equalsIgnoreCase("X"))||
         (change[1][1].equalsIgnoreCase("X")&&change[1][2].equalsIgnoreCase("X"))||
         (change[2][0].equalsIgnoreCase("X")&&change[2][1].equalsIgnoreCase("X"))||
         (change[2][1].equalsIgnoreCase("X")&&change[2][2].equalsIgnoreCase("X"))||
         
         (change[0][0].equalsIgnoreCase("X")&&change[1][0].equalsIgnoreCase("X"))|| //checks to see if it gives the AI 2 in a row
         (change[1][0].equalsIgnoreCase("X")&&change[2][0].equalsIgnoreCase("X"))|| //vertically
         (change[0][1].equalsIgnoreCase("X")&&change[1][1].equalsIgnoreCase("X"))||
         (change[1][1].equalsIgnoreCase("X")&&change[2][1].equalsIgnoreCase("X"))||
         (change[0][2].equalsIgnoreCase("X")&&change[1][2].equalsIgnoreCase("X"))||
         (change[1][2].equalsIgnoreCase("X")&&change[2][2].equalsIgnoreCase("X"))||
         
         (change[1][1].equalsIgnoreCase("X")&&change[0][0].equalsIgnoreCase("X"))|| //checks to see if it gives the AI 2 in a row
         (change[1][1].equalsIgnoreCase("X")&&change[2][0].equalsIgnoreCase("X"))|| //diagonally
         (change[1][1].equalsIgnoreCase("X")&&change[0][2].equalsIgnoreCase("X"))||
         (change[1][1].equalsIgnoreCase("X")&&change[2][2].equalsIgnoreCase("X"))||
         
         (change[0][0].equalsIgnoreCase("X")&&change[2][0].equalsIgnoreCase("X"))|| //checks to see if it gives the AI the potential
         (change[0][1].equalsIgnoreCase("X")&&change[2][1].equalsIgnoreCase("X"))|| //to make a move that could have the row closed
         (change[0][2].equalsIgnoreCase("X")&&change[2][2].equalsIgnoreCase("X"))|| //on the next move
         (change[0][0].equalsIgnoreCase("X")&&change[0][2].equalsIgnoreCase("X"))||
         (change[1][0].equalsIgnoreCase("X")&&change[1][2].equalsIgnoreCase("X"))||
         (change[2][0].equalsIgnoreCase("X")&&change[2][2].equalsIgnoreCase("X"))||
         (change[0][0].equalsIgnoreCase("X")&&change[2][2].equalsIgnoreCase("X"))||
         (change[0][2].equalsIgnoreCase("X")&&change[2][0].equalsIgnoreCase("X"))
         )
            return 1;
         else
            return 0;
      }
      else  //if it is a terminal case it returns whether or not it is benificial
         return isTerminal;
   }
}
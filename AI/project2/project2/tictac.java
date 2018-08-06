import cs1.Keyboard;
import java.util.ArrayList;

public class tictac
{
   public static void main(String[] args)
   {
      tictac t = new tictac();
      String[][] board = new String[][]{{" "," "," "},{" "," "," "},{" "," "," "}}; //creates the initial board
      game g = new game(board);
      g.setTurnPlayer(2);
      boolean isTerminal=false; //keeps track of whether or not the game is over
      int terminal = -2;
      while (isTerminal!=true)
      {
         g.printBoard();
         terminal = g.isTerminal(g.getBoard());
         if (terminal!=-2)
         {
            if (terminal==2)
               System.out.println("You tried your best. Thank you for playing.");
            if (terminal==-1)
               System.out.println("Congratulations, you have won!");
            if (terminal==0)
               System.out.println("It was a tie game. Thank you for playing.");
            isTerminal=true;
         }
         else
         {
            if (g.getTurnPlayer()==2) //handles the player's turn
            {
               System.out.println("Enter the x coordinate of your play");
               int X=Keyboard.readInt();
               System.out.println("Enter the y coordinate of your play");
               int Y=Keyboard.readInt();
               action a = new action(X, Y);
               if (g.validMove(a))
               {
                  g.applyChange(a);
                  g.setTurnPlayer(1);//sets the turn player to the AI
               }
               else
                  System.out.println("This move is not valid, please re-enter your move");
            }
            else //handles the AI's turn
            {
               ArrayList<action> positive=new ArrayList<action>(); //creates an arraylist to sort all the possible outcomes
               ArrayList<action> negitive=new ArrayList<action>();
               ArrayList<action> neutral=new ArrayList<action>();
               boolean moveMade=false;//tracks whether or not the move was made
               for (int x=0;x<3;x++)   //sort all possible moves by whether they are positive negitive or neutral
                  for (int y=0;y<3;y++)
                  {
                     int util = g.utility(new action(x, y));
                     if (util==1)
                        positive.add(new action(x, y));
                     if (util==0)
                        neutral.add(new action(x, y));
                     if (util==-1)
                        negitive.add(new action(x, y));
                     if (util==2)   //the case if the AI wins
                     {
                        if (g.validMove(new action(x, y))&&!moveMade)
                        {
                           moveMade=g.applyChange(new action(x, y));
                           break;
                        }
                     }
                  }  
                       
               /*System.out.println("Positive:");   If this is uncommented it will show the list of positive moves, neutral moves and negitive moves
               for (int x=0;x<positive.size();x++)
                  positive.get(x).printAction();
               System.out.println("Neutral:");
               for (int x=0;x<neutral.size();x++)
                  neutral.get(x).printAction();
               System.out.println("Negitive:");
               for (int x=0;x<negitive.size();x++)
                  negitive.get(x).printAction();*/
               
               int x=0,y=0,z=0;//used to count the iterations of the below while loops
               while (negitive.size()!=0&&!moveMade) //makes the first negitive move to disrupt the player
               {
                  if (negitive.size()>=x)
                  {
                     moveMade=g.applyChange(negitive.get(x));
                     x++;
                  }
                  else
                     break;
               }
               while (positive.size()!=0&&!moveMade) //makes the first positive move if a negitive one isn't present
               {
                  if (positive.size()>=y)
                  {
                     moveMade=g.applyChange(positive.get(y));
                     y++;
                  }
                  else
                     break;
               }
               while (neutral.size()!=0&&!moveMade)
               {
                  if (neutral.size()>=z)
                  {
                     moveMade=g.applyChange(neutral.get(z));
                     z++;
                  }
                  else
                     break;
               }
               g.setTurnPlayer(2);//sets the turn player to the player
            }
         }
         
      }
   }
   
}
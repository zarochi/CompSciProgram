import java.util.ArrayList;

public class player
{
   boolean isTurnPlayer=false;//tracks whether or not it is the player's turn
   ArrayList<Integer> possessions = new ArrayList<Integer>();//keeps track of what spaces each player possesses
   int player=-1;
   
   public player(int p) //numbers the player
   {
      player=p;
   }
   
   public int getNumber() //gets the player number
   {
      return player;
   }
   
   public void setTurn(boolean b)   //allows for setting of whether or not it is a players turn
   {
      isTurnPlayer=b;
   }
   
   public boolean isTurn() //returns whether or not it is this player's turn
   {
      return isTurnPlayer;
   }
   
}
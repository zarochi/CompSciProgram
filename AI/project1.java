import cs1.Keyboard;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Stack;

public class project1
{
   FileReader Reader;
   BufferedReader r;
   File f;
   static ArrayList<point> cities = new ArrayList<point>();   //stores the cities that have already been used so it doesn't replicate points
   
   public static void main(String[] args)
   {
      System.out.println("Enter the name of the text file with the connecting contries. You can omit the .txt at the end.");
      String file=Keyboard.readString();
      project1 p = new project1();
      p.makeReader(file);
      p.makePoints();
      long startTime=System.nanoTime();
      point end = p.BFS("USA", cities.get(0));
      //System.out.println(end.getCityName());
      long endTime=System.nanoTime();
      System.out.println("BFS took: "+(endTime-startTime)+" nanoseconds to find a path.");
      p.setupNeighbours();
      startTime=System.nanoTime();
      end = p.DFS("USA", cities.get(0));
      //System.out.println(end.getCityName());
      endTime=System.nanoTime();
      System.out.println("DFS took: "+(endTime-startTime)+" nanoseconds to find a path.");
   }
   
   public void makeReader(String filename) //creates all the necessary file objects to open it for reading
   {
      String file=filename;
      if (file.substring(file.length()-4,file.length()).equalsIgnoreCase(".txt"))   //checks to see if the file name had the file type on the end
         f=new File(file); //creates file object
      else
      {
         file=file+".txt";
         f=new File(file); //creates file object
      }
      try
      {
         Reader = new FileReader(f); //creates the file reader object
         r = new BufferedReader(Reader); //creates the buffered reader
      }
      catch (Exception e)
      {
         System.out.println(e.toString());
      }
   }
   
   public void makePoints()   //creates a point object to reference each city
   {
      try
      {
         String s = r.readLine();
         while (s!=null)
         {
            StringTokenizer tokenizer=new StringTokenizer(s);
            String city=tokenizer.nextToken();
            int cityLocation=-1; //stores index of the city
            
            boolean inArray=false; //will be turned to true if the city is in the arraylist
            for (int x=0;x<cities.size();x++)   //adds the city to the arraylist if it is not already there
               if (cities.get(x).getCityName().equalsIgnoreCase(city))
               {
                  inArray=true;
                  cityLocation=x;
               }
            if (!inArray)
            {     
               cities.add(new point(city));
               cityLocation=cities.size()-1;
            }
            
            while (tokenizer.hasMoreTokens())
            {
               cities.get(cityLocation).addConnection(tokenizer.nextToken());
            }
            s=r.readLine();
         }
         //for (int y=0;y<cities.size();y++)
            //System.out.println(cities.get(y).getCityName());
         r.close();
      }
      catch (Exception e)
      {
         System.out.println(e.toString());
      }
   }
   
   public point BFS(String goal, point s0) //does the Breadth first search, couldnt figure out how to print the path
   {
      //BFS(G,s0)
      String mark="";
      int index=-1;
      //ArrayList<String> mark = new ArrayList<String>();
      //create a queue Q
      ArrayList<point> Q = new ArrayList<point>(); //This uses .add(E e) to enqueue and .remove() to dequeue
      //v= enqueue s0 onto Q
      Q.add(s0);
      //mark v
      mark=s0.getCityName();
      //while Q is not empty
      try
      {
         while (Q.size()!=0)
         {
          //t = Q.dequeue()
            point t = Q.remove(0);
            //System.out.println("Taking out: "+t.getCityName()+" connections: "+t.getSize());
           //if t is what we are looking for:
            if (t.getCityName().equalsIgnoreCase(goal))
            {
            //return t
               return t;
            }
            //for all edges e in G.adjacentEdges(t) do
            else
            {  
               for (int x=0;x<t.getSize();x++)
               {
               //u = G.adjacentVertex(t,e)
               
                  for (int y=0;y<cities.size();y++)
                  {
                     String c = cities.get(y).getCityName();
                     if (c.equalsIgnoreCase(t.getCity(x)))
                        index = y;
                  }
                  
                  point u =cities.get(index);
                  //if u is not marked:
                  if (!mark.equalsIgnoreCase(u.getCityName()))
                  {
                     // mark u
                     mark=u.getCityName();
                     //enqueue u onto Q
                     //System.out.println("Putting in: "+u.getCityName());
                     Q.add(u);
                  }
                  
               }
               //Object[] queue = Q.toArray();
               //for (int y=0;y<queue.length;y++)
                  //System.out.print(queue[y].toString()+" ");
               //System.out.println("\n");
            }
         }
      }
      catch (Exception e)
      {
         System.out.println(e.toString());
      }
      //return none
      return null;
   }
   
   public void setupNeighbours() //sets up the neighbours for use with DFS
   {
      for (int x=0;x<cities.size();x++)
      {
         for (int y=0;y<cities.size();y++)
         {
            cities.get(x).setNeighbours(cities.get(y));
         }
      }
      //for (int x=0;x<cities.size();x++)   
         //cities.get(x).printNeighbours();
   }
   
   public point DFS(String goal, point s0) //does the Depth first search, couldnt figure out how to print the path
   {
       //create a stack S
      Stack<point> S = new Stack<point>();
      String mark="";
      int index=-1;
       //v= push s0 onto S
      S.push(s0);
       //mark v
      mark=s0.getCityName();
       //while S is not empty:
      while (!S.empty())
      {
       //t = S.pop()
         point t = S.pop();
       //if t is what we are looking for:
         if (t.getCityName().equalsIgnoreCase(goal))
         {
            //return t
            return t;
         }
         //for all unmarked neighbouring nodes u with t do
         else
         {  
            for (int x=0;x<t.getNSize();x++)
            {
               for (int y=0;y<cities.size();y++)
               {
                  String c = cities.get(y).getCityName();
                  if (c.equalsIgnoreCase(t.getNeighbour(x)))
                     index = y;
               }
               point u = cities.get(index);
            //if u is not marked:
               if (!mark.equalsIgnoreCase(u.getCityName()))
               {
               //mark u
                  mark=u.getCityName();
               //push u onto S
                  if (u.getCityName().equalsIgnoreCase(goal))
                  {
                  //return t
                     return u;
                  }
                  S.push(u);
               }
            }
         }
       //return none
      }
      return null;
   }
}
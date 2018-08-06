import java.util.ArrayList;

public class point implements Comparable<point>  //stores a city/country and what citys/countrys it connects to
{
   ArrayList<String> connect = new ArrayList<String>();
   ArrayList<String> adjacent = new ArrayList<String>(); //stores the citys' neighbours
   String cityName = "";
   
   public point(point p)
   {
      for (int x=0;x<p.getSize();x++)
         connect.add(p.getCity(x));
      cityName=p.getCityName();   
   }
   public point(ArrayList<String> connections)
   {
      for (int x=0;x<connect.size();x++)
         connect.add(connections.get(x));
   }
   
   public point(String name)
   {
      cityName=name;
   }
   
   public void setNeighbours(point p)
   {
      ArrayList<String> c = p.getConnections();
      if (c.contains(cityName))
         for (int x=0;x<c.size();x++)
            if (!c.get(x).equalsIgnoreCase(cityName))
               adjacent.add(c.get(x));
   }
   
   public void printNeighbours()
   {
      if (adjacent.size()==0)
         System.out.println("Has no neighbours");
      else
      {
         System.out.println(cityName);
         for (int x=0;x<adjacent.size();x++)
            System.out.println("\t"+adjacent.get(x));
      }
   }
   
   public int getNSize()
   {
      return adjacent.size();
   }
   
   public String getNeighbour(int index)
   {
      return adjacent.get(index);
   }
   
   public String getCityName()
   {
      return cityName;
   }
   
   public String toString()
   {
      return cityName;
   }
   
   public int getSize()
   {
      return connect.size();
   }
   
   public String getCity(int index)
   {
      return connect.get(index);
   }
   
   public ArrayList<String> getConnections()
   {
      return connect;
   }
   
   public void addConnection(String c) //takes a city and adds it to the connections for this point
   {
      connect.add(c);
   }
   
   public void printPoint()
   {
      System.out.println("Connections for: "+cityName);
      for (int x=0;x<connect.size();x++)
         System.out.println("\t"+connect.get(x));
   }
   
   public int compareTo(point p)
   {
      for (int x=0;x<getSize();x++)
         if (getCity(x).equalsIgnoreCase(p.getCityName()))
            return -1;
      if (cityName.equalsIgnoreCase(p.getCityName()))
         return 0;
      else
         return 1;
   }
}
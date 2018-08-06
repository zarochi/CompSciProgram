import cs1.Keyboard;
  
public class LRU
{
   int[][] array; //filled as [frames][counter]
   int faults=0; //page faults
   public LRU(int numFrames)	//constructor inputs the number of frames and creates the array
   {
      array=new int[numFrames][2];
      for (int y=0;y<numFrames;y++)	//fills array with sentenal data
      {
         array[y][0]=-1;
         array[y][1]=-1;
      }
   }

   public void execute() //inputs the requests and performs the algorithm
   {
      for (int x=0;x<10;x++)	//does 10 page requests
      {
         System.out.println("Enter an integer value for the page (5-digit max)");
         int request=Keyboard.readInt();
         int counter=-1;
         int place=-1;	//holds the place of the min counter
         if (x<array.length)	//fills the empty space
         {
            array[x][0]=request;
            array[x][1]=0;	//sets up the counter
            faults++;	//incriments the page fault
         }
         else
         {
            for (int y=0;y<array.length;y++)	//checks array for lowest counter
            {
               if (array[y][1]>=counter)
               {
                  counter=array[y][1];
                  place=y;
               }
            }
            for (int y=0;y<array.length;y++)
            {
               if (array[y][0]==request)
               {
                  place=y;
               }
            }
            if (array[place][0]!=request)
               faults++;
            array[place][0]=request;
            array[place][1]=0;
         }
         System.out.println("Pages in memory");
         for (int y=0;y<array.length;y++)
            System.out.println("|"+array[y][0]+"|");
         for (int y=0;y<array.length;y++)	//incriments the counter for each request
            if (array[y][1]!=-1)
               array[y][1]++;
      }
      System.out.println("Page Faults: "+faults);
   }
   
   public static void main(String[] args)
   {
      System.out.println("Enter the number of frames");
      int frames=Keyboard.readInt();
      LRU lru=new LRU(frames);
      lru.execute();
   }
}
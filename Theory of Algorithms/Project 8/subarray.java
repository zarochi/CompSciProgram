public class subarray
{
   public static void main(String[] args)
   {
      int[] price={100,113,110,85,105,102,86,63,81,101,94,106,101,79,94,90,97};
      subarray s = new subarray();
      int[] change = s.computeChange(price);
      for (int x=0;x<price.length;x++)
         System.out.print(price[x]+",");
      System.out.print("\n");
      for (int x=0;x<change.length;x++)
         System.out.print(change[x]+",");
      System.out.print("\n");   
      System.out.println("Linear max: "+s.linear(change));
      maxSubArray max = s.findMax(change, 0, change.length-1);
      System.out.println("Recursive max: "+max.max+" Start: "+max.low+" End: "+max.high);
      System.out.println("\nBelow is the same with a random price array:");
      price = new int[15];
      for (int x=0;x<price.length;x++)
         price[x] = (int) (Math.random()*100);
      change = s.computeChange(price);
      for (int x=0;x<price.length;x++)
         System.out.print(price[x]+",");
      System.out.print("\n");
      for (int x=0;x<change.length;x++)
         System.out.print(change[x]+",");
      System.out.print("\n");   
      System.out.println("Linear max: "+s.linear(change));
      max = s.findMax(change, 0, change.length-1);
      System.out.println("Recursive max: "+max.max+" Start: "+max.low+" End: "+max.high);
   }

   public int[] computeChange(int[] p) //makes the change array
   {
      int[] change = new int[p.length-1];
      for (int x=0;x<change.length;x++)
         change[x]=p[x+1]-p[x];
      return change;
   }
   
   public int linear(int[] c) //finds the max in n^2 time
   {
      int max=0;
      
      for (int x=0;x<c.length;x++)
      {
         int current=0;
         for (int y=x;y<c.length;y++)
         {
            current+=c[y];
            if (current>max)
               max=current;
         }
      }
      return max;
   }
   
   public maxSubArray findMax(int[] c, int l, int h)
   {
      if (l==h) //handles the base case
         return new maxSubArray(l, h, c[l]);
      else
      {
         int mid = (int)((l+h)/2);
         maxSubArray left = findMax(c, l, mid);
         maxSubArray right = findMax(c, mid+1, h);
         maxSubArray crossing = findCrossing(c, l, mid, h);
         
         if (left.max>=right.max&&left.max>=crossing.max)   //handles if left is largest
            return left;
         else if (right.max>=left.max&&right.max>=crossing.max)   //handles if right is largest
            return right;
         else
            return crossing;
      }
   }
   
   public maxSubArray findCrossing(int[] c, int l, int m, int h)
   {
      //sets up the left hand side
      maxSubArray left = new maxSubArray();
      left.max=-10000000;
      int sum = 0;
      left.high = m; //sets the high on the left side to the midpoint
      for (int x=m;x>=l;x--)
      {
         sum += c[x];
         if (sum > left.max)
         {
            left.max=sum;
            left.low=x;
         }
      }
      //sets up the right hand side
      maxSubArray right = new maxSubArray();
      right.max=-10000000;
      sum = 0;
      for (int x=m+1;x<=h;x++)
      {
         sum +=c[x];
         if (sum > right.max)
         {
            right.max=sum;
            right.high=x;
         }
      }
      return new maxSubArray(left.low, right.high, left.max+right.max);
   }
   
   public class maxSubArray
   {
      public int low=-1, high=-1, mid=-1, max=-1;
      public maxSubArray(){}
      public maxSubArray(int l, int h, int m)
      {
         low=l;
         high=h;
         max=m;
      }
   }
}
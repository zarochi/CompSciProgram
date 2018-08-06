import cs1.Keyboard;

public class heap
{
   int[] array;
   int n;
   int largest=0;
   
   public void maxHeap(int index)   //puts the array in max heap order
   {
      int left=index*2, right=index*2+1;  //references the numbers to the left and right of the current one
      if (left <= n && array[left] > array[index])   //checks to see if the left value is larger than the current one
      {
         largest=left;
      }
      else
         largest=index;
      if (right <= n && array[right] > array[largest])   //checks to see if the right value is larger than the current one
      {
         largest=right;
      }
      if (largest!=index)
      {
         swap(index, largest);
         maxHeap(largest);
      }
   }
   
   public void swap(int i, int j)   //swaps 2 values in the array
   {
      int t=array[i];
      array[i]=array[j];
      array[j]=t;
   }
   
   public void printArray()   //prints all the values of the array
   {
      System.out.print("Values: [");
      for (int i=0; i<array.length; i++)
      {
         if (i==array.length-1)
            System.out.print(array[i]);
         else
            System.out.print(array[i]+", ");
      }
      System.out.print("]");
   }
   
   public void buildHeap() //builds the heap in max heap order
   {
      n=array.length-1;
      for (int i=n/2;i>=0;i--)
      {
         maxHeap(i);
      }
   }
   
   public void sort()   //sorts the heap
   {
      buildHeap();
      for (int i=n;i>0;i--)
      {
         swap(0,i);
         n=n-1;
         maxHeap(0);
      }
   }
   
   public void createArray(int max) //takes in the number of random integers fills the array with random integers
   {
      array=new int[max];
      for (int i=0;i<max;i++)
      {
         array[i]=(int)(Math.random()*100);
      }
   }
   
   public static void main(String[] args)
   {
      System.out.println("Enter the number of random integers to generate");
      int values=Keyboard.readInt();
      heap h=new heap();
      h.createArray(values);
      h.printArray();
      System.out.println("\nMax heap order:");
      h.buildHeap();
      h.printArray();
      System.out.println("\nSorted Array:");
      h.sort();
      h.printArray();
   }
}
    //create your own version of the ArrayList by completing this class definition
	 //look in ListInterface to see what methods you need to create
    
public class MyArrayList<anyType> implements ListInterface<anyType>
{
   private Object[] list;		//stores the actual elements
   private int numElements;	//used to keep track of the number of valid elements in the list
   	
   public MyArrayList()
   {
      list = new Object[10];	//start with a buffer size of 10
      numElements = 0;
   }
   
   private void doubleCapacity()	//private because this is a helper method that need not be used outside of the class
   {
      	//make list twice as big, i.e. given [A, B, C, null], results with [A, B, C, null, null, null, null, null]
      	//to be used if we add an element that would be over the capacity of list
      Object [] tmp = new Object[list.length * 2];
         
      for (int i = 0; i < tmp.length; i ++){
         if (i > list.length - 1)
            tmp[i] = null;
         else
            tmp[i] = list[i];
               
      }
      list = tmp;
         
   }
      
   private void cutCapacity()	//private because this is a helper method that need not be used outside of the class
   {
      	//make list half as big, i.e. given [A, B, C, null, null, null, null, null], results with [A, B, C, null]
      	//to be used if after removing an element, we have less than 1/3 of the capacity of list being used
      Object[] tmp = new Object[list.length /2];
      numElements /= 2;
         
      for (int i = 0; i < tmp.length; i ++){
         
         tmp[i] = list[i];
      }
      list = tmp;
   }
      
   public String toString()
   {
      String ans = "[";
      for (int i = 0; i < list.length; i ++){
         if (list[i] != null && i < list.length - 1){
            if (list[i + 1] == null)
               ans += list[i] + "";
            else
               ans += list[i] + ", ";
         
         }
      }//add all array elements with a comma separating each, i.e. [A, B, C] 
      
      return ans + "]";
   }
      //HERE
   public boolean add(Object x){
      numElements++;
      int nullindex = -1;
      for (int i = 0; i < list.length; i ++){
         nullindex = list[i] == null ? i : -1;
         if (nullindex > -1)
            break;
      }
         
      if (nullindex > -1)
         list[nullindex] = x;
      else{
         doubleCapacity();
         
         list[list.length /2] = x;
      }
      return true;
       
   }				
       //adds element x to the end of the list
     
   public boolean add(int index, Object x){
      //exception handler
      if (index > numElements)
         throw new IndexOutOfBoundsException(" The numElements is " + numElements + " List.size() is " + size()+ " and list.length is " + list.length);
      numElements++;
      Object tempElement = new Object();
       
      if (list[list.length -1] == null){
         tempElement = list[index];
         list[index] = x;
         for (int i = index + 1; i < list.length + 1; i ++){
            list[i] = tempElement;
            tempElement = list[i+1];
            
         }
       
      }
      else {
         doubleCapacity();
         tempElement = list[index];
         list[index] = x;
         for (int i = index + 1; i < index + 2; i ++){
            list[i] = tempElement;
            tempElement = list[i+1];
            
         }
      }
      return true;
   }	
       //adds element x at a particular index in list
       //elements to the right of that index are moved over one space to the right
   									
   public int size(){
      return numElements;
   }								
       //returns the number of elements in the list
       
   public anyType set(int index, Object x){
      if (index >= numElements){
         throw new IndexOutOfBoundsException(" The numElements is " + numElements + " List.size() is " + size()+ " and list.length is " + list.length);
      }
      Object temp = new Object();
      temp = list[index];
       
      list[index] = x;
       
      return (anyType)temp;
   }	
       //changes the element at a specific index to x, returning the element that was originally there
       
   public anyType get(int index){
      if (index >= numElements){
         throw new IndexOutOfBoundsException(" The numElements is " + numElements + " List.size() is " + size()+ " and list.length is " + list.length);
      }
      
      return (anyType)list[index];
       
   }			
       //returns the object at a specific index (first element is index 0)
       
   public anyType remove(int index){
      if (index >= numElements){
         throw new IndexOutOfBoundsException(" The numElements is " + numElements + " List.size() is " + size()+ " and list.length is " + list.length);
      }
      numElements--;
      Object former = new Object();
       
       
      former = list[index];
       
      for (int i = index; i < list.length; i ++){
         if (i < list.length - 1 && list[i] != null){
            list[i] = list[i+1];
         }
         else list[i] = null;
      }
       //null calculation
      int nulls = 0;
       
      for (int i = 0; i < list.length; i ++){
         nulls += (list[i] == null) ? 1 : 0;
      }
      if (nulls > 0 && numElements > 10){
         double ratio = list.length / nulls;
       
         if(ratio <= 1.5){//if there are more than or equal to 2/3 nulls
            cutCapacity();
         
         }
      }
      
      return (anyType)former;
       
   }
       			                                 // removes element from position index, moving
   															// elements at position index + 1 and higher to the
   															// left (subtracts 1 from their indices) and adjusts
   															// size;  returns the element formerly at index
      
}
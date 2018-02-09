
import java.util.*;
import java.io.*;


public class LinkedQueue extends LinkedList implements Serializable

{
	int size=0; //the number of element in queue

	  //return number of elements in the queue
	  public int size()
	  {  return size;    }

	  //return if the queue is empty
	  public boolean isEmpty()
	  {
		  return (size==0);
	  }
	  
     //return element at front
	 public Object front()
	  {
	  	return getFirst();
	  }

      //return element at tail
	  public Object tail()
	  {
	  	 return getLast();

	  }

	  //add element at tail
	  public void  enqueue(Object obj)
	  {
	  	size++;
	     addLast( obj);
	  }
      
      //add element at front
	  public Object dequeue()
	  { size--;
	     return removeFirst();
	  }
	  
	  public String toString()
	  {
	  	String s = new String();
		for(int i=0; i< this.size(); i++)
		{
			Line temp = (Line) this.dequeue();
			s+=(temp).toString()+"\n";
			this.enqueue(temp);		
		}
		return s;
	  }
	  
	  public String toStringHTML()
	  {
	  	String s = new String();
		for(int i=0; i< this.size(); i++)
		{
			Line temp = (Line) this.dequeue();
			s+=(temp).toString()+"<br> \n";
			this.enqueue(temp);		
		}
		return s;
	  }


}
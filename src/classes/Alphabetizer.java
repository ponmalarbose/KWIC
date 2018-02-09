
//This class is used for sorting Circular shifted Lines
//These lines are stored in a linked queue

import java.beans.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.*;
public class Alphabetizer extends Canvas implements Serializable,URLListener,Runnable

{
	
    private URLEventObject lastURLEvent;
    private Vector urlListeners = new Vector();
    private boolean open = true;
    Thread thread;
	LinkedQueue newlineQueue;   //Queue to store alphabetized lines
  
	
	//constructor
	public Alphabetizer()
	{	
		newlineQueue = new LinkedQueue();
		thread = new Thread(this);
        thread.start();
	}
	
	public void handleURL(URLEventObject event) 
    {
	
        lastURLEvent = event;
             
        CShiftLines cShiftLineObj = (CShiftLines) event.getSource();
        Line [] cShiftLines = cShiftLineObj.getCSLines();
        processCSLines(cShiftLines);
        
        cShiftLineObj.setOpen(true);
                
        System.out.println("url Event handled in alphabetizer,\n"
                           +"----new alphabetized line-----\n"
                            +this.toString()); 
                        
   }
    
    public void processCSLines(Line [] csLines)
    {
    	LinkedQueue addedLines=new LinkedQueue() ;
    	Line [] unsortedLines = csLines;
		for (int i=0; i< unsortedLines.length; i++)
		{     
		    addedLines.enqueue(unsortedLines[i]);
		}
		
		mergeSort(addedLines);
		     
    	if(newlineQueue.size()==0)
    	{
    		newlineQueue=addedLines;
	        
		}
		else
		{
			LinkedQueue resultQueue=new LinkedQueue();
			merge(newlineQueue, addedLines,  resultQueue);
			
			newlineQueue=resultQueue;
			resultQueue = null;
		}
    	
    }
    
    public void run() 
  {
  	while(true) 
  	{
          try 
          { 
            thread.sleep(1000);
          } 
          catch (Exception e) 
          {}
          
   }
 }
  
	
	//return linked Queue
	public LinkedQueue getSortedLines()
	{   return newlineQueue;   }
	
	//return sorted array, used as base for Binary Search
	public Line [] getSortedLinesArrary()
	{ 
	   Line [] sortedLineArr = new Line[newlineQueue.size()] ;
	   for (int i = 0; i< newlineQueue.size() ; i++)
	   {  
	   	sortedLineArr[i]=(Line) newlineQueue.front();
	   	newlineQueue.enqueue(newlineQueue.dequeue());
	   	
	   	}
	    return sortedLineArr; 
    }
	
	//return Alphabetized lines in String in print format
	public String toString()
	{
		
		return newlineQueue.toString();
	}
	
	public String toStringHTML()
	{
		
		return newlineQueue.toStringHTML();
	}
	
	//merge sort  function
	public  void mergeSort(LinkedQueue Q )
	{
		
		int n = Q.size();
		// a sequence with 0 or 1 element is already sorted
		if (n<2) return;
		// divid
		int i =n;
		LinkedQueue Q1=new LinkedQueue();
		do{
			Q1.enqueue(Q.dequeue());
			i--;
		  }
		while(i>n/2);
		LinkedQueue Q2=new LinkedQueue();
		do{
			Q2.enqueue(Q.dequeue());
			i--;
		   }
		while(i>0);
		
		//recursion
		mergeSort(Q1);
		mergeSort(Q2);
		//conquer
		merge(Q1,Q2,Q);
	 }
	 
	 //merge two queue in a new queue
	 public void merge(LinkedQueue Q1, LinkedQueue Q2, LinkedQueue Q)
	 {
	 	while(!Q1.isEmpty()&&!Q2.isEmpty())
	 	{
	 		String s1=((Line)Q1.front()).toString();
	 		int startPosition1 = s1.lastIndexOf(" ");
		     s1= s1.substring(0, startPosition1-1);
		    
		    String s2=((Line)Q2.front()).toString();
	 		int startPosition2 = s2.lastIndexOf(" ");
		    s2 = s2.substring(0, startPosition2-1);
		
	 	   if(s1.compareTo(s2)>=0)
	 	        Q.enqueue(Q1.dequeue());
	 	   
	 	   else
	 	        Q.enqueue(Q2.dequeue());
	 	 }       
	 	while(!Q1.isEmpty())
	 	    Q.enqueue(Q1.dequeue());
	 	while(!Q2.isEmpty())
	 	    Q.enqueue(Q2.dequeue());
	 	        
	 }
  
  //Add a url listener object to the list.
  public synchronized void addURLListener( URLListener listener)
  {
    urlListeners.addElement(listener);
  }//end addPropertyChangeListener
  //
  
  //Remove a url listener from the list.
  public synchronized void removeURLListener( URLListener listener)
  {
    
    urlListeners.removeElement(listener);
  }//end removePropertyChangeListener()
	
}
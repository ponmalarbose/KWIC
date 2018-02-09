//CShiftLine object represent a set of lines 
//each line is created by shifting one word.

import java.beans.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.*;
public class CShiftLines extends Canvas implements Serializable,URLListener,Runnable

{
	private Vector urlListeners = new Vector();
    private URLEventObject lastURLEvent;
    private boolean open = true;
    Thread thread;
	
    Line [] newCSLines;     //the new line which will be processed.
  
  //constructor
  public CShiftLines()
  {
  	  Line [] temp = {new Line("thereisnothing")};
      
      newCSLines = temp;
     thread = new Thread(this);
     thread.start();
  }//end constructor
  
  
  public  synchronized  boolean isOpen() 
  {
       return open;
  }
  public  synchronized  void setOpen(boolean x) 
  {
     open = x;
  }
  
  public void handleURL(URLEventObject event) 
  {
	    
        lastURLEvent = event;
         if (isOpen())
          {     
                DetailURL detailURL = (DetailURL) event.getSource();
                setCSLines(detailURL);
                open=false;
          }
         System.out.println("url Event object handled in circular shift line\n"
                            +"----new circular shift line-----\n"
                            +this.toString()); 
        announceURL();                    
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
          if (lastURLEvent != null) 
          {
          	try
            {
              if(!isOpen())
               {
                 System.out.println
                 ("Server is busy, please wait during processing your saving request.");
               }//end if
            }
            catch(Exception e)
            {System.out.println("Inside CSLine run:"+e.toString());}
          }
   }
 }
 
 void announceURL()
  {
  	try{
    
      //notify property listeners of property change
        Vector listenersCopy;
        System.out.println("Inside of circular shifted line ");
        URLEventObject urlevent = new URLEventObject(this);
          synchronized(this) 
            {
                   listenersCopy= (Vector)urlListeners.clone();
             }
        for (int i = 0; i < listenersCopy.size(); i++) 
        {
          URLListener wl = (URLListener) listenersCopy.elementAt(i);
          wl.handleURL(urlevent) ;
        }
   }
   catch(Exception e)
   {
   	System.out.println("Inside DetailURL, processURL:"+e.toString());
   }
  }//end announce the url

  
  
  
  public void setCSLines(DetailURL detailURL)
  {
  	  
  	 
  	  Line originLine=new Line(
  	  	 noNoiseLine( detailURL.getDescription()));
  	  String url = detailURL.getURL();
  	  
	  int	index =originLine.getWordNum();
	    
		
		newCSLines = new Line[index];
		LinkedQueue wordQueue = originLine.getWordQueue();
	    
		//creat lines from CSLines[0] to CSLines[n-1]  
		for (int i=0; i<index;i++)
		{
			String temp= new String();
            
			//creat a circular shift line
			for(int j=0; j<index; j++)
			{
				String word=(String) wordQueue.dequeue();
				temp+=word;
				temp+=" ";
				wordQueue.enqueue(word);
			
			}
			newCSLines[i]=new Line(temp+" "+url);
			
			String head =(String) wordQueue.dequeue();
			wordQueue.enqueue(head);
		
		}
  	
   //processTheNewCShiftLine();
    
                    
                    
  }
  
  public String noNoiseLine(String s) 
	{
	
	   String	aLine=new String();
		StringTokenizer stk = new StringTokenizer (s," ");
		
		int iTime = stk.countTokens();
		for(int i=0;i<iTime;i++)
		{
			String adding = new String();
			String checking =stk.nextToken();
			
			if(checking.charAt(0)<65||checking.charAt(0)>90)
			checking = checking.substring(1);
			if(checking.charAt(checking.length()-1)<65
			       ||checking.charAt(checking.length()-1)>90)
			checking = checking.substring(0, checking.length()-1);       
			adding = checking;
			if(!(adding.equals("AND")||adding.equals("I")||adding.equals("OR")
			||adding.equals("A")||adding.equals("OF")||adding.equals("HE")
			||adding.equals("HIS")||adding.equals("HER")||adding.equals("IS")
			||adding.equals("AM")||adding.equals("ARE")||adding.equals("THEY")
			||adding.equals("IT")||adding.equals("NO")||adding.equals("NOT")
			||adding.equals("CAN")||adding.equals("MUST")||adding.equals("DO")
			||adding.equals("HOW")||adding.equals("WHAT")||adding.equals("THIS")
			||adding.equals("THAT")||adding.equals("WHEN")||adding.equals("WHERE")
			||adding.equals("THESE")||adding.equals("THOSE")||adding.equals("WHO")
			||adding.equals("WHAT")||adding.equals("AN")||adding.equals("WAS")
			||adding.equals("BE")||adding.equals("FOR")	))
				{
			
		            aLine+=adding+" ";}	
           }
        return aLine;
        				
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
  //	
  
  
  
	// print out n lines of circular shifted lines
	public String toString()
	{
		String s= new String();
		for(int i=0;i<newCSLines.length; i++)
		{
			s+= newCSLines[i].toString();
			s+="\n";
		}
		return s;
	}
	
	public String toStringHTML()
	{
		String s= new String();
		for(int i=0;i<newCSLines.length; i++)
		{
			s+= newCSLines[i].toString();
			s+="<br>\n";
		}
		return s;
	}
	
	public Line[] getCSLines()
	{   return newCSLines;  
	 }
	
    
	
}
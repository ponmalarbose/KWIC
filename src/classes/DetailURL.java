import java.beans.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.*;

public class DetailURL 
{
	private String description;
	private String newurl;
	private Vector urlListeners = new Vector();
	
  
	public DetailURL(String in_url, String information)
	{
		
        newurl = in_url;//proposed new url
		description=information;
		announceURL();	
						
	}
	
	public DetailURL()
	{
		
        newurl = "";//proposed new url
		description="";
			
						
	}
	
  public synchronized void setURL(String in_url,String inform){
    
    newurl = in_url;//proposed new url
    description= inform;
    
    //go process the env which is related with this url
    announceURL();
    
  }//end setURL()
  
  
  void announceURL()
  {
  	try{
    
      //notify property listeners of property change
        Vector listenersCopy;
        
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
  }//end process the url  
  
	
	public String getURL()
	{	return newurl;}
	
	public String getDescription()
	{   return description; }
	
  
  
  
  
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
  
  public String toString()
  {
   return newurl+" "+description;	
  }
	
}



//This class is used for sorting a list of Lines
//These lines are stored in a linked queue

import java.io.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.beans.*;

public class KeyWordSearch 
{
   private Line [] sortedLineArr;
   
   private LinkedQueue findLineQue;
   
   
   //constructor
    public KeyWordSearch()
    {
    	Line [] temp = {new Line()};
    	sortedLineArr= temp;
    	
    	findLineQue = new LinkedQueue();
    }
    
	public boolean doSearch  ( String multipleKey, Alphabetizer alphabetizer)
	{
		findLineQue = new LinkedQueue();
		sortedLineArr = alphabetizer.getSortedLinesArrary();
		StringTokenizer stk = new StringTokenizer (multipleKey," ");
		
		int jTime = stk.countTokens();
		for(int j=0;j<jTime;j++)
		{
		  String key =stk.nextToken();
		  //System.out.println("KWS----11--"+"No."+j+" key  "+key);
		  //System.out.println(sortedLineArr.length);
		  
            for(int i = 0; i<sortedLineArr.length; i++)
            {   
                //System.out.println("KWS---22--"+"search in line No."+i+" "+sortedLineArr[i].toString());
            	if( isMatch(sortedLineArr[i], key))
         	         findLineQue.enqueue( sortedLineArr[i]);
            }
          	
        }
         
         if(findLineQue.size()!=0) 
         return true;
         else 
         return false;	
	}
	
	public boolean isMatch(Line oneLine, String key)
	{
		if( (oneLine.toString()).indexOf(key)==0 )
		{ 
		  return true;
         }
         else return false;
		
	}
	
	public LinkedQueue getNoRepeatURLName()
	{ 
	   Line [] findLineArr = new Line[findLineQue.size()] ;
	   LinkedQueue noRepeatURLName= new LinkedQueue();
	   for (int i = 0; i< findLineQue.size() ; i++)
	   {  
	    findLineArr[i]= (Line) findLineQue.front();
	   	findLineQue.enqueue(findLineQue.dequeue());
	   	
	   	}
	   	int jTime = findLineArr.length ;
	   	int flag=0;//0 represent add "no repeat url",1:not add
		for(int j=0;j<jTime;)
		{
		  for(int i=j+1;i<jTime;)
		     { if( !findLineArr[j].equals(findLineArr[i]))
		          {i++; }
		       else 
		          { i=jTime; flag=1;}
		       }
		  if(flag==0)
		   {
		  	//System.out.println("URL111---get a no repeat url: "+findLineArr[j]);
		  	String lineStr =findLineArr[j].toString();
		  	String urlname=lineStr.substring(lineStr.lastIndexOf(" ")+1);
		  	noRepeatURLName. enqueue( new Line(urlname));
		  	}
		   j++;
		   flag=0;
	   }
	   	
	    return noRepeatURLName; 
    }
    
    
    
	
}

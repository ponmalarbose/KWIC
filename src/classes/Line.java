//Line object represent an ordered set of words
//It is tokened by ' ' sign from the text input by user

import java.io.*;
import java.lang.*;
import java.util.*;

public class Line implements Serializable
{
	private String oneLine;
	private LinkedQueue wordQueue;
	private int wordNum;
	
	public Line(String s)
	{
		oneLine=s;
		StringTokenizer stk = new StringTokenizer (oneLine," ");
		wordNum=stk.countTokens();
		wordQueue = new LinkedQueue();
		for(int i=0;i<wordNum;i++)
		{
           wordQueue.enqueue(stk.nextToken());
        }					
	}
	
	public Line()
	{
		oneLine=new String();
		wordNum=0;
		wordQueue = new LinkedQueue();
							
	}
	
	public LinkedQueue getWordQueue()
	{	return wordQueue;}
	
	public int getWordNum()
	{   return wordNum; }
	
	public String toString()
	{   return oneLine;}
	
	
	
}



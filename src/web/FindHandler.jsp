
<%@ page import="CShiftLines,Alphabetizer,DetailURL,KeyWordSearch, LinkedQueue, Line"%>
<%@ page import="javax.servlet.http.*,java.awt.*, java.awt.event.*, java.beans.*, java.io.Serializable"%>

<%!	String key_name;
    KeyWordSearch search;
    Alphabetizer alphabetizer;
%>
	
<% key_name = request.getParameter("keyword").toUpperCase();%>

<%
  search =(KeyWordSearch) application.getAttribute("search");
  if (search==null) 
  { search = new KeyWordSearch();}
  alphabetizer =(Alphabetizer) application.getAttribute("alphabetizer");
  if (alphabetizer==null) 
  { 
    alphabetizer = new Alphabetizer();
    application.setAttribute("alphabetizer",alphabetizer);
  }
  LinkedQueue urlQueue = (LinkedQueue) application.getAttribute("urlqueue");
   if (urlQueue==null) {
   	urlQueue = new LinkedQueue();
   	application.setAttribute("urlqueue", urlQueue);
   	}
%>


<html>
<body bgcolor="#FDF5E6" link="#999999" 
   vlink="#999999" alink="#999999">
<center>
<font size=+2>
<b>Mini Web Search Engine-------Search</B>
</font>
</center>

<% 
  
if(key_name==null||key_name.length()==0)
{%> 
      invalid input
 <%}
else{
	 		 if( !search.doSearch(key_name, alphabetizer) )
      		 {
      		   %>
      		   Your search has no resoult!
      		   <%}
      		 else
      		     {
      		     
      		     LinkedQueue urlSearchQ = search.getNoRepeatURLName();
      		     
                 int jCount = urlSearchQ.size();
                 
                 for(int j=0;j<jCount;j++)
		          {  String oneURLName =((Line)urlSearchQ.dequeue()).toString();
		                    
		    int dbsize = urlQueue.size();     
		   for(int i=0; i<dbsize; i++)
		   { 
		        
		    if(((DetailURL)urlQueue.front()).getURL().equals(oneURLName))
		    {
		    	  
		    	
		        %>
	<%=((j+1)+"--URL:  "+oneURLName+"<br>\n    --DESCRIPTION:  "
		              +((DetailURL)urlQueue.front()).getDescription()+"<BR>\n")%>
		        <%
		     
		     }  
		    urlQueue.enqueue((DetailURL) urlQueue.dequeue());
		    
		  }
            
               }//end of for loop for each found url name
   
                }//end of else: key word found		      
      		      
      		     
  }//end of else: key word not null and not zero length
  
%>
     

</body>
</html>

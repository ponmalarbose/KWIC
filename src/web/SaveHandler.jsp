
<%@ page import="CShiftLines,Alphabetizer,DetailURL,KeyWordSearch,LinkedQueue,Line"%>
<%@ page import="javax.servlet.http.*,java.awt.*, java.awt.event.*, java.beans.*, java.io.Serializable"%>


<%!
     private DetailURL url ;
     private CShiftLines circularShift;
     private Alphabetizer alphabetizer;
     private KeyWordSearch search;
     
   String url_name = null;
   String url_descript =null;
   
   

   public void jspDestroy() 
   {    
      
      circularShift = null;
      url = null;
      alphabetizer = null;
      search =null;
   }
%>
<%
      
      circularShift =(CShiftLines) application.getAttribute("circularShift");
      if (circularShift==null) { circularShift = new CShiftLines();
                                 application.setAttribute("circularShift",circularShift);
                                 }
                                 
      alphabetizer =(Alphabetizer) application.getAttribute("alphabetizer");
      if (alphabetizer==null) { alphabetizer = new Alphabetizer();
                                 application.setAttribute("alphabetizer",alphabetizer);
                                 }
                                 
      search =(KeyWordSearch) application.getAttribute("search");
      if (search==null) { search = new KeyWordSearch();
                                 application.setAttribute("search",search);
                        }
      url = (DetailURL) application.getAttribute("url");
      if (url==null) 
      {              url = new DetailURL();
                       application.setAttribute("url",url);
                       url.addURLListener(circularShift);
                      circularShift.addURLListener(alphabetizer );
        }
%>

<html>
<body bgcolor="#FDF5E6" link="#999999" 
   vlink="#999999" alink="#999999">
<center>
<font size=+2>
<b>Mini Web Search Engine-------Saving</B>
</font>
</center>

<% LinkedQueue urlQueue = (LinkedQueue) application.getAttribute("urlqueue");
   if (urlQueue==null) {
   	urlQueue = new LinkedQueue();
   	application.setAttribute("urlqueue", urlQueue);
   	}
   
try{
   
   url_name = request.getParameter("url_name").toUpperCase();
   url_descript = request.getParameter("url_descript").toUpperCase();
      
   if( url_name!=null&&url_descript!=null)
   {
    try{
   	   url.setURL(url_name, url_descript);
   	   
       urlQueue.enqueue(new DetailURL(url_name, url_descript));                                   
%>
<p>*****Saved*****<br> URL Name:<%=url_name%><br> URL Description: <%=url_descript%>
</p>

<%  
      	}
      catch (NullPointerException ex) {
            System.out.println("Null pointer exception "+ ex.getMessage());
      } 
      catch (Exception ex) {
           System.err.println("Caught an exception." );
           ex.printStackTrace();
       }
       
   } else{%>
    Invalid input.
    <%} //end else
   
   
      }
      catch (NullPointerException ex) {
            System.out.println("Null pointer exception at end 666 "+ ex.getMessage());
      } 
  %>



</body>
</html>


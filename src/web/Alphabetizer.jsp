
<%@ page import="javax.ejb.*, java.math.*, javax.naming.*,java.util.*, javax.rmi.PortableRemoteObject, java.rmi.RemoteException" %>
<%@ page import="Alphabetizer"%>
<%@ page import="javax.servlet.http.*,java.awt.*, java.awt.event.*, java.beans.*, java.io.Serializable"%>


<%!
     
     private Alphabetizer alphabetizer;
     
   public void jspDestroy() 
   {  
      alphabetizer = null;
      
   }
%>
<%
      alphabetizer =(Alphabetizer) application.getAttribute("alphabetizer");
      if (alphabetizer==null) { alphabetizer = new Alphabetizer();
                                 application.setAttribute("alphabetizer",alphabetizer);
                                 }
      
%>

<html>
<body bgcolor="#FDF5E6" link="#999999" 
   vlink="#999999" alink="#999999">
<center>
<font size=+2>
<b>Mini Web Search Engine-------Current Alphabetizer </B>
</font>
</center>
<%= alphabetizer.toStringHTML()%>


</body>
</html>


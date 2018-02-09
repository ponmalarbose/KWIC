
<%@ page import="javax.ejb.*, javax.naming.*,java.util.*, javax.rmi.PortableRemoteObject, java.rmi.RemoteException" %>
<%@ page import="CShiftLines"%>
<%@ page import="javax.servlet.http.*,java.awt.*, java.awt.event.*, java.beans.*, java.io.Serializable"%>


<%!
     
     private CShiftLines circularShift;
     
   public void jspDestroy() 
   {  
     circularShift= null;
      
   }
%>
<%
      circularShift =(CShiftLines) application.getAttribute("circularShift");
      if (circularShift==null) 
      { circularShift = new CShiftLines();
        application.setAttribute("circularShift",circularShift);
      }
%>
      

<html>
<body bgcolor="#FDF5E6" link="#999999" 
   vlink="#999999" alink="#999999">
<center>
<font size=+2>
<b>Mini Web Search Engine-------Current Circular Shifted Line </B>
</font>
</center>
<%= circularShift.toStringHTML()%>


</body>
</html>


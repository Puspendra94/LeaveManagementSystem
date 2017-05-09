<%-- 
    Document   : Inbox
    Created on : Feb 1, 2015, 8:53:53 PM
    Author     : Sony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="bca.leave.model.UserLoginBean" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inbox</title>
        <link rel="stylesheet" href="/LeaveManagement/CSS/ApplyCss.css" type="text/css" media="screen">
        <link rel="stylesheet" href="CSS/paging.css" type="text/css" media="screen">
        <link rel="stylesheet" href="CSS/Inbox.css" type="text/css" media="screen">
        <link rel="stylesheet" href="CSS/StyleCSS.css" type="text/css" media="screen">
         <script src="/LeaveManagement/js/jquery-1.9.1.js"></script>
         <script src="/LeaveManagement/js/messagenotif.js"></script>
         <script src="/LeaveManagement/js/InboxButtonPanelJquery.js"></script>
        <script src="/LeaveManagement/js/jquery-ui_1.10.3.js"></script>
        <script src="/LeaveManagement/js/NotifJquery.js"></script>
        <script src="/LeaveManagement/js/delete.js"></script>
        
       </head>
    <body>
        
        <jsp:include page="UserLoggedIn.jsp"/>
        
        <%
             HttpSession ses=request.getSession(false);
             //UserLoginBean b=(UserLoginBean)ses.getAttribute("b"); 
             String uname=(String)ses.getAttribute("name");
             if(ses.equals("")||uname.equals(""))
             {
                 response.sendRedirect("index.html");
             }
             else
             {
        %>
        
        <c:if test="${sessionScope.emsg eq true}">
        <center>
            <div id="emsg">
                <%= ses.getAttribute("msg")%>
            </div>
        </center>
        </c:if>
        <c:if test="${sessionScope.cmsg eq true}">
        <center>
            <div id="cmsg">
                <%= ses.getAttribute("msg")%>
            </div>
        </center>
        </c:if>
        <form action="InboxProcess" method="post" name="record">
            
            <div id="panel">
                <input name="action" type="submit" value="Approve" id='a'>
                <input name="action" type="submit" value="Reject" id='r'>
                <input name="action" type="submit" value="Forward" id='f'>
                            <input name="action" type="submit" value="Delete">
                            <font color="red"><p>Please check only one checkbox<br>Its a Bug</p></font>
            </div>
            <table width="100%" id="tableData">
                <tr><th></th><th bgcolor="orange">From</th><th bgcolor="orange">To</th><th bgcolor="orange">Leave Type</th><th bgcolor="orange">Duration</th><th bgcolor="orange">Message</th><th bgcolor="orange">Status</th></tr>
            
            
                <c:forEach var="b" items="${b}">
                <tr>
                
                    <td><input type="checkbox" name="cb" class="c" value="${b.id}"/></td>
                    <%
                    try{
                        %>
                          <input type="hidden" value="<%= uname%>" name="uname" id="${b.id}t"/>
                    <td>${b.from}<input type="hidden" name="from${b.id}" value="${b.from}"/><br>
                        Forwarded by:<i><c:out value="${b.fwdby}"></c:out></i><input type="hidden" value="${b.fwdby}" name="fwdby${b.id}" id="${b.id}f"/></td>
                    <td><c:out value="${b.to}"></c:out><input type="hidden" value="${b.to}" name="to${b.id}"/></td>
                    <td><c:out value="${b.ltype}"></c:out><input type="hidden" value="${b.ltype}" name="ltype${b.id}"/></td>
                    <td><c:out value="${b.fdate}"></c:out><input type="hidden" value="${b.fdate}" name="fod${b.id}"> - ${b.tdate}<input type="hidden" value="${b.tdate}" name="tod${b.id}"/><br>
                        <c:out value="${b.day}"></c:out> Days<input type="hidden" value="${b.day}" name="day${b.id}"</td>
                    <td><span id="msgg">${b.msg}</span></td>
                    
                             <td><c:out value="${b.status}"></c:out><input type="hidden" value="${b.status}" id="${b.id}" name="status"/></td>
                        <input type="hidden" name="cl${b.id}" value="${b.cl}"/>
                        <input type="hidden" name="al${b.id}" value="${b.al}"/>
                        <input type="hidden" name="sl${b.id}" value="${b.sl}"/>
                        <input type="hidden" name="dl${b.id}" value="${b.dl}"/>
                        <input type="hidden" name="lwp${b.id}" value="${b.lwp}"/>
                        <input type="hidden" name="spl${b.id}" value="${b.spl}"/>
                        <input type="hidden" name="ao${b.id}" value="${b.ao}"/>
                        
                    <%
                    }
                    catch(Exception e)
                    {
                        System.err.println("The problem in Inbox.jsp is "+e.getCause()+"\n"+e.getMessage());
                    }
                    %>
                </tr>
                
            </c:forEach>
            </table>
            
        </form>
    
    <head>
        <script type="text/javascript" src="/LeaveManagement/js/jquery_1.11.2.min.js"></script> 
<script src="/LeaveManagement/js/jquery-ui_1.11.2.min.js"></script>
<script type="text/javascript" src="/LeaveManagement/js/paging.js"></script> 
<script type="text/javascript">
            $(document).ready(function() {
                $('#tableData').paging({limit:10});
            });
        </script>
  
    </head>
        <jsp:include page="footer.jsp"/>
        <%
             }
             ses.setAttribute("emsg", false);
             ses.setAttribute("cmsg", false);
        %>
    
    </body>
</html>

<%-- 
    Document   : AddGroup
    Created on : Mar 5, 2015, 11:45:36 AM
    Author     : Sony
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Group</title>
        <link rel="stylesheet" href="/LeaveManagement/CSS/ApplyCss.css" type="text/css" media="screen">
        <script src="/LeaveManagement/js/jquery-1.9.1.js"></script>
        <script src="/LeaveManagement/js/jquery-ui_1.10.3.js"></script>
        <script src="/LeaveManagement/js/messagenotif.js"></script>
        
       
    </head>
    <body>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0);
            if(session.getAttribute("uname").equals(""))
            {
                response.sendRedirect("index.jsp");
            }
            else
            {
        %>
        
    
    <center>
        <jsp:include page="link.html"/>
            <c:if test="${sessionScope.emsg eq true}">
        <center>
            <div id="emsg">
                <%= session.getAttribute("msg")%>
            </div>
        </center>
    </c:if>
        <c:if test="${sessionScope.cmsg eq true}">
        <center>
            <div id="cmsg">
                <%= session.getAttribute("msg")%>
            </div>
        </center>
        </c:if>
    
        <br><br><br><br><br><br>
        <form action="AddGroupServlet" method="post">
            <table cellspacing="12">
                <tr><td>Group Name</td><td><input type="text" name="gname" required/></td></tr>
                <tr><td>Casual Leave</td><td><input type="number" min="0" name="cl" required/></td></tr>
                <tr><td>Annual Leave</td><td><input type="number" min="0" name="al" required/></td></tr>
                <tr><td>Sick/Medical Leave</td><td><input type="number" min="0" name="sl" required/></td></tr>
                <tr><td></td><td><input type="submit" value="Add Group"/><input type="reset" value="clear"/></td></tr>
            </table>
        </form>
    </center>
        <%
            }
            session.setAttribute("cmsg", false);
            session.setAttribute("emsg", false);
         %>
    </body>
</html>

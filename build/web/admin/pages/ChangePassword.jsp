<%-- 
    Document   : ChangePassword
    Created on : Apr 28, 2015, 11:27:46 PM
    Author     : Sony
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
        <script src="/LeaveManagement/js/jquery-1.9.1.js"></script>
        <script src="/LeaveManagement/js/jquery-ui_1.10.3.js"></script>
        <script src="/LeaveManagement/js/messagenotif.js"></script>
        <link rel="stylesheet" href="/LeaveManagement/CSS/ApplyCss.css" type="text/css" media="screen">
        
    </head>
    <body>
        <%
            //HttpSession session = request.getSession(false);
        %>
        <jsp:include page="link.html"/>
        <br><br><br>
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
    <center>
        
        <form action="ChangeAdminPasswordServlet" method="post">
            <table cellspacing="8">
                <tr><td>Old Password</td><td><input type="password" name="p1"></td></tr>
                <tr><td>New Password</td><td><input type="password" name="p2"></td></tr>
                <tr><td>Confirm Password</td><td><input type="password" name="p3"></td></tr>
                <tr><td></td><td></td></tr>
                <tr><td colspan="2"><input type="submit" value="Change Password">
                        <input type="reset" value="clear">
                        <input type="button" value="Cancel"></td></tr>
            </table>
        </form>
    </center>
    <%
        session.setAttribute("emsg", false);
        session.setAttribute("cmsg", false);
        
    %>
    </body>
</html>

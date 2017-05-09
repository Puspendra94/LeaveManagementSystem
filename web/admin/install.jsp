<%-- 
    Document   : install
    Created on : May 7, 2015, 2:13:08 PM
    Author     : Sony
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Admin</title>
        <link rel="stylesheet" href="css/admin.css" type="text/css" media="screen">
    </head>
    <body>
        <c:if test="${requestScope.emsg eq true}">
        <center>
            <div id="emsg">
                <%= request.getAttribute("msg")%>
            </div>
        </center>
        </c:if>
    
    <c:if test="${requestScope.cmsg eq true}">
        <center>
            <div id="cmsg">
                <%= request.getAttribute("msg")%>
            </div>
        </center>
    </c:if>
        <br><br><br>
        <div id="form"> 
            <h2 class="design">Registration Form</h2>
               <form action="AdminRegisterServlet" method="post" role="form">
                     <table cellspacing="8" align="center">
                         <tr><td>User Name:</td><td><input type="text" name="uname" required class="form-control"/></td></tr>
                         <tr><td>Password:</td><td><input type="password" required name="pass"/></td></tr>
                         <tr><td>Confirm Password:</td><td><input type="password" required name="cpass"/></td></tr>
                        <tr><td></td><td><input id="submit" type="submit" value="Register"/>
        
                        </td></tr>
                     </table>
              </form>
        </div>
    </body>
</html>

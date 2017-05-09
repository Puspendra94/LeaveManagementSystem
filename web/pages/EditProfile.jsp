<%-- 
    Document   : EditProfile
    Created on : Feb 2, 2015, 3:22:16 PM
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
        <title>Edit Profile</title>
        <link rel="stylesheet" href="CSS/ApplyCss.css" type="text/css" media="screen">
        <script src="/LeaveManagement/js/jquery-1.9.1.js"></script>
        <script src="/LeaveManagement/js/jquery-ui_1.10.3.js"></script>
        <script src="/LeaveManagement/js/messagenotif.js"></script>
        <style>
            #fo{
                margin-top: 20%;
            }
        </style>
        
    </head>
    <body>
        <%
            HttpSession ses = request.getSession(false);
           
        %>
         
        <jsp:include page="UserLoggedIn.jsp"/>
        
        
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
        <div id="form">
            <h2>Change Password</h2>
        <form action="ChangePasswordServlet" method="post">
            <table cellspacing="8">
                <tr>
                    <td>Older Password</td><td><input type="password" name="opass"></td>
                </tr>
                <tr>
                    <td>New Password</td><td><input type="password" name="npass"></td>
                </tr>
                <tr>
                    <td>Confirm New Password</td><td><input type="password" name="cnpass"></td>
                </tr>
                <tr><td></td><td colspan="2"><input type="submit" value="Change Password"/></td></tr>
            </table>
        </form>
        </div>
        <div id="fo">
            <jsp:include page="footer.jsp"/>
        </div>
            <%
            
                ses.setAttribute("emsg", false);
                ses.setAttribute("cmsg", false); 
            %>
    </body>
</html>

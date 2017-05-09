<%-- 
    Document   : 440
    Created on : May 2, 2015, 1:35:32 PM
    Author     : Sony
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="err/css/sss.css" type="text/css" media="screen">
        <title>440 Log In Timeout</title>
        <script>
            function back()
            {
                window.history.back();
            }
        </script>
    </head>
    <body>
        
        <br><br><br>
        <div id="panel">
            <center><h1><b>SERVER ERROR</b></h1></center>
            <h2>440 -- LogIn Timeout</h2>
            <div id="con" align="left">
                <h4>Sorry!!!<br>Log-In Timeout</h4><br><br><br>
            
                <button onclick="back()">Go Back</button><br><br><br><br><br><br><br><br><br><br><br><br>
                <c:choose>

                <c:when test="${ sessionScope.session ne null }">
                    <a href="/LeaveManagement/admin/pages/AdminLoggedIn.jsp"> Go Back to Home </a>
                </c:when>

                <c:when test="${ sessionScope.ses ne null }">
                    <a href="/LeaveManagement/pages/UserLoggedIn.jsp"> Go Back to Home </a>
                </c:when>

                <c:otherwise>
                    <a href="/LeaveManagement/"> Go Back to Home page </a>
                </c:otherwise>

            </c:choose>
            </div>
            
        </div>
    
    </body>
</html>

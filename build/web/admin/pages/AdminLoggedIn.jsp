<%-- 
    Document   : AdminLoggedIn
    Created on : Jan 29, 2015, 11:10:25 AM
    Author     : Sony
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <script src="/LeaveManagement/js/jquery-1.9.1.js"></script>
        <script src="/LeaveManagement/js/jquery-ui_1.10.3.js"></script>
        <script src="/LeaveManagement/js/messagenotif.js"></script>
         <link rel="stylesheet" href="/LeaveManagement/CSS/ApplyCss.css" type="text/css" media="screen">
       
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
    <h1>Hello Admin</h1>
    <%
            }
            session.setAttribute("emsg", false);
            session.setAttribute("cmsg", false);
    %>
    </body>
</html>

<%-- 
    Document   : UserLoggedIn
    Created on : Jan 31, 2015, 8:53:08 PM
    Author     : Sony
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Leave Management System</title>
         
        <link rel="stylesheet" href="/LeaveManagement/CSS/user.css" type="text/css" media="screen">
        <link rel="stylesheet" href="/LeaveManagement/CSS/Home.css" type="text/css" media="screen">
        <script src="/LeaveManagement/js/jquery-1.9.1.js"></script>
        <script src="/LeaveManagement/js/jquery-ui_1.10.3.js"></script>
        <script src="/LeaveManagement/js/HomeJquery.js"></script>
        <script src="/LeaveManagement/js/NotifAjax.js"></script>
         </head>
    <body>
        <%
            HttpSession ses = request.getSession(false);
            //int a = ses.getAttribute("a");
            
            if(ses.equals("")||ses.getAttribute("uname").equals(""))
            {
                response.sendRedirect("index.html");
            }
            else
            {
        %>
        
        <div id="nav">
             <h3>
                 <ul>
                     <%--<li><a href="UserLoggedIn.jsp">Home</a></li>--%>
                     <li><a href="InboxServlet" class="link">Inbox</a></li>
                     <li><a href="ApplyServlet" class="link">Apply Leave</a></li>
                     <li><a href="SentServlet" class="link">Applied Leave</a></li>
                     <li><a href="ShowTrashServlet" class="link">Trash</a></li>
                     <li><a href="ProfleServlet" class="link">Change Password</a></li>
                
                     <li><a href="NotifServlet" id="n" class="link">Notification
                    <input type="hidden" value="<%= ses.getAttribute("a")%>" id="not"/>
                         </a><c:if test="${sessionScope.ac ne true && sessionScope.a ge 1}">
                             <span id="notification_count"><%= ses.getAttribute("a")%></span>
                         </c:if>
                             <span id="noti"></span>
                     </li>
                     <li>
                         <a href="UserLogOutServlet" id="out">LogOut</a>
                     </li>
                 </ul>
                
             </h3>
            
        </div> 
        <hr>
        <%
            }
        %>
    </body>
</html>

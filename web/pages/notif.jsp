<%-- 
    Document   : notif
    Created on : Feb 24, 2015, 11:31:20 PM
    Author     : Sony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notification</title>
            <link rel="stylesheet" href="/LeaveManagement/CSS/jquery-ui_start.css">
       <link rel="stylesheet" href="CSS/Notif.css" type="text/css" media="screen">
       <link rel="stylesheet" href="CSS/paging.css" type="text/css" media="screen">
       <link rel="stylesheet" href="CSS/StyleCSS.css" type="text/css" media="screen">
        <script src="/LeaveManagement/js/jquery-1.10.2.js"></script>
        <script src="/LeaveManagement/js/jquery-ui_1.11.2.js"></script>
        <script src="/LeaveManagement/js/NotifJquery.js"></script>
        <script src="/LeaveManagement/js/delete.js"></script>
        
    </head>
    <body>
        <%
            HttpSession ses = request.getSession(false);
            if(ses.equals("")||ses.getAttribute("uname").equals(""))
            {
                response.sendRedirect("index.html");
            }
            else
            {
        %>
        <jsp:include page="UserLoggedIn.jsp"/>
        
        <form action="DeleteNotifServlet" method="post" name="record">
            <div id="panel">
                <input type="submit" value="Delete" id="d"/>
            </div>
            <table cellspacing="12" width="100%" id="tableData" >
                
                <tr><th bgcolor="orange"></th><th bgcolor="orange">Notification</th></tr>
           
           
                <c:forEach items="${bb}" var="bb">
                    
                        <tr>
                            <td width="120px" align="center"> <input type="checkbox" value="${bb.id}" class="cb" name="c"/></td>
                            
                            <td><c:out value="${bb.msg}"></c:out><br>
                                On Date :<c:out value="${bb.ondate}"></c:out> From Date :<c:out value="${bb.fod}"></c:out> To Date :<c:out value="${bb.tod}"></c:out><br>
                        <a href="/LeaveManagement/InboxServlet">${bb.button}</a></td>
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

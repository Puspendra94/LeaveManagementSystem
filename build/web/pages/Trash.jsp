<%-- 
    Document   : Trash
    Created on : Feb 3, 2015, 11:21:59 AM
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
        <title>Trash</title>
         <link rel="stylesheet" href="CSS/ApplyCss.css" type="text/css" media="screen">
        <script src="/LeaveManagement/js/jquery-1.9.1.js"></script>
        <script src="/LeaveManagement/js/jquery-ui_1.10.3.js"></script>
        <script src="/LeaveManagement/js/TrashJquery.js"></script>
        <link rel="stylesheet" href="CSS/paging.css" type="text/css" media="screen">
        <link rel="stylesheet" href="CSS/SentMail.css" type="text/css" media="screen">
        <link rel="stylesheet" href="CSS/StyleCSS.css" type="text/css" media="screen">
        <script src="/LeaveManagement/js/NotifJquery.js"></script>
        <script src="/LeaveManagement/js/delete.js"></script>
        
    </head>
    <body>
        
        <jsp:include page="UserLoggedIn.jsp"/>
        
        <%
             HttpSession ses=request.getSession(false);
             //UserLoginBean b=(UserLoginBean)ses.getAttribute("b"); 
             String uname=(String)ses.getAttribute("uname");
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
        <form action="TrashServlet" method="post" name="record">
            <div id="panel">
                            <input name="action" type="submit" value="Restore">
                       
                            <font color="red"><p>Please check only one checkbox<br>Its a Bug</p></font>
            </div>
            <table width="100%" cellspacing="12" id="tableData" >
                <tr><th></th><th bgcolor="orange">From/To</th><th bgcolor="orange">Leave Type</th><th bgcolor="orange">Duration</th><th bgcolor="orange">Message</th><th bgcolor="orange">Status</th></tr>
            
                <c:forEach var="b" items="${b}">
                    <tr>
                        <td><input type="checkbox" name="cb" class="c" value="${b.id}"/></td>
                        <td><c:out value="${b.from}"></c:out> ---&raquo; <c:out value="${b.to}"></c:out><br>
                            Forwarded By :<i><c:out value="${b.fwdby}"></c:out></i></td>
                        <td><c:out value="${b.ltype}"></c:out></td>
                        <td><c:out value="${b.fdate}"></c:out> - <c:out value="${b.tdate}"></c:out><br>
                            <c:out value="${b.day}"></c:out> Days</td>
                        <td><span id="msg">${b.msg}</span></td>
                        <td><c:out value="${b.status}"></c:out></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
        
        
        <script type="text/javascript" src="/LeaveManagement/js/jquery_1.11.2.min.js"></script> 
<script src="/LeaveManagement/js/jquery-ui_1.11.2.min.js"></script>
<script type="text/javascript" src="/LeaveManagement/js/paging.js"></script> 
<script type="text/javascript">
            $(document).ready(function() {
                $('#tableData').paging({limit:10});
            });
        </script>
        
        <jsp:include page="footer.jsp"/>
        <%
             }
                ses.setAttribute("emsg", false);
                ses.setAttribute("cmsg", false); 
            %>
    </body>
</html>

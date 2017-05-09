<%-- 
    Document   : AddUser
    Created on : Jan 30, 2015, 11:07:40 AM
    Author     : Sony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="bca.leave.util.ConnProvider" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add User</title>
        <link rel="stylesheet" href="CSS/ApplyCss.css" type="text/css" media="screen">
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
    <center><br><br><br>
        <form action="UserServlet" method="get">
            <table cellspacing="8">
                <tr><td>User id</td><td><input type="text" name="uid" value="<%= request.getAttribute("id")%>" readonly="readonly"/></td></tr>
                <tr><td>User Name</td><td><input type="text" name="uname"/></td></tr>
                <tr><td>Password</td><td><input type="password" name="pass"/></td></tr>
                <tr><td>Confirm Password</td><td><input type="password" name="cpass"/></td></tr>
                <tr><td>Name</td><td><input type="text" name="name"/></td></tr>
                <tr><td>Department</td><td>
                        <select name="dep">
                            <c:forEach items="${dep}" var="dep">
                                <option>
                                    ${dep.dep}
                                </option>
                            </c:forEach>
                        </select>
                    </td></tr>
                <tr><td>Group</td><td>
                        <select name="role">
                            <c:forEach items="${group}" var="group">
                                <option>
                                    ${group.group}
                                </option>
                            </c:forEach>
                        </select>
                    </td></tr>
               
                <tr><td></td><td colspan="2"><input type="submit" value="submit"/>&nbsp;&nbsp;
                        <input type="reset" value="clear"/></td></tr>
            </table>
        </form>
    </center>
                <%
            }
            session.setAttribute("emsg", false);
            session.setAttribute("cmsg", false);
                %>
    </body>
</html>

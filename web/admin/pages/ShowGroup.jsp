<%-- 
    Document   : ShowGroup
    Created on : Mar 21, 2015, 10:58:20 PM
    Author     : Sony
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Group</title>
        <script src="/LeaveManagement/js/jquery-1.9.1.js"></script>
        <script src="/LeaveManagement/js/jquery-ui_1.10.3.js"></script>
        <script src="/LeaveManagement/js/messagenotif.js"></script>
        <link rel="stylesheet" href="/LeaveManagement/CSS/ApplyCss.css" type="text/css" media="screen">
        
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
    <center>
        <form action="UpdateGroupServlet" method="post">
            <table cellspacing="12">
                <tr><td>Group Name</td><td><c:out value="${b.gname}"></c:out><input type="hidden" name="gname" value="${b.gname}"/></td></tr>
                <tr><td>Casual Leave</td><td><input type="number" min="0" name="cl" value="${b.cl}"/></td></tr>
                <tr><td>Annual Leave</td><td><input type="number" min="0" name="al" value="${b.al}"/></td></tr>
                <tr><td>Sick/Medical Leave</td><td><input type="number" min="0" name="sl" value="${b.sl}"/></td></tr>
            </table>
            <center><input type="submit" value="Update"/></center>
            
        </form>
    </center>
            <%
            }
            session.setAttribute("emsg", false);
            session.setAttribute("cmsg", false);
            %>
    </body>
</html>

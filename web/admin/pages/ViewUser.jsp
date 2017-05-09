<%-- 
    Document   : ViewUser
    Created on : Jan 30, 2015, 8:11:40 PM
    Author     : Sony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Profile</title>
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
    <center>
        <form action="ViewUserServler" method="post">
            <table cellspacing="8">
                <tr><td>User id/User Name/Name</td><td><input type="text" name="view"/></td><td><input type="submit" value="View Profile" name="action"/></td></tr>
            </table>
        </form>
    </center>
    <%
            }
    %>
    </body>
</html>

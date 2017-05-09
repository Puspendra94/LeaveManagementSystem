<%-- 
    Document   : ViewUserProfile
    Created on : Jan 31, 2015, 10:10:20 AM
    Author     : Sony
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Profile</title>
         <link rel="stylesheet" href="/LeaveManagement/CSS/jquery-ui_blitzer.css">
         <link rel="stylesheet" href="/LeaveManagement/CSS/UserProfileCss.css">
        <script src="/LeaveManagement/js/jquery-1.10.2.js"></script>
        <script src="/LeaveManagement/js/jquery-ui_1.11.2.js"></script>
        <script src="/LeaveManagement/js/UserProfileJquery.js"></script>
        <script src="/LeaveManagement/js/messagenotif.js"></script>
        
        <style type="text/css">
            .ui-dialog-titlebar {
                    background: #335599;
                    border: 1px solid #555;
                    color: white;
            }
            
                  
</style>
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
        <jsp:include page="ViewUser.jsp"></jsp:include>
        <hr>
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
        <form action="UserMainServlet" method="get"> 
            <table cellspacing="8">
                <tr><td>User Id</td><td><input type="text" name="id" value="<%= request.getAttribute("id")%>" readonly="readonly"></td></tr>
            <tr><td>User Name</td><td><input type="text" name="uname" value="<%= request.getAttribute("uname")%>"></td></tr>
            
                <tr><td>New Password</td><td><input type="password" name="pass"></td></tr>
                <tr><td>Confirm Password</td><td><input type="password" name="cpass"/></td></tr>
                <tr><td>Name</td><td><input type="text" name="name" value="<%= request.getAttribute("name")%>"></td></tr>
                <tr><td>Department</td><td><input type="text" name="dep" value="<%= request.getAttribute("dep")%>" readonly="readonly"></td></tr>
                
                <tr><td>Role</td><td>
                        <input type="text" name="r1" readonly id="t" value="<%= request.getAttribute("role")%>">
                        <input type="hidden" name="r2" value="<%= request.getAttribute("role")%>">
                        <input type="button" value="change role" id="change">
                    </td></tr>
                
                <tr><td>Casual Leave</td><td><input type="text" name="cleave" value="<%= request.getAttribute("cleave")%>"></td></tr>
                <tr><td>Annual Leave</td><td><input type="text" name="aleave" value="<%= request.getAttribute("aleave")%>"></td></tr>
                <tr><td>Sick/Medical Leave</td><td><input type="text" name="sleave" value="<%= request.getAttribute("sleave")%>"></td></tr>
                
                <input type="hidden" name="cl" value="<%= request.getAttribute("cleave")%>">
                <input type="hidden" name="al" value="<%= request.getAttribute("aleave")%>">
                <input type="hidden" name="sl" value="<%= request.getAttribute("sleave")%>">
                <input type="hidden" name="bc" value="<%= request.getAttribute("bc")%>"/>
                <input type="hidden" name="ba" value="<%= request.getAttribute("ba")%>"/>
                <input type="hidden" name="bs" value="<%= request.getAttribute("bs")%>"/>
                <tr><td>Registration Date</td><td><%= request.getAttribute("date")%></td></tr>
                <tr><td>Registration Time</td><td><%= request.getAttribute("time")%></td></tr>
                <tr><td colspan="2"><input type="hidden" name="ppass" value="<%= request.getAttribute("pass")%>"</td></tr>
                <tr><td></td><td><input type="submit" name="action" value="Update"><a href="ShowUserServlet"><input type="button" value="Cancel"/></a>
                       
        </table>
        </form> 
                <div id="p" title="Select a group">
                    <select size="6" id="v">
                                        <c:forEach items="${group}" var="group">
                                <option>
                                    ${group.group}
                                </option>
                            </c:forEach>
                        </select>
                </div>
   <%
            }
            session.setAttribute("emsg", false);
            session.setAttribute("cmsg", false);
   %>
    </body>
</html>

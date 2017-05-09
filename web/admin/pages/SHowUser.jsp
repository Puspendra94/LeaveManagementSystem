<%-- 
    Document   : SHowUser
    Created on : Feb 15, 2015, 2:03:22 PM
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
        <title>Users List</title>
        
        <script src="/LeaveManagement/js/jquery-1.9.1.js"></script>
        <script src="/LeaveManagement/js/jquery-ui_1.10.3.js"></script>
        <link rel="stylesheet" href="/LeaveManagement/CSS/jquery-ui_blitzer.css" />
        <link rel="stylesheet" href="/LeaveManagement/CSS/ApplyCss.css" type="text/css" media="screen">
        <script src="/LeaveManagement/js/messagenotif.js"></script>
        <%--
        <link rel="stylesheet" href="/LeaveManagement/CSS/Inbox.css" type="text/css" media="screen">
        --%>
        <link rel="stylesheet" href="/LeaveManagement/CSS/paging.css" type="text/css" media="screen">
        <script src="/LeaveManagement/js/TrashJquery.js"></script>
        <script type="text/javascript" src="/LeaveManagement/js/jquery_1.11.2.min.js"></script> 
        <script src="/LeaveManagement/js/jquery-ui_1.11.2.min.js"></script>
        <script type="text/javascript" src="/LeaveManagement/js/paging.js"></script>
        <script type="text/javascript" src="/LeaveManagement/js/delete.js"></script>
        
        <script type="text/javascript">
            $(document).ready(function() {
                $('#tableData').paging({limit:10});
            });
        </script>
        <style>
            #panel{
                display: none;
                margin-left: 2%;
            }
            td{
                text-align: center;
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
        <jsp:include page="ViewUser.jsp"/>
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
    <form action="ViewUserServler" method="post" name="record">
            <div id="panel">
                <input type="submit" value="view & Update" id="v" name="action"/>
                <input type="submit" value="Delete" name="action"/>
            </div>
            <table width="100%" cellspacing="8" id="tableData">
                <tr><th></th><th bgcolor="orange">User Name</th><th bgcolor="orange">Name</th><%--<th bgcolor="orange">Password</th>--%><th bgcolor="orange">Department</th><th bgcolor="orange">Role</th><th bgcolor="orange">Casual Leave</th><th bgcolor="orange">Annual Leave</th><th bgcolor="orange">Sick Leave</th><th bgcolor="orange">Registration Date</th><th bgcolor="orange">Registration Time</th></tr>
           
                <%
                try
                {
                    %>
                     
                <c:forEach items="${b}" var="b">
                    <tr>
                        <td><input type="checkbox" class="c" value="${b.id}" name="view" /></td>
                        <td><c:out value="${b.uname}"></c:out></td>
                        <td><c:out value="${b.name}"></c:out></td>
                        <td><c:out value="${b.dep}"></c:out></td>
                        <td><c:out value="${b.role}"></c:out></td>
                        <td><c:out value="${b.cleave}"></c:out></td>
                        <td><c:out value="${b.aleave}"></c:out></td>
                        <td><c:out value="${b.sleave}"></c:out></td>
                        <td><c:out value="${b.regdate}"></c:out></td>
                        <td><c:out value="${b.regtime}"></c:out></td>
                    
                    </tr>
                    
                    </c:forEach>
                    
                <%
                }
                catch(Exception e)
                {
                    System.out.println("The problem in SHowUser.jsp is "+e.getCause()+"\n"+e.getMessage());
                }
            }
            
            session.setAttribute("emsg", false);
            session.setAttribute("cmsg", false); 
                %>
                
            </table>
        </form>
    </body>
</html>

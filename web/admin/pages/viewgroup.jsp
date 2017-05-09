<%-- 
    Document   : viewgroup
    Created on : Mar 14, 2015, 1:50:15 PM
    Author     : Sony
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Group</title>
        <script src="/LeaveManagement/js/jquery-1.9.1.js"></script>
        <script src="/LeaveManagement/js/jquery-ui_1.10.3.js"></script>
        <script src="/LeaveManagement/js/messagenotif.js"></script>
        <script src="/LeaveManagement/js/ViewGroupJquery.js"></script>
        <link rel="stylesheet" href="/LeaveManagement/CSS/ViewGroupCss.css" type="text/css" media="screen">
        <script type="text/javascript" src="/LeaveManagement/js/jquery_1.11.2.min.js"></script> 
        <script src="/LeaveManagement/js/jquery-ui_1.11.2.min.js"></script>
        <script type="text/javascript" src="/LeaveManagement/js/paging.js"></script>
        <script type="text/javascript" src="/LeaveManagement/js/delete.js"></script>
        <link rel="stylesheet" href="/LeaveManagement/CSS/paging.css" type="text/css" media="screen">
        
        <script type="text/javascript">
            $(document).ready(function() {
                $('#tableData').paging({limit:25});
            });
        </script>
   
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
         <form action="ViewGroupProcessServlet" method="post" name="record">
            <div id="panel">
                <input type="submit" value="Delete" name="action" id="d"/>
                <input type="submit" value="Update" name="action" id="u"/>
            </div>
            <table id="tableData" cellspacing="12" width="100%">
                <tr><th></th><th bgcolor="orange">Group Name</th><th bgcolor="orange">Casual Leave</th><th bgcolor="orange">Annual Leave</th><th bgcolor="orange">Sick/Medical Leave</th></tr>
           
             
                <c:forEach items="${b}" var="b">
                     <tr>
                        <td><input type="checkbox" class="c" value="${b.gname}" name="view" /></td>
                        <td><c:out value="${b.gname}"></c:out></td>
                        <td><c:out value="${b.cl}"></c:out></td>
                        <td><c:out value="${b.al}"></c:out></td>
                        <td><c:out value="${b.sl}"></c:out></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
         <%
            }
            session.setAttribute("cmsg", false);
            session.setAttribute("emsg", false);
         %>
    </body>
</html>

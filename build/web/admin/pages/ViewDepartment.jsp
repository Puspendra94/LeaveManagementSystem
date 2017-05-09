<%-- 
    Document   : ViewDepartment
    Created on : Feb 5, 2015, 3:16:53 PM
    Author     : Sony
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Department</title>
         <link rel="stylesheet" href="/LeaveManagement/CSS/TrashJquery.css" type="text/css" media="screen">
        <link rel="stylesheet" href="/LeaveManagement/CSS/paging.css" type="text/css" media="screen">
        <link rel="stylesheet" href="/LeaveManagement/CSS/ApplyCss.css" type="text/css" media="screen">
        <script src="/LeaveManagement/js/jquery-1.9.1.js"></script>
        <script src="/LeaveManagement/js/jquery-ui_1.10.3.js"></script>
        <script src="/LeaveManagement/js/messagenotif.js"></script>
        <script src="/LeaveManagement/js/TrashJquery.js"></script>
        <script src="/LeaveManagement/js/delete.js"></script>
        
        <style>
            td{
                text-align: center;
                font-size: 18px;
            }
            #panel{
                display: none;
                margin-left: 88%;
                margin-top: 10px;
            }
        </style>
        
        
        <script type="text/javascript" src="/LeaveManagement/js/jquery_1.11.2.min.js"></script> 
<script src="/LeaveManagement/js/jquery-ui_1.11.2.min.js"></script>
<script type="text/javascript" src="/LeaveManagement/js/paging.js"></script> 
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
        <form action="EditDepartmentServlet" method="post" name="record">
            <div id="panel">
                <input type="submit" value="Edit" name="action"/>
                <input type="submit" value="Delete" name="action"/>
            </div>
            <table id="tableData" cellspacing="12" width="100%">
                <tr><th></th><th bgcolor="orange">Department Id</th><th bgcolor="orange">Department Name</th><th bgcolor="orange">Head Of Department</th></tr>
           
            
                <c:forEach items="${b}" var="b">
                     
                    <tr>
                        <td><input type="checkbox" class="c" value="${b.did}" name="id" /></td>
                        <td><c:out value="${b.did}"></c:out></td>
                        <td><c:out value="${b.dname}"></c:out><input type="hidden" name="dname${b.did}" value="${b.dname}"/></td>
                        <td><c:out value="${b.hod}"></c:out><input type="hidden" name="hod${b.did}" value="${b.hod}"/></td>
                    </tr>
                    
                    </c:forEach>
            </table>
        </form>
        <%
            }
            session.setAttribute("emsg", false);
            session.setAttribute("cmsg", false);
        %>
    </body>
</html>

<%-- 
    Document   : AddDepartment
    Created on : Jan 29, 2015, 2:49:33 PM
    Author     : Sony
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="bca.leave.model.DepartmentBean" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Department</title>
        <link rel="stylesheet" href="/LeaveManagement/CSS/jquery-ui_redmond.css" />
        <link rel="stylesheet" href="/LeaveManagement/CSS/ApplyCss.css" type="text/css" media="screen">
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
        <form action="Department" method="post">
            <table cellspacing="8">
                <input type="hidden" name="id" value="<%=  request.getAttribute("id")%>"/>
                <tr><td>Department Id</td><td><input type="text" name="did" value="<%= request.getAttribute("result")%>" 
                                                     readonly="readonly"></td></tr>
                <tr><td>Department Name</td><td><input type="text" name="dname"></td></tr>
                <tr><td>HOD/In-Charge</td><td><input type="text" name="hod"></td></tr>
                <tr><td></td><td colspan="2"><input type="submit" value="Submit">
                        <input type="reset" value="clear"></td></tr>
            </table>
        </form>
    </center>
     <%
            }
            
            session.setAttribute("cmsg", false);
            session.setAttribute("emsg", false); 
     %>
    </body>
</html>

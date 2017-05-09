<%-- 
    Document   : Message
    Created on : Mar 18, 2015, 8:54:45 PM
    Author     : Sony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="CSS/ApplyCss.css" type="text/css" media="screen">
        <script src="/LeaveManagement/js/jquery-1.9.1.js"></script>
        <script src="/LeaveManagement/js/jquery-ui_1.10.3.js"></script>
        <script>
            $('document').ready(function(){
                $('#emsg,#cmsg').click(function(){
                    $('#emsg').hide(1000);
                    $('#cmsg').hide(1000);
                });
            });
        </script>
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
        
        <%
                }
            ses.setAttribute("emsg", false);
            ses.setAttribute("cmsg", false);
        %>
    </body>
</html>

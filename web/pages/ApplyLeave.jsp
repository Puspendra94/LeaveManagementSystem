<%-- 
    Document   : ApplyLeave
    Created on : Feb 2, 2015, 12:34:22 PM
    Author     : Sony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Apply Leave</title>
        <link rel="stylesheet" href="CSS/ApplyCss.css" type="text/css" media="screen">
         <link rel="stylesheet" href="/LeaveManagement/CSS/jquery-ui_redmond.css" />
        <script src="/LeaveManagement/js/jquery-1.9.1.js"></script>
        <script src="/LeaveManagement/js/jquery-ui_1.10.3.js"></script>
        <script src="/LeaveManagement/js/Validator.js"></script>
        <script src="/LeaveManagement/js/ApplyLeaveJquery.js"></script>
        <script type="text/javascript" src="/LeaveManagement/js/nicEdit-latest.js">
            
        </script> <script type="text/javascript">
//<![CDATA[
        bkLib.onDomLoaded(function() {  
         new nicEditor({buttonList : ['bold','italic','underline','left','center','right',
                 'justify','ol','ul','subscript','superscript','strikethrough','removeformat',
                 'indent','outdent','hr','forecolor','bgcolor','link','unlink','fontSize',
                 'fontFamily','fontFormat']}).panelInstance('area2');
    });
  //]]>
  </script>
  
  <style type="text/css">
            .ui-datepicker {
                background: #335599;
                border: 1px solid #555;
                color: #EEE;
        }
            
        </style>
    </head>
    <body>
        <jsp:include page="UserLoggedIn.jsp"/>
        
        <font color="red"></font>
        <%
             HttpSession ses=request.getSession(false);
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
        <div id="form">
            <h2>Apply Leave</h2>
        <form action="ApplyLeaveServlet" method="post">
            <marquee><div align="right" text="red">(*) fields are mandatory</div></marquee>
            <table cellspacing="10">
                
                <tr><td>User Name</td><td><input type="text" width="750px" value="<%= uname%>" readonly="readonly"></td></tr>
                <tr><td>Leave Type</td><td><select id="type" name="type" width="750px">
                            <option>Casual Leave</option>
                            <option>Annual Leave</option>
                            <option>Sick/Medical Leave</option>
                            <option>Duty Leave</option>
                            <option>Leave Without Pay</option>
                            <option>Special Leave</option>
                            <option>Any Other</option>
                        </select></td></tr>
                <tr><td>From Date(*)</td><td><input type="text" name="fod" placeholder="dd/mm/yyyy" id="start" required ></td></tr>
            <tr><td>To Date(*)</td><td><input type="text" name="tod" placeholder="dd/mm/yyyy" id="end" required></td></tr>
            <tr><td>Days</td><td><input type="text" placeholder="0" required id="day" readonly name="day"/></td></tr>
            
            <tr><td>To(*)</td><td><input type="text" name="to" id="af" list="name" placeholder="Type name or double click"/></td></tr>
            <tr><td>Forwarded By</td><td><input type="text" name="fwdby" list="name" placeholder="Type name or double click"/></td></tr>
            
            <tr><td>Message(*)</td><td><textarea id="area2" name="area2" cols="50" rows="10"></textarea></td></tr>
                <tr><td></td><td colspan="2"><input type="submit" value="Apply" onclick="ApplyValidate()"/><input type="reset" value="clear"/></td></tr>
            </table>
                <datalist id="name">
                    <c:forEach var="b" items="${b}">
                        <option>${b.name}</option>
                    </c:forEach>
                </datalist> 
                
                <input type="hidden" value="${bb.cl}" name="cl"/>
                <input type="hidden" value="${bb.al}" name="al"/>
                <input type="hidden" value="${bb.cl}" name="sl"/>
                <input type="hidden" value="${bb.dl}" name="dl"/>
                <input type="hidden" value="${bb.lwp}" name="lwp"/>
                <input type="hidden" value="${bb.spl}" name="spl"/>
                <input type="hidden" value="${bb.ao}" name="ao"/>
                
        </form>
        </div>
                <div id="nmn">
                    <div>
                    <h2>Balance Leaves</h2>
                    <table cellspacing="8" align="center">
                        <tr><td>Casual Leave</td><td>${bb.cl}</td></tr>
                        <tr><td>Annual Leave</td><td>${bb.al}</td></tr>
                        <tr><td>Sick/Medical Leave</td><td>${bb.sl}</td></tr>
                    </table>
                    </div>
                    <br><br>
                    <div>
                        <h2>Taking Special Type Leaves</h2>
                        <table cellspacing="8" align="center">
                            <tr><td>Duty Leave</td><td>${bb.dl}</td></tr>
                            <tr><td>Leave Without Pay</td><td>${bb.lwp}</td></tr>
                            <tr><td>Special Leave</td><td>${bb.spl}</td></tr>
                            <tr><td>Any Other</td><td>${bb.ao}</td></tr>
                        </table>
                    </div>
                </div>
        
                
                
                
                <div id="fo">
                    <jsp:include page="footer.jsp"/>
                </div>
                <%
                    }
                        ses.setAttribute("cmsg", false);
                        ses.setAttribute("emsg", false);
                %>
    </body>
</html>

<%-- 
    Document   : Login
    Created on : May 17, 2015, 11:38:36 AM
    Author     : Sony
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/admin.css" type="text/css" media="screen">
        <title>JSP Page</title>
    </head>
    <body>
        
        <div id="form"> 
            <h2 class="design">Login Form</h2>
               <form action="AdminLoginServlet" method="post" role="form">
                     <table cellspacing="8" align="center">
                         <tr><td>User Name:</td><td><input type="text" name="uname" class="form-control"/></td></tr>
                        <tr><td>Password:</td><td><input type="password" name="pass"/></td></tr>

                        <tr><td></td><td><input id="submit" type="submit" value="login"/>
        
                        </td></tr>
                     </table>
              </form>
        </div>
    </body>
</html>

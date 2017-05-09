<%-- 
    Document   : 401
    Created on : May 2, 2015, 1:12:13 PM
    Author     : Sony
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="err/css/sss.css" type="text/css" media="screen">
        <title>401 Unauthorized</title>
        <script>
            function back()
            {
                window.history.back();
            }
        </script>
    </head>
    <body>
         
        <br><br><br>
        <div id="panel">
            <center><h1><b>SERVER ERROR</b></h1></center>
            <h2>401 -- Unauthorized</h2><br>
            <div id="con" align="left">
                <h4>Sorry!!!<br>You required an Unauthorized web resource </h4><br><br><br>
            
                <button onclick="back()">Go Back</button><br><br><br><br><br><br><br><br><br><br><br><br>
                
            </div>
            
        </div>
    
    </body>
</html>

<%-- 
    Document   : 400
    Created on : May 2, 2015, 1:03:37 PM
    Author     : Sony
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>400 Bad Request</title>
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
            <h2>400 -- Bad Request</h2>
            <div id="con" align="left">
                <h4>The server cannot or will not process the request due to something that is perceived<br>
                    to be a client error (e.g., malformed request syntax, invalid request message framing,<br>
                    or deceptive request routing). </h4><br><br><br>
            
                <button onclick="back()">Go Back</button><br><br><br><br><br><br><br><br><br><br><br><br>
        
                
            </div>
                    
        </div>
    
    </body>
</html>

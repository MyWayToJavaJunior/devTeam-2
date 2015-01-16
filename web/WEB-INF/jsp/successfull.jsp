<%-- 
    Document   : successfull
    Created on : Jan 15, 2015, 12:14:38 PM
    Author     : vsa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        Command was compleated successfully ! Please, return to dashboard.
        <form id="returnToDashboard" action="Controller" method="POST">
        <input type="hidden" name="command" value="showDashboard">
        <input type="submit" value="return">
    </body>
</html>

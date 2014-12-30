<%--
    Document   : successfullogin
    Created on : Dec 26, 2014, 11:18:01 AM
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
        <h1>SuccessfulLogin for Staff!</h1>
      Welcome, 
        Role :   ${role}
      Worker id: ${id}
                  
 <jsp:useBean id="worker" class = "vsashyn.dt.model.Staff" scope="session"/> 
        <jsp:getProperty name="worker" property="name"/>
        <jsp:getProperty name="worker" property="surname"/>
    </body>
</html>

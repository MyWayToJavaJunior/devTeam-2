<%-- 
    Document   : successfull
    Created on : Jan 15, 2015, 12:14:38 PM
    Author     : vsa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href='${pageContext.servletContext.contextPath}/css/bootstrap.css' rel='stylesheet'>
    </head>
    <body>
        <div class="container">
        <h1> Congratulations! </h1>
        
       Your command has been completed successfully ! Please, return to dashboard.
       
    <c:if test="${ not empty customerID  }">
        <a href="${pageContext.request.contextPath}/controller/dashboard?command=showCustomerDashboard">Home</a>
    </c:if>
    
    <c:if test="${ not empty workerID}">
           <a href="${pageContext.request.contextPath}/controller/dashboard?command=showDashboard">Home</a>
    </c:if>
    </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>

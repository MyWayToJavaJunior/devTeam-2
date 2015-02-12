<%-- 
    Document   : customer_dashboard
    Created on : Jan 26, 2015, 10:25:37 AM
    Author     : vsa
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <link href='${pageContext.servletContext.contextPath}/css/bootstrap.css' rel='stylesheet'>
    </head>
    <body>
    <div class="container">    
        <h1>Hello, Customer!</h1>

        Hello, ${customer.name} !
        Your project are: 

        <br>

        <c:forEach items="${projects}" var="current" varStatus="stus">
            <p>
                Id project : ${current.id}<br>
                Title : ${current.title} <br>
                Bill: ${current.bill} <br>
            </p>
            </c:forEach>

            <%-- Create new Specification  --%>
            <br>
            Easy starting the new project ! Fill the next :
            <br>
        <p>
            Name of next project:
            <input type="text" name="title" form="specform"/>
        </p>
        
        <p>
            Specification  of project (all in your mind):
            <br>
                <textarea class="form-control" rows="3" name="specification" form="specform">
                    Enter text here...
                </textarea>
            <form id="specform"  action="newproject" method="POST">
                <input type="submit" name="Submit" value="submit"/>
                <input type="hidden" name="command" value="addSpecification"/>
            </form> 
        </p>
        
        
        <hr align="center" width="500" size="2" color="#ff0000" />
        <a href="index?command=logout">Logout</a>
        </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>

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
    </head>
    <body>
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
                <textarea rows="4" cols="50" name="specification" form="specform">
                    Enter text here...
                </textarea>
            <form id="specform"  action="newproject" method="POST">
                <input type="submit" name="Submit" value="submit"/>
                <input type="hidden" name="command" value="addSpecification"/>
            </form> 
        </p>
        
        
        <hr align="center" width="500" size="2" color="#ff0000" />
        <a href="controller/index?command=logout">Logout</a>
</body>
</html>

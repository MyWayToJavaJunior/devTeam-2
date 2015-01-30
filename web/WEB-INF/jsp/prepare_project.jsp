<%-- 
    Document   : prepare_project
    Created on : Jan 30, 2015, 12:59:22 PM
    Author     : vsa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Preparing new project</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>    
            Next id Specification for preparing: ${specification.id}
            <br>
            ${specification.customerId}
            <br>
            ${specification.title}
            <br>
            ${specification.specification}
            <br>
        </p>
        <p>
            Next project will be created:
                Name : 
                <input type="text" name="nameProject" form="createProject"/>
            <br>
                Bill:
                <input type="text" name="billProject" form="createProject"/>
            <br>
            Next developers you can add:
            
        <form id="createProject" action="createProject" method="POST" >
            <c:forEach items="${freeWorkers}" var="elem" varStatus="count">
                <input type="checkbox" name="workerIDs" value="${elem.id}" form="createProject"/>
                ${elem.name}
                ${elem.surname}
                ${elem.idQualification}
                <br>
            </c:forEach>
            <input type="hidden" name="command" value="createProject"/>
            <input type="submit" value ="start new project"/>
        </form>
        
        </p>
    </body>
</html>

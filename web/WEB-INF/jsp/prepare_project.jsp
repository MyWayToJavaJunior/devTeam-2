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
        <title>developersTeam</title>
        <link href='${pageContext.servletContext.contextPath}/css/bootstrap.css' rel='stylesheet'>
    </head>
    <body>
        <div class="container">
        <h2>Create new Project!</h2>
        <strong>You was chose next specification : </strong>
        <br>
        <br>
            <table class="table table-hover">
                <tr>
                    <td>Id Specification</td>
                    <td>Id Customer</td>
                    <td>Title</td>
                    <td>Specification</td>
                </tr>
                <tr> 
                    <td>${specification.id}</td>
                    
                    <td>${specification.customerId}</td>
                    <td>${specification.title}</td>
                    <td>${specification.specification}</td>
                </tr>
            </table>
        
        
        <br>
        <strong>Next project will be created:</strong>
        <br> <br>
        <div class="row">
        
                <div class="col-md-3">Name :</div>
                <div class="col-md-3"> 
                    <input type="text" name="nameProject" form="createProject" required autofocus/> 
                </div>
        </div>
        <div class="row">
                <div class="col-md-3">Bill ( in USD ) </div>
                <div class="col-md-3"> 
                    <input type="text" name="billProject" form="createProject" required/> 
                    <span class="glyphicon glyphicon-usd" aria-hidden="true"></span>
                </div>
        </div>
        <br>
        <strong>    Next developers you can add: </strong>
        <br> <br>
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
      </div>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    </body>
</html>

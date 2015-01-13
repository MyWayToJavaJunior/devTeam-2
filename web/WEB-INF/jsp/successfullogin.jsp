<%--
    Document   : successfullogin
    Created on : Dec 26, 2014, 11:18:01 AM
    Author     : vsa
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:out value="Welcome to JSTL"/>
        <%--  <%=session.getId() %> --%>




        <h1>SuccessfulLogin for Staff!</h1>

        <c:if test="${not empty worker }" var="hasSession" scope="session">
            <c:out value = "This from IF coutf. Welcome, ${worker.name}"  />
        </c:if>



        Welcome, 
        Role :   ${role}
        Worker id: ${worker.id}

        <jsp:useBean id="worker" class = "vsashyn.dt.model.Staff" scope="session"/> 
        <jsp:getProperty name="worker" property="name"/>
        <jsp:getProperty name="worker" property="surname"/>
        
        
        <br>
        Projects in which you are involved:
        <c:forEach var="elem" items="${involvedProjects}" varStatus="status">
            
          Id project: ${elem.id}  
          Title: ${elem.title} 
            
        </c:forEach>

    </body>
</html>

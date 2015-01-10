<%--
    Document   : successfullogin
    Created on : Dec 26, 2014, 11:18:01 AM
    Author     : vsa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:out value="Welcome to JSTL"/>
      <%--  <%=session.getId() %> --%>
        ${session.Id}
        ${sessionScope.worker}
        ${id}
        ${worker}
        ${worker.name}
      <%--  <c:if test="${ sessionScope.worker != null} " var="hasSession" scope="session">
            <c:out value = "This from IF coutf. Welcome, ${worker.name}"  />
        </c:if>  --%>
      
        <h1>SuccessfulLogin for Staff!</h1>
      Welcome, 
        Role :   ${role}
      Worker id: ${id}
                  
 <jsp:useBean id="worker" class = "vsashyn.dt.model.Staff" scope="session"/> 
        <jsp:getProperty name="worker" property="name"/>
        <jsp:getProperty name="worker" property="surname"/>
    </body>
</html>

<%--
    Document   : dashboard
    Created on : Dec 26, 2014, 11:18:01 AM
    Author     : vsa
--%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>developersTeam</title>
    </head>
    <body>
        <h1>Welcome to dashboard!</h1>

        <%-- Unauthorized persons are redirecting to login page  --%>
        <c:if test="${ empty worker }" var="unauthorized" scope="session">
            <c:redirect url="index"/>
        </c:if>
        
        <%------------------------------------------------------------------ 
        Import manager dashboard
        ----------------------------------------------------------------
        <c:if test="${role eq 'Manager'}" var="isManager" scope="session">
            <c:import url="WEB-INF\jsp\dashboard_manager.jsp" charEncoding="utf-8"/>
        </c:if> -%>
        
        <%------------------------------------------------------------------ 
        Import developer dashboard
        -----------------------------------------------------------------
        <c:if test="${isManager eq false}" var="isDeveloper" scope="session">
            <c:import url="WEB-INF\jsp\dashboard_developer.jsp" charEncoding="utf-8"/>
        </c:if> -%>
        
        <%------------------------------------------------------------------
        Welcome print
        ------------------------------------------------------------------%>
        <p>
            Welcome, ${worker.name} !
            <br>
            Role :   ${role}
            <br>
            Worker id: ${worker.id}
            <br>
            Ready to get in new project : ${worker.isFree} . 
                Change status to :
                
                
                    <select name="status" form="changeInvolvement">
                        <option value="true">True</option>
                        <option value="false">False</option>
                    </select>
                <form id="changeInvolvement" action="changeInvolvement" method="POST">
                    <input type="hidden" name="command" value="changeInvolvement"/>
                    <input type="submit" name="changeStatus" value="Submit"/>
                </form>
        </p>
        <%--      Example
        <jsp:useBean id="worker" class = "vsashyn.dt.model.Staff" scope="session"/> 
        <jsp:getProperty name="worker" property="name"/>
        <jsp:getProperty name="worker" property="surname"/>
        --%>
        
        
        
        <%--------------------------------------------------------------------
        Projects in witch involved staff worker
        ---------------------------------------------------------------------%>
        <p>
            Projects in which you are involved:
            <c:forEach var="elem" items="${projectsTimes}" varStatus="status">
                Id project: ${elem.key.id} 
                Title: ${elem.key.title} 
                Total time, you spend with this project : ${elem.value}
            </c:forEach>
        </p>
        
        <%--------------------------------------------------------------------
        Adding elapsed time 
        ---------------------------------------------------------------------%>
        <p>
            Please, add elapsed time to project :
            <select name="witchProject" form="elapsedTimes">
                <c:forEach var="elem" items="${projectsTimes}" varStatus="status">
                    <option value="${elem.key.id}">
                        Title: ${elem.key.title} 
                    </option>
                </c:forEach>
            </select> 
            in minutes :
        <form id="elapsedTimes" action="addtime" method="POST">
            <input type="hidden" name="command" value="addElapsedTime"/>
            <input type="text" name="elapsedTime"/>
            <input type="submit" name="changeTime" value ="Submit"/>
        </form>
        </p>
        
        

</body>
</html>

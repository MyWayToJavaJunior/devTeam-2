<%-- 
    Document   : dashboard_manager
    Created on : Jan 26, 2015, 10:49:57 AM
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
        <p>
            Welcome, ${worker.name} !
            <br>
            Role :   ${role}
            <br>
            Worker id: ${worker.id}
        </p>
        
        <p>
            Projects in which you are involved:
            <c:forEach var="elem" items="${projectsTimes}" varStatus="status">
                Id project: ${elem.key.id} 
                Title: ${elem.key.title} 
                Total time, you spend with this project : ${elem.value}
            </c:forEach>
        </p>

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

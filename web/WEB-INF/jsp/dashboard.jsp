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
        <link href='${pageContext.servletContext.contextPath}/css/bootstrap.css' rel='stylesheet'>
        <title>developersTeam</title>
    </head>

    <body>

        <div class='container'>           
            <h1>Welcome to dashboard!</h1>

            <%-- Unauthorized persons are redirecting to login page  --%>
            <c:if test="${ empty worker }" var="unauthorized" scope="session">
                <c:redirect url="index"/>
            </c:if>

            <%------------------------------------------------------------------ 
            Import manager dashboard
            ----------------------------------------------------------------%>
            <c:if test="${role eq 'Manager'}" var="isManager" scope="session">
                <%-- <c:import url="WEB-INF\jsp\dashboard_manager.jsp" charEncoding="utf-8"/> --%>
            </c:if> 

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

        
        <%------- Show all new spec for manager . MANAGER SPACE -----------------
         ------------------------------------------------------------------------%>
        <c:if test="${ isManager and worker.isFree }">
           <h3> New projects, witch you can accept : </h3>

        <table class="table table-hover">    
            <tr>
                <td>Check for preparing</td>
                <td>Title</td>
                <td>Specification</td>
                <td>Customer id</td>
            </tr>
            <form id="prepareProjectForm" action="prepareProject" method="POST" >
                <c:forEach var="elem" items="${requestScope.specificationsNew}" varStatus="status" >
                    <tr>
                       <td> <input type="radio" name="idSpecification"  value="${elem.id}"> </td>
                       <td>${elem.title} </td>
                       <td> ${elem.specification} </td>
                       <td> ${elem.customerId} </td>
                    </tr>
                </c:forEach>
        </table>
            <input type="hidden" name="command" value="prepareProject"/>
            <input type="submit" name="submitProject" value="Begin prepare new Project"/>
            </form>
        
        </c:if>
        <%--------------------------------------------------------------------
        Projects in witch involved staff worker
        ---------------------------------------------------------------------%>

        
    <p>
            <h3>  Projects in which you are involved: </h3>
                <table class="table table-hover">
                    <tr> 
                        <td>id</td>
                        <td>Title</td>
                        <td>Total elapsed time</td>
                        <c:if test="${isManager}"> 
                               <td>Bill</td>
                        </c:if>
                     
                     </tr>
                    <c:forEach var="elem" items="${projectsTimes}" varStatus="status">
                        <tr>        
                            <td>${elem.key.id} </td> 
                            <td> ${elem.key.title}  </td>
                            <td> ${elem.value} </td>
                            <c:if test="${isManager}"> <br>
                                <td> ${elem.key.bill} </td>
                            </c:if>
                        </tr>        
                    </c:forEach>
               </table>  

            
    </p>
    <br>

    <%--------------------------------------------------------------------
    Adding elapsed time 
    ---------------------------------------------------------------------%>
    <p>
        <strong> Please, add elapsed time to project : </strong>
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
        <input type="text" name="elapsedTime" required autofocus/>
        <input type="submit" name="changeTime" value ="Submit"/>
    </form>
</p>



    <a href="index?command=logout">Logout</a>
    <script src='http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js'></script>
    <script src='js/bootstrap.js'></script>

</div>
</body>
</html>

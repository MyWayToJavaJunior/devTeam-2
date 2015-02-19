<%--
    Document   : dashboard
    Created on : Dec 26, 2014, 11:18:01 AM
    Author     : vsa
--%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setBundle basename="context"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='${pageContext.servletContext.contextPath}/css/bootstrap.css' rel='stylesheet'>
        <title>developersTeam</title>
    </head>

    <body>
        <div class='container'>           
            
            <h1><fmt:message key="message.welcome"/></h1>

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
                <fmt:message key="message.greeting"/> ${worker.name} !
                <br>
                <fmt:message key="label.worker_role"/>   ${role}
                <br>
                <fmt:message key="label.id"/> ${worker.id}
                <br>
                <fmt:message key="message.ready_to_get_new_project"/> ${worker.isFree} . 
                <fmt:message key="message.change_status"/>
            <select name="status" form="changeInvolvement">
                    <option value="true"><fmt:message key="label.true"/></option>
                    <option value="false"><fmt:message key="label.false"/></option>
                </select>
            <form id="changeInvolvement" action="changeInvolvement" method="POST">
                <input type="hidden" name="command" value="changeInvolvement"/>
                <input type="submit" name="changeStatus" value="<fmt:message key="label.submit"/>"/>
            </form>
        </p>

        
        <%------- Show all new spec for manager . MANAGER SPACE -----------------
         ------------------------------------------------------------------------%>
        <c:if test="${ isManager and worker.isFree }">
           <h3> <fmt:message key="message.new_project_to_accept"/></h3>

        <table class="table table-hover">    
            <tr>
                <td> <fmt:message key="message.chech_for_preparing"/> </td>
                <td> <fmt:message key="label.title"/>                 </td>
                <td> <fmt:message key="label.spec"/>                  </td>
                <td> <fmt:message key="label.customer_id"/>           </td>
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
            <input type="submit" name="submitProject" value="<fmt:message key="message.begin_prepare_project"/>"/>
            </form>
        
        </c:if>
        <%--------------------------------------------------------------------
        Projects in witch involved staff worker
        ---------------------------------------------------------------------%>

        
    <p>
            <h3> <fmt:message key="message.project_can_involved"/></h3>
                <table class="table table-hover">
                    <tr> 
                        <td><fmt:message key="label.id"/></td>
                        <td><fmt:message key="label.title"/></td>
                        <td><fmt:message key="label.total_elapsed_time"/></td>
                        <c:if test="${isManager}"> 
                               <td><fmt:message key="label.bill"/></td>
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
        <strong><fmt:message key="message.add_elapsed_time"/></strong>
        <select name="witchProject" form="elapsedTimes">
            <c:forEach var="elem" items="${projectsTimes}" varStatus="status">
                <option value="${elem.key.id}">
                    <fmt:message key="label.title"/> ${elem.key.title} 
                </option>
            </c:forEach>
        </select> 
      <fmt:message key="message.in_minutes"/>
    <form id="elapsedTimes" action="addtime" method="POST">
        <input type="hidden" name="command" value="addElapsedTime"/>
        <input type="text" name="elapsedTime" required autofocus/>
        <input type="submit" name="changeTime" value ="<fmt:message key="label.submit"/>"/>
    </form>
</p>



    <a href="index?command=logout">Logout</a>
    <script src='http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js'></script>
    <script src='js/bootstrap.js'></script>
    <br><br>
<ctg:powered-by/>
</div>
</body>
</html>

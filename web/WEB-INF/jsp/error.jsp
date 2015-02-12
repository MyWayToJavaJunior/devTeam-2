<%-- 
    Document   : error
    Created on : Jan 14, 2015, 10:19:09 AM
    Author     : vsa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
        <link href='${pageContext.servletContext.contextPath}/css/bootstrap.css' rel='stylesheet'>
    </head>
    Request from ${pageContext.errorData.requestURI} is failed
    <br/>
    Servlet name or type: ${pageContext.errorData.servletName}
    <br/>
    Status code: ${pageContext.errorData.statusCode}
    <br/>
    Exception: ${pageContext.errorData.throwable}

</body>
</html>

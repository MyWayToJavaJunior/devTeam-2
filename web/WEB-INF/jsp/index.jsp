<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <c:if test="${ not empty worker }" var="hasSession" scope="session">
            <c:redirect url="dashboard"/>
        </c:if>
        <br><br>
        
        You can login as customer.
        <div>
            <form action="controller/auth" method="POST"> <!-- Where to send inputed data -->
                <input type="hidden" name="command" value="loginCustomer" />
                <fieldset>                                  <!-- Group you form  and add legend-->
                    <legend>Enter you Value</legend>
                    <input type="text" name="login" /> <!-- name - variable name of parameter -->
                    <input type="text" name="password" /> 
                    <input type="submit" name="isCustomer" value="Submit"  />
            </form>
        </div>
        <br>


        Or you can connect as employer. Try using login: sashyn.v@gmail.com   pass: 12345
        
        <form action="controller/showdashboard" method="POST"> <!-- Where to send inputed data -->
            <input type="hidden" name="command" value="loginStaff" />
            <fieldset>                                  <!-- Group you form  and add legend-->
                <legend>Enter you Value</legend>
                <input type="text" name="login" value="sashyn.v@gmail.com"/> <!-- name - variable name of parameter -->
                <input type="text" name="password" value="12345" />
                <input type="submit" name="isStaff" value="Submit"  />
        </form>

    </body>
</html>

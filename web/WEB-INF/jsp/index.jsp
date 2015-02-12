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
        <link href='${pageContext.servletContext.contextPath}/css/bootstrap.css' rel='stylesheet'>
    </head>
    <body>

               
        <c:if test="${ not empty worker }" var="hasSession" scope="session">
            <c:redirect url="dashboard"/>
        </c:if>
        <br><br>
      
            
        <div class="container">
        
        
        
        <p>
            <form action="controller/showdashboard" method="POST" class="form-signin"> <!-- Where to send inputed data -->
                <h4 class="form-signin-heading">Please sign in as customer</h4>
                <input type="hidden" name="command" value="loginCustomer" />
                                    
                    <label for="inputEmail" class="sr-only">Email address</label>
                    <input type="text" name="login" value="google@google" class="form-control" placeholder="Email address" required autofocus/> <!-- name - variable name of parameter -->
                    <input type="text" name="password" value="12345" class="form-control" placeholder="Password" required/> 
                    <input type="submit" name="isCustomer" value="Submit"  class="btn btn-lg btn-primary btn-block"/>
            </form>
        </p>
        <br>

        <p>
        <h4 class="form-signin-heading">Or you can connect as employer. Try using login: sashyn.v@gmail.com   pass: 12345
        If you want to connect as manager, try :  "ss@gmail.com", 12345 </h4>
        
        <form action="controller/showdashboard" method="POST" class="form-signin"> <!-- Where to send inputed data -->
            <input type="hidden" name="command" value="loginStaff" />
            
                <label for="inputEmail" class="sr-only">Email address</label>
                <input type="text" name="login" value="sashyn.v@gmail.com" class="form-control" placeholder="Email address" required autofocus/> <!-- name - variable name of parameter -->
                <input type="text" name="password" value="12345" class="form-control" placeholder="Password" required/>
                <input type="submit" name="isStaff" value="Submit"  class="btn btn-lg btn-primary btn-block"/>
        </form>
        </p>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    </body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Damon
  Date: 09/10/2015
  Time: 18:00
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>

<body>
<g:form uri="j_spring_security_check" method="POST">
    <fieldset class="form">
        <div class="fieldcontain required">
            <label for="j_username">User Login</label>
            <g:textField name="j_username" value="${username}"/>
        </div>
        <div class="fieldcontain required">
            <label for="j_password">Password</label>
            <g:passwordField name="j_password"/>
        </div>
        <div class="fieldcontain required">
            <label for="_spring_security_remember_me">Remember me</label>
            <g:checkBox name="_spring_security_remember_me"/>
        </div>
    </fieldset>
    <fieldset class="buttons">
        <g:submitButton name="signIn" value="Sign in"/>
    </fieldset>
</g:form>
</body>
</html>
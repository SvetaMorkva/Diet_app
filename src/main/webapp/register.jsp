<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Registration Form</title>
</head>
<body>
<h1>Register Form</h1>
<form action="register" method="post">
    <table style="with: 50%">
        <tr>
            <td>Login</td>
            <td><input type="text" name="login" /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" /></td>
        </tr>
        <tr>
            <td>Weight</td>
            <td><input type="number" name="weight" /></td>
        </tr>
        <tr>
            <td>Height</td>
            <td><input type="number" name="height" /></td>
        </tr></table>
    <c:if test="${not empty errorMsg}">
        <p style="color: red">${errorMsg}</p>
    </c:if>
    <input type="submit" value="Submit" /></form>
</body>
</html>
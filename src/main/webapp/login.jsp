<%--
  Created by IntelliJ IDEA.
  User: svetamorkva
  Date: 3/2/21
  Time: 1:58 пп
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login Form</title>
</head>
<body>
<h1>Login</h1>
<form action="login" method="post">
    <table style="with: 50%">
        <tr>
            <td>Login</td>
            <td><input type="text" name="login" /></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" /></td>
        </tr>
    </table>
    <input type="submit" value="Submit" /></form>
<c:if test="${not empty errorMsg}">
    <tr>
        <td><p style="color: red">${errorMsg}</p></td>
        <td><a href="/register"><button>Sign up?</button></a></td>
    </tr>
</c:if>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: svetamorkva
  Date: 3/2/21
  Time: 3:25 пп
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add Food Type Form</title>
</head>
<body>
<h1>Create custom food type!</h1>
<form action="addFoodType" method="post">
    <table style="with: 50%">
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" /></td>
        </tr>
        <tr>
            <td>Protein</td>
            <td><input type="number" name="protein" /></td>
        </tr>
        <tr>
            <td>Fats</td>
            <td><input type="number" name="fats" /></td>
        </tr>
        <tr>
            <td>Carbs</td>
            <td><input type="number" name="carbs" /></td>
        </tr>
        <tr>
            <td>Calories</td>
            <td><input type="number" name="calories" /></td>
        </tr></table>
    <input type="submit" value="Add food"/></form>
</body>
</html>
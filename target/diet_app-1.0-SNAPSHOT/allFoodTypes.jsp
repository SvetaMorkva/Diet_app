<%--
 Created by IntelliJ IDEA.
  User: svetamorkva
  Date: 3/2/21
  Time: 5:07 пп
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>All food types!</h1>
<table border="1">
    <tr>
        <td style="display:none;">Id</td>
        <td>Name</td>
        <td>Protein</td>
        <td>Fats</td>
        <td>Carbs</td>
        <td>Calories</td>
    </tr>
    <c:if test="${not empty allFoods}">
    <c:forEach items="${allFoods}" var="item">
        <tr>
            <td style="display:none;"><c:out value="${item.getFoodId()}" /></td>
            <td><c:out value="${item.getName()}" /></td>
            <td><c:out value="${item.getProtein()}" /></td>
            <td><c:out value="${item.getFats()}" /></td>
            <td><c:out value="${item.getCarbs()}" /></td>
            <td><c:out value="${item.getCalories()}" /></td>
        </tr>
    </c:forEach>
    </c:if>
</table>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: svetamorkva
  Date: 3/2/21
  Time: 7:49 пп
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Your custom food types!</h1>
<c:choose>
    <c:when test="${not empty userFoods}">
    <table border="1">
        <tr>
            <td>Name</td>
            <td>Protein</td>
            <td>Fats</td>
            <td>Carbs</td>
            <td>Calories</td>
            <td>Remove</td>
        </tr>
        <c:forEach items="${userFoods}" var="item">
            <tr>
                <td><c:out value="${item.getName()}" /></td>
                <td><c:out value="${item.getProtein()}" /></td>
                <td><c:out value="${item.getFats()}" /></td>
                <td><c:out value="${item.getCarbs()}" /></td>
                <td><c:out value="${item.getCalories()}" /></td>
                <form method="post">
                <input type="hidden" name="actionType" value="removeFood" />
                <input type="hidden" name="foodId" value="${item.getFoodId()}" />
                <td><input type="submit" name="remove" value="-" /></td>
                </form>
            </tr>
        </c:forEach>
    </table>
    </c:when>
    <c:otherwise>
        <h3>Sorry, you have no added products yet</h3>
    </c:otherwise>
</c:choose>
<a href="/addFoodType"><button>Add custom food type</button></a>
<a href="/welcome"><button>Back to main</button></a>
</body>
</html>

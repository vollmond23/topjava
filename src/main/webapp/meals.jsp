<%@ page import="java.lang.Boolean" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="storage" type="java.util.List<ru.javawebinar.topjava.model.MealTo>" scope="request"/>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border="1" cellpadding="10">
    <thead>
    <tr>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
        <td colspan="2"></td>
    </tr>
    </thead>
    <tbody>
    <form method="post" action="meals" enctype="application/x-www-form-urlencoded">
        <tr>
            <td><input type="datetime-local" name="date_time" value="${TimeUtil.truncate(LocalDateTime.now())}"></td>
            <td><input type="text" name="description" value=""/></td>
            <td><input type="text" name="calories" value=""/></td>
            <td colspan="2" align="center">
                <button type="submit">Add meal</button>
            </td>
        </tr>
    </form>
    <c:forEach var="mealTo" items="${storage}">
        <tr bgcolor="${mealTo.excess == Boolean.TRUE ? '#bc8f8f' : '#00FF00'}">
            <td>${TimeUtil.format(mealTo.dateTime)}</td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
            <td><a href="meals?action=edit&id=${mealTo.id}">Edit</a></td>
            <td><a href="meals?action=delete&id=${mealTo.id}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
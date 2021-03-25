<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit meal</h2>
<table border="1" cellpadding="10">
    <thead>
    <tr>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
        <td></td>
    </tr>
    </thead>
    <tbody>
    <form method="post" action="meals" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="id" value="${meal.id}">
        <tr>
            <td><input type="datetime-local" name="date_time" value="${TimeUtil.truncate(meal.dateTime)}"></td>
            <td><input type="text" name="description" value="${meal.description}"/></td>
            <td><input type="text" name="calories" value="${meal.calories}"/></td>
            <td align="center">
                <button type="submit">Edit meal</button>
            </td>
        </tr>
    </form>
    </tbody>
</table>
</body>
</html>
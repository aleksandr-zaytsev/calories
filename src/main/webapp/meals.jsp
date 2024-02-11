<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<section>
    <table>
        <a href="meals?action=new">Add Meal</a>
        <tr>
            <th>Date/Time</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        <jsp:useBean id="mealsTo" scope="request" type="java.util.List"/>
        <c:forEach items="${mealsTo}" var="mealTo">
            <tr class="${mealTo.excess ? 'excess' : 'notexcess'}">
                <td><a href="meals?id=${mealTo.id}&action=view">${mealTo.dateTime.format(TimeUtil.DATE_TIME_FORMATTER)}</a>
                <td>${mealTo.description}</td>
                <td>${mealTo.calories}</td>
                <td><a href="meals?action=update">Update</a></td>
                <td><a href="meals?action=delete">Delete</a></td>

            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
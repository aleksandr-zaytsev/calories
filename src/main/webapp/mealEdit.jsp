<%--suppress HtmlFormInputWithoutLabel --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="ru">
<head>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <title>Edit meal</title>
</head>
<body>
<h3><a href="meals">Meals list</a></h3>
<hr>
<section>
    <form name="mainForm" method="post" action="meals" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="formResult" value="ok" />
        <input type="hidden" name="id" value="${meal.id}">
        <dl>
            <dt>Date / time:</dt>
            <dd><input type="datetime-local" name="dateTime" id="dateTime" size=50 value="${meal.dateTime}" autofocus required></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" name="description" id="description" size=50 value="${meal.description}" required></dd>
        </dl>
        <dl>
            <dt>Calories:</dt>
            <dd><input type="number" name="calories" id="calories" size=50 value="${meal.calories}" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section></body>
</html>
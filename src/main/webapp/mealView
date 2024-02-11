<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <title>View meal</title>
</head>
<body>
<h3><a href="meals">Meals list</a></h3>
<hr>
<section>
    <p>${meal.description}&nbsp;<a href="meals?id=${meal.id}&action=edit"><img src="<c:url value='/img/pencil.png'/>"
                                                                               alt="edit"></a></p>
    <p>${meal.dateTime.format(TimeUtil.DATE_TIME_FORMATTER)}</p>
    <p>${meal.calories}</p>
    <button onclick="window.history.back()">Back</button>
</section>
</body>
</html>
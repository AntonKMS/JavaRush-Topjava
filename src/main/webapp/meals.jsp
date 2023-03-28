<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ru">
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border="1" cellpadding="5" >
<caption>Таблица калорий</caption>
    <thead>
        <tr>
            <th>Дата</th>
            <th>Описание</th>
            <th>Калории</th>
            <th>Превышение</th>
        </tr>
    </thead>
    <tbody bgcolor="#00ff00">

        <c:forEach items="${mealsTo}" var="meal">

                <tr <c:if test="${meal.isExcess()}">bgcolor="#ff0000"</c:if>>
                <td>
                    <fmt:parseDate value="${meal.getDateTime()}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />
                </td>
                <td>${meal.getDescription()}</td>
                <td>${meal.getCalories()}</td>
                <td>${meal.isExcess()}</td>
                </tr>

        </c:forEach>

    </tbody>

</table>
</body>
</html>

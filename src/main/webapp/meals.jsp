<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table class="table">
    <thead>
        <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        </tr>
    </thead>

    <tbody>
            <c:forEach items="${meals}" var="meal">

                <tr <c:if test="${meal.isExcess() == false}"> bgcolor="#d92a2a" </c:if>>
                    <td>
                        <fmt:parseDate value="${meal.getDateTime()}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                        <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />
                    </td>
                    <td>${meal.getDescription()}</td>
                    <td>${meal.getCalories()}</td>
                    </tr>

            </c:forEach>
    </tbody>
</table>
</body>

<style>
    .table {
        width: 100%;
        margin-bottom: 20px;
        border: 5px solid #fff;
        border-top: 5px solid #fff;
        border-bottom: 3px solid #fff;
        border-collapse: collapse;
        outline: 3px solid #ffd300;
        font-size: 15px;
        background: #a2ee89;
    }
    .table th {
        font-weight: bold;
        padding: 7px;
        background: #ffd300;
        border: none;
        text-align: left;
        font-size: 15px;
        border-top: 3px solid #d92a2a;
        border-bottom: 3px solid #ffd300;
    }
    .table td {

        padding: 7px;
        border: none;
        border-top: 3px solid #fff;
        border-bottom: 3px solid #fff;
        font-size: 15px;
    }

</style>
</html>
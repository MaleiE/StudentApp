<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>StudentAdd</title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
</head>
<style>
    body { padding-top: 70px; }
</style>
<body>
<div class="container">
    <%@include file="menu.jsp"%>
    <form action="/app" method="post">
        <h2>Студент ${error}</h2>
        <div class="form-group col-xs-4">
            <label for="name" class="control-label col-xs-4">Имя:</label>
            <input type="text" name="firstName" id="name" class="form-control" value="${sessionScope.firstName}" required="true"/>

            <label for="lastName" class="control-label col-xs-4">Фамилия:</label>
            <input type="text" name="lastName" id="lastName" class="form-control" value="${sessionScope.lastName}" required="true"/>

            <label for="birthdate" class="control-label col-md-6">Год поступления:</label>
            <input type="text"   name="entranceYear" id="birthdate" class="form-control" value="${sessionScope.entranceYear}" placeholder="yyyy" required="true"/>

            <br></br>
            <input type="hidden" name="url" value="/app/studentAddForm">
            <button type="submit" class="btn btn-primary  btn-md">Добавить</button>
        </div>
    </form>
</div>
</body>
</html>

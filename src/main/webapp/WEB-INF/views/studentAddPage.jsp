<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>StudentAdd</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
</head>
<style>
    body { padding-top: 70px; }
</style>
<body>
<div class="container">
    <%@include file="menu.jsp"%>

        <form:form method="POST" modelAttribute="student" action="addStudentPr">
        <form:input type="hidden" path="id" id="id"/>
        <table>
            <tr>
                <td><label for="firsName">Имя: </label> </td>
                <td><form:input path="firsName" id="firstName"/></td>
                <td><form:errors path="firsName" cssClass="error"/></td>
            </tr>

            <tr>
                <td><label for="lastName">Фамилия: </label> </td>
                <td><form:input path="lastName" id="lastName"/></td>
                <td><form:errors path="lastName" cssClass="error"/></td>
            </tr>

            <tr>
                <td><label for="yearRevenue">Дата поступления(dd/mm/yyyy): </label> </td>
                <td><form:input path="yearRevenue" id="yearRevenue"/></td>
                <td><form:errors path="yearRevenue" cssClass="error"/></td>
            </tr>
            <tr>
                <td colspan="3" align="center">
                    <input type="submit" value="Обновить"/>
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>

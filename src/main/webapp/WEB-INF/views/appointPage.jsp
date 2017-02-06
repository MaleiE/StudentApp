<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Appoint</title>
</head>
<body>
<%@include file="menu.jsp"%>
<div>
    <h4>${mess}</h4>
    <table border=1 bgcolor="#85d6ff">
        <tr>
            <td><B>Имя</B></td>
            <td><B>Фамилия</B></td>
            <td><B>Назанчения</B></td>
        </tr>
        <c:forEach items="${students}" var="student">
            <tr>
                <td>${student.firsName}</td>
                <td>${student.lastName}</td>
                <td>
                     <c:forEach items="${student.appoints}" var="app">
                                Предмет ${app.subject.nameSubject}
                     </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

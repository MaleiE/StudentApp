<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Students</title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/bootstrap-select.min.css"/>" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="<c:url value="/css/mycss.css"/>" type="text/css" />
</head>

<body class="bod">


<div class="container">
    <%@include file="menu.jsp"%>
    <h4>Всего студентов ${students.size()}</h4>
    <h4>${mess}</h4>
    <form  method="get">
        <input type="hidden" name="url" value="/app/studentAdd">
        <button  type="submit" class="btn btn-default btn-lg btn-block">Добавить студента <span class="glyphicon glyphicon-user"></span></button>
    </form>
    <table class="table table-striped">
        <thead>
        <tr>
            <td><B>id</B></td>
            <td><B>Имя</B></td>
            <td><B>Фамилия</B></td>
            <td><B>Год поступления</B></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        </thead>
        <c:forEach items="${students}" var="student">
                <tr>
                    <td>${student.id}</td>
                    <td>${student.firsName}</td>
                    <td>${student.lastName}</td>
                    <td>${student.yearRevenue.getYear()}</td>
                    <td>
                        <form  method="get">
                            <input type="hidden" name="url" value="/app/updateSt">
                            <c:choose>
                                <c:when test="${sessionScope.studentId ne null}">
                                    <input type="hidden" name="studentId" value="${sessionScope.studentId}">
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden" name="studentId" value="${student.id}">
                                </c:otherwise>
                            </c:choose>
                            <button type="submit" class="btn btn-primary">Редактировать</button>
                        </form>
                    </td>
                    <td>
                        <form  method="get" >
                            <input type="hidden" name="url" value="/app/deleteSt">
                            <c:choose>
                                <c:when test="${sessionScope.studentId ne null}">
                                    <input type="hidden" name="studentId" value="${sessionScope.studentId}">
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden" name="studentId" value="${student.id}">
                                </c:otherwise>
                            </c:choose>
                            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-trash"></span>Удалить</button>

                        </form>
                    </td>
                    <td>
                        <form  method="get">
                                   <input type="hidden" name="url" value="/app/appointAdd">
                               <select name="subjectId" class="bs-docs-example">
                                   <option value="" disabled selected>Выбрать предмет</option>
                                        <c:forEach items="${subjects}" var="sub1">
                                            <option value=${sub1.id}>Предмет ${sub1.nameSubject}</option>
                                        </c:forEach>
                                   <input type="hidden" name="studentId" value="${student.id}">
                                   <button type="submit" class="btn btn-primary">Назанчить</button>
                               </select>
                        </form>
                    </td>
                </tr>
        </c:forEach>
    </table>
</div>


</body>
</html>
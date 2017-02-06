<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
    <title>Title</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <link href="<c:url value="/resources/css/mycss.css"/>" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/resources/js/student.js"></script>
    <script type="text/javascript" src="/resources/js/bootstrap-birthday.js"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body class="bod">
<%@include file="menu.jsp"%>
<div class="container">
    <div class="row">
        <div class="col-xs-6">

            <div class=" col-md-9 col-lg-9 ">
                <table class="table table-user-information">
                    <tbody>
                    <tr>
                        <td>Имя:</td>
                        <td>${student.firsName}</td>
                    </tr>
                    <tr>
                        <td>Фамилия</td>
                        <td>${student.lastName}</td>
                    </tr>
                    <tr>
                        <td>Год поступления</td>
                        <td>${student.yearRevenue.getYear()}</td>
                    </tr>

                    <tr>
                    <tr>
                        <td>Username</td>
                        <td>${student.username}</td>
                    </tr>
                    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                    <tr>
                        <td>Role</td>
                        <c:forEach items="${student.roles}" var="rol">
                        <td>${rol.getType()}</td>
                        </c:forEach>
                    </tr>
                    </sec:authorize>
                    </tbody>
                </table>
                <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                <h4>Назначить предмет студенту ${student.firsName} ${student.lastName}</h4>
                <form:form action="/appointAdd" method="post" modelAttribute="attend">
                    <form:input type="hidden" path="id"/>
                    <p><label for="subject">Предмет: </label>
                        <select id="subject" name="subject">
                            <option value="0">Выберите предмет</option>
                            <c:forEach items="${sublist}" var="subject">
                                <option value="${subject.id}">${subject.nameSubject}</option>
                            </c:forEach>
                        </select>
                    </p>
                    <input type="hidden" name="studentId" value="${student.id}">
                    <input type="submit" value="Назначить предмет">
                </form:form>
                </sec:authorize>
            </div>

        </div>
        <div class="col-xs-6">
            <table class="table table-striped" id="studentTableResponse">
                <thead>
                <tr>
                    <td><B>id</B></td>
                    <td><B>Назначение</B></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                </thead>
                <c:forEach items="${applist}" var="appoint">
                    <tr id="${appoint.id}">
                        <td>${appoint.id}</td>
                        <td>${appoint.getSubject().nameSubject}</td>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                        <td>
                            <a href="/deleteAppoint/${appoint.id}/${student.id}" class="btn btn-primary">Удалить</a>
                        </td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Студенты</title>
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
<div id="container" class="container">
    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
<%@include file="menu.jsp"%>
        <button class="btn btn-default btn-lg btn-block" data-toggle="modal" data-target="#myModal">
        Добавить студента <span class="glyphicon glyphicon-user"></span>
    </button>
        <div id="success"></div>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">Добавить студента</h4>
                </div>
                <div class="modal-body">
                    <div id="error" class="error"></div>
                    <form:form modelAttribute="student" id="saveStudent" method="get">
                        <div class="form-group">
                            <label for="firsNameInput" class="control-label">Имя:</label>
                            <form:input path="firsName" type="text" id="firsNameInput" name="firsName" class="form-control" required="true"/>

                            <label for="lastNameInput" class="control-label">Фамилия:</label>
                            <form:input path="lastName" type="text" id="lastNameInput" name="lastName" class="form-control" required="true"/>

                            <label for="usernameInput" class="control-label">Username:</label>
                            <form:input path="username" type="text" id="usernameInput" name="username" class="form-control" required="true"/>

                            <label for="passwordInput" class="control-label">Password:</label>
                            <form:input path="password" type="text" id="passwordInput" name="userpasswordname" class="form-control" required="true"/>

                            <div class="date-dropdowns">
                            <label for="yearRevenueInput" class="control-label">Год поступления:</label>
                            <form:input type="hidden" id="yearRevenueInput" name="yearRevenue" path="yearRevenue"/>
                            </div>
                            <br></br>
                        </div>
                        <div class="modal-footer">
                            <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                            <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                            <input id="submit" type="submit" class="btn btn-primary" value="Добавить"/>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>

        <table class="table table-striped" id="studentTableResponse">
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
                <tr id="${student.id}">
                    <td>${student.id}</td>
                    <td>${student.firsName}</td>
                    <td>${student.lastName}</td>
                    <td>${student.yearRevenue.getYear()}</td>
                    <td>
                        <a href="/updateStudent/${student.id}" class="btn btn-warning">Профиль</a>
                    </td>
                    <td>
                        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                        <button class="btn btn-default" data-toggle="modal" data-target="#deleteModal${student.id}">
                            Удалить студента </button>
                        <div class="modal fade bs-example-modal-sm" id="deleteModal${student.id}" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-sm">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title" id="deleteModalLabel">Удалить</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="btn-group">
                                            <form:form  action="/delete/${student.id}.json" method="post" class="del1">

                                                <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                                                <button type="button" class="btn btn-warning" data-dismiss="modal">Отмена</button>
                                                <input  type="submit" class="btn btn-success" value="Удалить" path=""/>
                                            </form:form >
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Студенты</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/resources/css/mycss.css"/>" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/js/student.js"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>

<body class="bod">

<div  class="container">
    <%@include file="menu.jsp"%>
    <h4>Всего студентов ${students.size()}</h4>
    <h4>${mess}</h4>
    <button class="btn btn-default btn-lg btn-block" data-toggle="modal" data-target="#myModal">
        Добавить студента <span class="glyphicon glyphicon-user"></span>
    </button>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">Добавить студента</h4>
                </div>
                <div class="modal-body">
                    <div id="error" class="error"></div>
                    <form:form modelAttribute="student" id="saveStudentInput" method="get">
                        <div class="form-group">
                            <label for="firsNameInput" class="control-label">Имя:</label>
                            <form:input path="firsName" type="text" id="firsNameInput" name="firsName" class="form-control" required="true"/>

                            <label for="lastNameInput" class="control-label">Фамилия:</label>
                            <form:input path="lastName" type="text" id="lastNameInput" name="lastName" class="form-control" required="true"/>

                            <label for="yearRevenueInput" class="control-label">Год поступления:</label>
                            <form:input path="yearRevenue" type="text" id="yearRevenueInput" name="yearRevenue" class="form-control" required="true"/>

                            <br></br>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                            <input id="submit" type="submit" class="btn btn-primary" value="Добавить"/>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
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
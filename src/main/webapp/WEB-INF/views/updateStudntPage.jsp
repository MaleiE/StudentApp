<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>StudentAdd</title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/mycss.css"/>" rel="stylesheet" type="text/css" />

</head>
<body class="bod">
<div class="container">
    <%@include file="menu.jsp"%>
    <div class="col-md-4">
        <form action="/app" method="post" class="">
            <h2>Студент ${error}</h2>
            <div class="form-group">
                <label for="name" class="control-label col-xs">Имя:</label>
                <input type="text" name="firstName" id="name" class="form-control" value="${studentFirstName}" required="true"/>
                <label for="lastName" class="control-label col-xs">Фамилия:</label>
                <input type="text" name="lastName" id="lastName" class="form-control" value="${studentLastName}" required="true"/>
                <label for="birthdate" class="control-label col-xs">Год поступления:</label>
                <input type="text"   name="entranceYear" id="birthdate" class="form-control" value="${studentYear}"  required="true"/>
                <br></br>
                <input type="hidden" name="studentId" value="${id}">
                <input type="hidden" name="url" value="/app/studentUpdateForm">
                <button type="submit" class="btn btn-primary  btn-md">Обновить</button>
            </div>
        </form>
    </div>
    <div class="col-md-8">
        <form action="/app" method="post" class="">
            <h2>Назанчения ${error}</h2>
            <div>
                <table class="table table-striped">
                    <c:forEach items="${appoint}" var="appo">
                        <tr>
                            <td>${appo.subject.nameSubject}</td>
                            <td>
                                <form  method="get" >
                                    <input type="hidden" name="url" value="/app/deleteAppo">
                                    <input type="hidden" name="appoId" value="${appo.id}">
                                    <input type="hidden" name="studentId" value="${id}">
                                    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-trash"></span>Удалить</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </div>
</div>
</body>
</html>

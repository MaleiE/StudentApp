<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Subject</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <link href="<c:url value="/resources/css/mycss.css"/>" rel="stylesheet" type="text/css"/>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

</head>
<body class="bod">
        <div class="container">
            <%@include file="menu.jsp"%>
            <div class="col-md-4">
                    <form:form modelAttribute="subject" action="subjectAdd" method="post">
                        <div>
                            <label for="titleInput">Название предмета</label>
                            <form:input path="nameSubject" type="text" name="nameSubject" id="titleInput" class="form-control"/>
                        </div></br>
                        <div>
                            <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                            <input id="submit" class="btn btn-primary"  type="submit" value="Добавить"/>
                        </div>
                    </form:form>

            </div>
            <div class="col-md-8">
                    <h4 class="text-info">Всего предметов ${subjects.size()}</h4>
                    <div>
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <td>id</td>
                                <td>Название</td>
                                <td></td>
                                <td></td>
                            </tr>
                            </thead>
                            <c:forEach items="${subjects}" var="subject">
                                <tr>
                                    <td>${subject.id}</td>
                                    <td>${subject.nameSubject}</td>
                                    <td>
                                        <form:form  modelAttribute="subject" action="/updateSubject/${subject.id}" method="post" >
                                            <div class="input-group">
                                                <form:input path="nameSubject" type="text"  value="${subject.nameSubject}"  name="nameSubject"  class="form-control" required="true"/>
                                                <div class="input-group-btn">
                                                    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                                                    <button type="submit" class="btn btn-primary">Изменить</button>
                                                </div>
                                            </div>
                                        </form:form>
                                    </td>
                                    <td>
                                        <a href="/subjectDelete/${subject.id}" class="btn btn-primary"><span class="glyphicon glyphicon-trash"></span>Удалить</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
            </div>
        </div>
</body>
</html>

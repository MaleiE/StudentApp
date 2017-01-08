<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Subject</title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/css/mycss.css"/>" rel="stylesheet" type="text/css" />

</head>
<body class="bod">
        <div class="container">
            <%@include file="menu.jsp"%>
            <div class="col-md-4">
                <form   action="/app" method="post" >
                    <c:if test="${error!=null}">
                        <h1 class="text-danger">${error}</h1>
                    </c:if>
                    <c:if test="${mess!=null}">
                        <h1 class="text-success">${mess}</h1>>
                    </c:if>
                    <label for="name" class="control-label"> Название предмета:</label>
                    <input type="text" name="subjectName" id="name" class="form-control" value="${sessionScope.firstName}" required="true"/>
                    <input type="hidden" name="url" value="/app/subjectAdd">
                    <br></br>
                    <button type="submit" class="btn btn-primary  btn-md">Добавить предмет</button>
                </form>
            </div>
            <div class="col-md-8">
                <form action="/app" method="post" >
                    <h4 class="text-info">Всего предметов ${subject0.size()}</h4>
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
                            <c:forEach items="${subject0}" var="subject">
                                <tr>
                                    <td>${subject.id}</td>
                                    <td>${subject.nameSubject}</td>
                                    <td>
                                        <form action="/app" method="get" >
                                            <div class="input-group">
                                                <input type="text"   name="nameSub"  class="form-control" value="${subject.nameSubject}"  required="true"/>
                                                <input type="hidden" name="idSub" value=${subject.id}>
                                                <input type="hidden" name="url" value="/app/updateSub">
                                                <div class="input-group-btn">
                                                    <button type="submit" class="btn btn-primary">Изменить</button>
                                                </div>
                                            </div>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="/app" method="get" >
                                            <input type="hidden" name="url" value="/app/deleteSub">
                                            <input type="hidden" name="idSub" value=${subject.id}>
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

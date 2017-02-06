<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>ауцацу </title>
</head>
<body>
<a href="<c:url value='/students' />"> На главную </a>
<c:url var="principalName" value="${pageContext.request.userPrincipal.name}"/>
Чет не так
</body>
</html>

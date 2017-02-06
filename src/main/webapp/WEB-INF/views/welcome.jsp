<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Welcome</title>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/js/welcome.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.particleground.js"></script>
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/resources/css/welcomecss.css"/>" rel="stylesheet" type="text/css" />



</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-7">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-lock"></span> Login</div>
                <div class="panel-body">
                    <form class="form-horizontal" action="/login" method="post">
                        <div class="form-group">
                            <label for="username" class="col-sm-3 control-label">
                                Логин</label>
                            <div class="col-sm-9">
                                <input type="text" id="username" name="username" class="form-control" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-3 control-label">
                                Password</label>
                            <div class="col-sm-9">
                                <input type="password" id="password" name="password" class="form-control" required/>
                            </div>
                            <div class="input-group input-sm">
                                <div class="checkbox">
                                    <label><input type="checkbox" id="rememberme" name="remember-me"> Remember Me</label>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                        <div class="form-group last">
                            <div class="col-sm-offset-3 col-sm-9">
                                <input type="submit" class="btn btn-success btn-sm" value="Sign in">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-footer">
                    Not Registred? <a href="http://www.jquery2dotnet.com">Register here</a>
                                       <a href="http://www.jquery2dotnet.com">ru</a>
                        |
                        <a href="http://www.jquery2dotnet.com">en</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

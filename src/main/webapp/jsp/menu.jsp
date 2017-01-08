<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-collapse collapse" id="bs-example-navbar-collapse-9" style="height: 1px;">
            <ul class="nav navbar-nav">
                <li>
                    <div class="navbar-form navbar-left">
                        <div class="form-group">
                            <form  method="get" class="centering" >
                                <input type="hidden" name="url" value="/app/start" class="form-control">
                                <button  type="submit" class="btn btn-default"><b>На главную</b></button>
                            </form>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="navbar-form navbar-left">
                        <div class="form-group">
                            <form  method="get" class="button" >
                                <input type="hidden" name="url" value="/app/subject" class="form-control">
                                <button  type="submit" class="btn btn-default">Предметы</button>
                            </form>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>
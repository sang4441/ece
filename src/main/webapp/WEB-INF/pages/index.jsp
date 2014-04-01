<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="/ece/resources/css/bootstrap.min.css" />
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="/ece/resources/js/bootstrap.min.js"></script>
</head>
<a style="color:red">${login}</a>
<body>
    <div class="row">
        <div class="col-md-3 col-md-offset-4">
            <form id="login" action="login_action" method="post" >
                <h1>Log in</h1>
                <div>&nbsp</div>
                <div><input class="form-control" type="text" id="username" name="username" placeholder="username" /></div>
                <div><input class="form-control" type="password" id="password" name="password" placeholder="password"/></div>
                <button type="submit" class="btn btn-default">login</button>
            </form>
        </div>
    </div>

</body>
</html>
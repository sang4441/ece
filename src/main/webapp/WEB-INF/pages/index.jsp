<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<a style="color:red">${login}</a>
<body>
    <form id="login" action="login_action" method="post" >
        <input type="text" id="username" name="username" placeholder="username" />
        <input type="password" id="password" name="password" placeholder="password"/>
        <button type="submit">login</button>
    </form>
</body>
</html>
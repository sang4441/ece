<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="stylesheet" href="/ece/resources/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/ece/resources/css/style.css" />
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="/ece/resources/js/bootstrap.min.js"></script>
</head>
<body>
<div class="row">
    <div class="col-md-10 col-md-offset-2">
        <div class="btn-group">
            <button class="btn btn-default"><a href="/ece/financial/dashboard">Financial's dashboard</a></button>
        </div>
        <button class="btn btn-default"><a href="/ece/log_out">Logout</a></button>

        <c:set var="content" value="${content}.jsp"/>
        <jsp:include page="${content}"/>
    </div>
</div>
</body>
</html>

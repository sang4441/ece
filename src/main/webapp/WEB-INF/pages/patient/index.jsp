<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="stylesheet" href="/ece/resources/css/style.css" />
    <link rel="stylesheet" href="/ece/resources/css/bootstrap.min.css" />
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="/ece/resources/js/bootstrap.min.js"></script>

</head>
<body>
<div class="row">
    <div class="col-md-10 col-md-offset-2">
        <div class="btn-group">
            <button class="btn btn-default"><a href="/ece/patient/dashboard">Dashboard</a></button>
            <button class="btn btn-default"><a href="/ece/patient/profile/${user.personId}">view my profile</a></button>
            <button class="btn btn-default"><a href="/ece/patient/edit_profile/${user.personId}">edit my profile</a></button>
            <button class="btn btn-default"><a href="/ece/patient/visit_record/${user.personId}">view my visit</a></button>
        </div>
        <a href="/ece/log_out"><button class="btn btn-default">Logout</button></a>
        <c:set var="content" value="${content}.jsp"/>
        <jsp:include page="${content}"/>
    </div>
</div>
</body>
</html>

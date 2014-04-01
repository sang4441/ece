<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="stylesheet" href="/ece/resources/css/style.css" />
    <link rel="stylesheet" href="/ece/resources/css/bootstrap.min.css" />
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="/ece/resources/js/bootstrap.min.js"></script>
</head>
<body>
<a href="/ece/log_out"><button>Logout</button></a>
<c:set var="content" value="${content}.jsp"/>
<jsp:include page="${content}"/>
</body>
</html>

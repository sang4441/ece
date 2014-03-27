<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="stylesheet" href="/ece/resources/css/style.css" />
</head>
<body>
<a href="/ece/log_out"><button>logout</button></a><c:set var="content" value="${content}.jsp"/>
<jsp:include page="${content}"/>
</body>
</html>

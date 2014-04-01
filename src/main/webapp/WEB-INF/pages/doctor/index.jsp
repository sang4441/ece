<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="stylesheet" href="/ece/resources/css/style.css" />
</head>
<body>
<a href="/ece/log_out"><button>Logout</button></a>
<a href="/ece/doctor/search_patient"><button>Search Patient</button></a>
<a href="/ece/"><button>Search Appointment</button></a>
<a href="/ece/doctor/grant_permission"><button>Grant Permission</button></a>
<c:set var="content" value="${content}.jsp"/>
<jsp:include page="${content}"/>
</body>
</html>

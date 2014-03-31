<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="stylesheet" href="/ece/resources/css/style.css" />
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
</head>
<body>
<button><a href="dashboard">Staff Dashboard</a></button>
<button><a href="see_appointment">See appointment</a></button>
<button><a href="create_appointment_form_1">Create appointment</a></button>
<button><a href="create_patient_form">New patient</a></button>
<button><a href="update_patient">Update patient</a></button>
<button><a href="review_patient">Review Records</a></button>
<a href="/ece/log_out"><button>logout</button></a><c:set var="content" value="${content}.jsp"/>
<jsp:include page="${content}"/>
</body>
</html>

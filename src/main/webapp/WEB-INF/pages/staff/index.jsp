<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="stylesheet" href="/ece/resources/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/ece/resources/css/style.css" />
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script src="/ece/resources/js/bootstrap.min.js"></script>
</head>
<body>

<div class="row">
    <div class="col-md-10 col-md-offset-2">
        <div class="btn-group">
            <button class="btn btn-default"><a href="/ece/staff/dashboard">Staff Dashboard</a></button>
            <button class="btn btn-default"><a href="/ece/staff/see_appointment">See appointment</a></button>
            <button class="btn btn-default"><a href="/ece/staff/create_appointment_form_1">Create appointment</a></button>
            <button class="btn btn-default"><a href="/ece/staff/create_patient_form">New patient</a></button>
            <button class="btn btn-default"><a href="/ece/staff/update_patient">Update patient</a></button>
            <button class="btn btn-default"><a href="/ece/staff/review_patient">Review Records</a></button>
        </div>
<a href="/ece/log_out"><button class="btn btn-default">Logout</button></a><c:set var="content" value="${content}.jsp"/>
<jsp:include page="${content}"/>
    </div>
</div>
</body>
</html>

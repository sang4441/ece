<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Select a Doctor</h2>

<c:forEach items="${doctors}" var="doctor">
    <div><a href="select_patient/${doctor.id}">${doctor.nameFirst} ${doctor.nameLast}</div></a>
</c:forEach>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Select patients</h2>

Received doctorId : ${doctorId}
Status : ${status}

<c:forEach items="${patients}" var="patient">
    <div><a href="/ece/staff/add_patient/${patient.id}/${doctorId}">${patient.nameFirst} ${patient.nameLast}</div></a>
</c:forEach>



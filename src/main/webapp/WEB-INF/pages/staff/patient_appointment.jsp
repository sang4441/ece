<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<h2>${patient.nameLast} ${patient.nameFirst}'s appointments</h2>
<c:forEach items="${appointments}" var="appointment">
    <a href="/ece/staff/appointment_schedule/${appointment.id}"><div>${patient.nameLast} ${patient.nameFirst} ${appointment.id} ${appointment.date}</div></a>
</c:forEach>


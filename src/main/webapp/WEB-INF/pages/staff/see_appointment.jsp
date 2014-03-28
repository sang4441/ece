<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>See appointment</h2>
<form method="post" action="see_appointment">
    Search patient: <input type="text" name="keyword"/>
</form>

<form method="post" action="reschedule">
    <c:forEach items="${appointments}" var="appointment" >
        <div><input type="radio" name="patientId" value="${appointment.patientId}"/>${appointment.patientName} ${appointment.date}</div>
    </c:forEach>
    <input type="hidden" name="patientId" value="${app}">
    <input type="submit" value="reschedule">
    <button>Delete</button>
</form>

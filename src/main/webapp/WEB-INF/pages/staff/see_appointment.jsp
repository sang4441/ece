<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>See appointment</h2>
<%--<form method="post" action="see_appointment">--%>
    <%--Search patient: <input type="text" name="keyword"/>--%>
<%--</form>--%>

<form method="post"action="/ece/staff/appointment_form_submit">
    <div>Reschedule date ${appointment.date} to:</div>
    <div>Patient: ${patient.nameLast} ${patient.nameFirst}</div>
    <%--<div>Doctor: ${doctor.nameLast} ${doctor.nameFirst}</div>--%>
    <input type="hidden" name="appointmentId" value="${appointment.id}">
    <%--<input type="hidden" name="doctorId" value="${doctor.id}">--%>
    <input type="hidden" name="patientId" value="${patient.personId}">
    <div>
        <table>
            <td>
                <div>time/day</div>
                <div>9:00</div>
                <div>9:30</div>
                <div>10:00</div>
                <div>10:30</div>
                <div>11:00</div>
                <div>11:30</div>
                <div>1:00</div>
                <div>1:30</div>
                <div>2:00</div>
                <div>2:30</div>
                <div>3:00</div>
                <div>3:30</div>
                <div>4:00</div>
                <div>4:30</div>
            </td>
            <c:forEach items="${schedule}" var="scheduleDay" >
                <td>
                    <c:forEach items="${scheduleDay}" var="slot">
                        <c:choose>
                            <c:when test="${slot.value == 'filled'}">
                                <%--<div class="schedule-open">${slot.value}</div>--%>
                                <%--<c:set var="object" value="${slot.value}"/>--%>
                                <div class="schedule-filled"></div>
                            </c:when>
                            <c:when test="${slot.key == '0'}">
                                <div class="schedule-open">${slot.value}</div>
                            </c:when>
                            <c:otherwise>
                                <div class="schedule-open"><input type="radio" name="time" value="${slot.value},${slot.key}"></div>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </td>
            </c:forEach>
        </table>
    </div>

    <button id="reschedule">Reschedule</button>
    <button>Delete</button>
</form>
<script>

    $('#reschedule').click(function() {
        window.location.href = "/ece/staff/reschedule/" + $('input[type="radio"]:checked').val();
//        $.get( "/ece/staff/reschedule/" + $('input[type="radio"]:checked').val());
    })
</script>
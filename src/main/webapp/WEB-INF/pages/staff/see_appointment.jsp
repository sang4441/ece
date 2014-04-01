<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form method="post"action="/ece/staff/appointment_form_submit">
    <div><h4>Patient: ${patient.nameLast} ${patient.nameFirst}</h4></div>
    <input type="hidden" name="appointmentId" value="${appointment.id}">
    <input type="hidden" name="patientId" value="${patient.id}">
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
                                <div class="schedule-open schedule-header">${slot.value}</div>
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
    <button id="reschedule" class="btn btn-default" type="submit" disabled>Reschedule</button>
</form>
<button id="delete" class="btn btn-default">Delete</button>

<script>
    $( document ).ready(function() {
        $( ".schedule-header").eq(${dayDiff}).nextAll().eq(${appointment.dateCode}-1).css("background", "yellow");
//        $('#reschedule').click(function() {
//            window.location.href = "/ece/staff/reschedule/" + $('input[type="radio"]:checked').val();
//        });

        $('input[type="radio"]').click(function() {
            $('#reschedule').removeAttr('disabled');
        })


        $('#delete').click(function() {
            window.location.href = "/ece/staff/appointment_delete/" + ${appointment.id};
        });
    });
</script>
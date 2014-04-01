<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form method="post"action="/ece/staff/appointment_form_submit">
    <c:choose>
        <c:when test="${type=='reschedule'}">
            <h2>Reschedule appointment</h2>
            <input type="hidden" value="reschedule" name="type">
        </c:when>
        <c:otherwise>
            <h2>Create appointment</h2>
            <input type="hidden" value="create" name="type">
        </c:otherwise>
    </c:choose>

    <div>Patient: ${patient.nameLast} ${patient.nameFirst}</div>
    <div>Doctor: ${doctor.nameLast} ${doctor.nameFirst}</div>
    <%--<input type="hidden" name="id" value="${schedule.id}">--%>
    <input type="hidden" name="doctorId" value="${doctor.id}">
    <input type="hidden" name="patientId" value="${patientId}">
    <%--From: <input type="text" class="datepicker"> To: <input type="text" class="datepicker"> <button id="change_date">Change date</button>--%>
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

    <%--<div>Prescription: <input type="text" name="prescription"></div>--%>
    <%--<div>Diagnosis: <input type="text" name="diagnosis"></div>--%>
    <%--<div>Comment: <textarea name="comment"></textarea></div>--%>
    <input class="btn btn-default" type="submit" value="submit">
</form>
<script>
    <%--$( document ).ready(function() {--%>
    <%--<c:forEach items="${dateDiffList}" var="dateDiff" >--%>

    <%--</c:forEach>--%>
        <%--$( ".schedule-header").eq(${dayDiff}).nextAll().eq(${appointment.dateCode}-1).css("background", "yellow");--%>
    <%--});--%>
</script>

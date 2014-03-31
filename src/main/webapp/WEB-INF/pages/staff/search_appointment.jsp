<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>See appointment</h2>
<form method="post" action="see_appointment">
    Search patient: <input type="text" name="keyword"/>
</form>

<%--<form method="post" action="reschedule">--%>
    <c:forEach items="${appointments}" var="appointment" >
        <a href="/ece/staff/see_appointment/${appointment.id}"><div>${appointment.patientName} ${appointment.date}</div></a>
    </c:forEach>
    <%--<button type="hidden" name="patientId" value="${app}">--%>
    <%--<button id="reschedule">Reschedule</button>--%>
    <%--<button>Delete</button>--%>
<%--</form>--%>
<%--<script>--%>

    <%--$('#reschedule').click(function() {--%>
        <%--window.location.href = "/ece/staff/reschedule/" + $('input[type="radio"]:checked').val();--%>
<%--//        $.get( "/ece/staff/reschedule/" + $('input[type="radio"]:checked').val());--%>
    <%--})--%>
<%--</script>--%>
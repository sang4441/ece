<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form method="post"action="appointment_form_submit">
    <h2>Create appointment</h2>
    <div>Doctor: ${doctor.nameLast} ${doctor.nameFirst}</div>
    From: <input type="text" class="datepicker"> To: <input type="text" class="datepicker"> <button id="change_date">Change date</button>
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
                <div>12:00</div>
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
                <div>${slot.value}</div>
                </c:forEach>
                </td>
            </c:forEach>
        </table>
        <%--<div></div>--%>
        <%--<div>Mar 27th</div>--%>

        <%--<a href="create_appointment_form_2/${patient.personId}"><div>${patient.nameFirst} ${patient.nameLast}</div></a>--%>

        <%--<select name="date">--%>
            <%--<option value="1:30">1:30</option>--%>
            <%--<option value="2:00">2:00</option>--%>
            <%--<option value="2:30">2:30</option>--%>
            <%--<option value="3:00">3:00</option>--%>
        <%--</select>--%>
    </div>

    <div>Prescription: <input type="text" name="prescription"></div>
    <div>Diagnosis: <input type="text" name="diagnosis"></div>
    <div>Comment: <textarea name="comment"></textarea></div>
    <input type="submit" value="submit">
</form>
<script>
    $(function() {
        $(".datepicker" ).datepicker();
//        $("#change_date").click(function() {
//            $.get( "test.php", { name: "John", time: "2pm" } );
//        })
    });
</script>
<form method="post"action="appointment_form_submit">
    <h2>Create appointment</h2>
    <div>Doctor: ${doctor.nameLast} ${doctor.nameFirst}</div>
    <div>Time:
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
        </table>
        <%--<div></div>--%>
        <%--<div>Mar 27th</div>--%>


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

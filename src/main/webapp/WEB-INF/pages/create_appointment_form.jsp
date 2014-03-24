<form method="post"action="appointment_form_submit">
    <h2>Create appointment</h2>
    <div>Time:
        <select name="date">
        <option value="1:30">1:30</option>
        <option value="2:00">2:00</option>
        <option value="2:30">2:30</option>
        <option value="3:00">3:00</option>
    </select>
    </div>
    <div>Prescription: <input type="text" name="prescription"></div>
    <div>Diagnosis: <input type="text" name="diagnosis"></div>
    <div>Comment: <textarea name="comment"></textarea></div>
    <input type="submit" value="submit">
</form>

<%--private int id;--%>
<%--private int patient_id;--%>
<%--private Date date;--%>
<%--private int length;--%>
<%--private String prescription;--%>
<%--private String diagnosis;--%>
<%--private int doctor_id;--%>
<%--private Date date_modified;--%>
<%--private String comment;--%>
<%--private int initial_id;--%>
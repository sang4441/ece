<form method="post"action="appointment_form_submit">
    <h2>Create appointment</h2>
    <div>Doctor: ${doctor.nameLast} ${doctor.nameFirst}</div>
    <div>Prescription: <input type="text" name="prescription"></div>
    <div>Diagnosis: <input type="text" name="diagnosis"></div>
    <div>Comment: <textarea name="comment"></textarea></div>
    <input type="submit" value="submit">
</form>

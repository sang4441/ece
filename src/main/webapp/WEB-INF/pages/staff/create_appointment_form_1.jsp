<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Create appointment</h2>
<form method="post" action="create_appointment_form_1">
    Search patient: <input type="text" name="keyword"/>
</form>
<c:forEach items="${patients}" var="patient" >
    <a href="create_appointment_form_2/${patient.personId}"><div>${patient.nameFirst} ${patient.nameLast}</div></a>
</c:forEach>
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
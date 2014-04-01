<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2>${doctor.nameFirst} ${doctor.nameLast} had ${numPatient} patients this year</h2>

<table border="1">
    <tr>
        <td># of visit</td>
        <td>Patient Name</td>
        <td>Date</td>
        <td>Time</td>
        <td>Prescription</td>
        <td>Diagnosis</td>
        <td>Surgery</td>
        <td>Comment</td>
    </tr>
    <c:forEach items="${records}" var="record" >
        <tr>
            <td>${record.numVisit}</td>
            <td>${record.patientName}</td>
            <td> <fmt:formatDate type="date" dateStyle="long" value="${record.date}" /> </td>
            <td> <fmt:formatDate type="time" timeStyle="short" value="${record.date}" /> </td>
            <td>${record.prescription}</td>
            <td>${record.diagnosis}</td>
            <td>${record.surgery}</td>
            <td>${record.comment}</td>
        </tr>
    </c:forEach>
</table>




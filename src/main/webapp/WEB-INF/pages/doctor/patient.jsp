<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<a href="/ece/doctor/patient/${patient.id}/grant_permission"><button class="btn btn-default">Grant Permission</button></a>
<a href="/ece/doctor/patient/${patient.id}/revoke_permission"><button class="btn btn-default">Revoke Permission</button></a>
<article class="info">
<h1>Records for ${patientName}</h1>
<table>
    <tr>
        <th>id</th>
        <th>Date</th>
        <th>Date Code</th>
        <th>Length (min)</th>
        <th>Prescription</th>
        <th>Diagnosis</th>
        <th>Comment</th>
        <th>Date Modified</th>
        <th>Initial ID</th>
    </tr>
    <c:forEach items="${visits}" var="visit" >
        <tr>
            <td><a href="/ece/doctor/appointment/${visit.id}">Edit</a></td>
            <td>${visit.date}</td>
            <td align="right">${visit.dateCode}</td>
            <td align="right">${visit.length}</td>
            <td>${visit.prescription}</td>
            <td>${visit.diagnosis}</td>
            <td>${visit.comment}</td>
            <td>${visit.dateModified}</td>
            <td align="right">${visit.initialID}</td>2
        </tr>
    </c:forEach>
</table>

<h1> Doctors with Permission</h1>
<ol>
<c:forEach items="${doctors}" var="doctor" >
    <li>${doctor.nameFirst} ${doctor.nameLast}</li>
</c:forEach>
</ol>

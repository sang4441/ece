<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<a href="/ece/doctor/patient/${patient.id}/grant_permission"><button class="btn btn-default">Grant Permission</button></a>
<a href="/ece/doctor/patient/${patient.id}/revoke_permission"><button class="btn btn-default">Revoke Permission</button></a>

<article class="info">
<h1>Records for ${patientName}</h1>
<table>
    <tr>
        <th style="padding:5"></th>
        <th>Date</th>
        <th>Time</th>
        <th>Prescription</th>
        <th>Diagnosis</th>
        <th>Comment</th>
        <th>Date Modified</th>
        <th></th>
    </tr>
    <c:forEach items="${visits}" var="visit" varStatus="counter">
        <tr>
            <td>${counter.count}</td>            
            <td style="padding:5"> <fmt:formatDate type="date" dateStyle="long" value="${visit.date}" /> </td>
            <td style="padding:5"> <fmt:formatDate type="time" timeStyle="short" value="${visit.date}" /> </td>
            <td>${visit.prescription}</td>
            <td>${visit.diagnosis}</td>
            <td>${visit.comment}</td>
            <td>${visit.dateModified}</td>
            <td><a href="/ece/doctor/appointment/${visit.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>

<h1> Doctors with Permission</h1>
<ol>
<c:forEach items="${doctors}" var="doctor" >
    <li>${doctor.nameFirst} ${doctor.nameLast}</li>
</c:forEach>
</ol>

<%@ page import="com.springapp.mvc.model.Doctor" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2>Patient Dashboard for ${user.nameFirst}</h2>

<a href="/ece/patient/profile/${personId}"><button>view my profile</button></a>
<table>
    <tr>
        <td>Id</td>
        <td>Date</td>
        <td>Time</td>
        <td>Length</td>
        <td>Doctor</td>
        <td>Prescription</td>
        <td>Diagnosis</td>
    </tr>
    <c:forEach items="${appointments}" var="appointment" >
        <tr>
            <td align="right">${appointment.id}</td>
            <td> <fmt:formatDate type="date" dateStyle="long" value="${appointment.date}" /> </td>
            <td> <fmt:formatDate type="time" timeStyle="short" value="${appointment.date}" /> </td>
            <td align="right">${appointment.length}</td>
            <td align="right">${appointment.doctorId}</td>
            <td>${appointment.prescription}</td>
            <td>${appointment.diagnosis}</td>
        </tr>
    </c:forEach>
</table>
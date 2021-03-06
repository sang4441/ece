<%@ page import="com.springapp.mvc.model.Doctor" %>
<%@ page import="com.springapp.mvc.model.Patient" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2>${user.nameFirst}'s Dashboard (Doctor)</h2>

<table>
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Default Doc</td>
        <td>Health Card</td>
        <td>SIN</td>
        <td>Current Health</td>
        <td>Patient Record</td>
    </tr>
    <c:forEach items="${patients}" var="patient" >
        <tr>
            <td>${patient.id}</td>
            <td>${patient.nameLast}, ${patient.nameFirst}</td>
            <td>${patient.defaultDoc}</td>
            <td>${patient.healthCard}</td>
            <td>${patient.SIN}</td>
            <td>${patient.currentHealth}</td>
            <td><a href="/ece/doctor/patient/${patient.id}"><button class="btn btn-default">view patient's record</button></a></td>
        </tr>
    </c:forEach>
</table>
<%@ page import="com.springapp.mvc.model.Doctor" %>
<%@ page import="com.springapp.mvc.model.Patient" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2>${user.nameFirst}'s Dashboard (Doctor)</h2>

<a href="/ece/patient/profile/${user.id}"><button>view my profile</button></a>
<table>
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Time</td>
        <td>Default Doc</td>
        <td>Health Card</td>
        <td>SIN</td>
        <td>Current Health</td>
    </tr>
    <c:forEach items="${patients}" var="patient" >
        <tr>
            <td>${patient.id}</td>
            <td>${patient.person.nameLast}, ${patient.person.nameFirst}</td>
            <td>${patient.defaultDoc}</td>
            <td>${patient.healthCard}</td>
            <td>${patient.SIN}</td>
            <td>${patient.currentHealth}</td>
        </tr>
    </c:forEach>
</table>
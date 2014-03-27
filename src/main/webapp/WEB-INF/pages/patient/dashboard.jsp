<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>${user.nameFirst}'s Dashboard (PATIENT)</h2>
<a href="/ece/patient/profile"><button>view my profile</button></a>
<table>
    <tr>
        <td>Id</td>
        <td>Date</td>
        <td>Prescription</td>
        <td>Diagnosis</td>
    </tr>
    <c:forEach items="${appointments}" var="appointment" >
        <tr>
            <td>${appointment.id}</td>
            <td>${appointment.date}</td>
            <td>${appointment.prescription}</td>
            <td>${appointment.diagnosis}</td>
        </tr>
    </c:forEach>
</table>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Records for ${patientName}</h1>
<table>
    <tr>
        <th>id</th>
        <th>Doctor Name</th>
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
            <td>${visit.id}</td>
            <td>${visit.doctorId}</td>
            <td>${visit.date}</td>
            <td>${visit.dateCode}</td>
            <td>${visit.length}</td>
            <td>${visit.prescription}</td>
            <td>${visit.diagnosis}</td>
            <td>${visit.comment}</td>
            <td>${visit.date_modified}</td>
            <td>${visit.initialID}</td>
        </tr>
    </c:forEach>
</table>
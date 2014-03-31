<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<a href="/ece/doctor/dashboard"><button>go back to doctor dashboard</button></a>

<form method="post" onsubmit="return checkPassword(this)" action="/ece/patient/edit_profile_action" class="search">
    <h1>Search</h1>
    <label>Visited On:</label><input type="date" name="date" value="${date}" /><br/>
    <label>Patient Name</label><input type="text" name="patientName" value="${patientName}"/><br/>
    <label>Diagnosis</label><input type="text" name="diagnosis" value="${diagnosis}" /><br/>
    <label>Comment</label><input type="text" name="comment" value="${comment}" /><br/>
    <label>Prescription</label><input type="text" name="prescription" value="${prescription}" /><br/>
    <label>Surgery</label><input type="text" name="surgery" value="${surgery}"/><br/>
    <label>Search By</label>
    <select name="searchJoin">
        <option value="and">AND</option>
        <option value="or">OR</option>
    </select><br/>
    <button type="submit">Search</button>
</form>

<h1>Visit Results</h1>
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

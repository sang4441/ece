<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2>${doctor.nameFirst} ${doctor.nameLast}</h2>

<table border="1">
    <tr>
        <td># of visit</td>
        <td>Patient Name</td>
        <td>Date</td>
        <td>Time</td>
        <td>Length</td>
    </tr>
    <c:forEach items="${records}" var="record" >
        <tr>
            <td>${record.numVisit}</td>
            <td>${record.patientName}</td>
            <td> <fmt:formatDate type="date" dateStyle="long" value="${record.date}" /> </td>
            <td> <fmt:formatDate type="time" timeStyle="short" value="${record.date}" /> </td>
            <td> 30m</td>
        </tr>
    </c:forEach>
</table>




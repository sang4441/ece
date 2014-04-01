<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2>${user.nameFirst}'s History</h2>

<table border="1">
    <tr>
        <th></th>
        <th style="padding:5">Date</th>
        <th style="padding:5">Time</th>
        <th style="padding:5">Doctor</th>
        <th style="padding:5">Prescription</th>
        <th style="padding:5">Diagnosis</th>
    </tr>
    <c:forEach items="${visits}" var="visit" varStatus="counter">
        <tr>
            <td align="right" style="padding:5">${counter.count}</td>
            <td style="padding:5"> <fmt:formatDate type="date" dateStyle="long" value="${visit.date}" /> </td>
            <td style="padding:5"> <fmt:formatDate type="time" timeStyle="short" value="${visit.date}" /> </td>
            <td align="right" style="padding:5">${visit.doctorName}</td>
            <td style="padding:5">${visit.prescription}</td>
            <td style="padding:5">${visit.diagnosis}</td>
        </tr>
    </c:forEach>
</table>
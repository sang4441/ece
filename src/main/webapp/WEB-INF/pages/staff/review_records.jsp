<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2>See Patient Records for "${doctor.nameFirst} ${doctor.nameLast}"</h2>

<table>
    <tr>
        <td>Patient Name</td>
        <td>Date</td>
        <td>Time</td>
        <td>Length</td>
    </tr>
<c:forEach items="${records}" var="record" >
	<tr>
    	<td>${record.patientName}</td>
    	<td> <fmt:formatDate type="date" dateStyle="long" value="${record.date}" /> </td>
    	<td> <fmt:formatDate type="time" timeStyle="short" value="${record.date}" /> </td>
    	<td>${record.length}</td>
    </tr>
</c:forEach>
</table>




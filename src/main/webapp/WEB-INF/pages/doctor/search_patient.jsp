<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Search Patient</h2>
<form method="post" action="search_patient">
    Search patient: <select name="searchCriteria">
    	<option value="">Select a search criteria</option>
    	<option value="patientName">Patient Name</option>
    	<option value="patientId">Patient ID</option>
    	<option value="LastVisit">Last Visit Date</option>
    </select>   <input type="text" name="keyword"/> (leave it empty to find ALL)
</form>
<ol>
<c:forEach items="${patients}" var="patient" >
    <li><div><a href="/ece/doctor/patient/${patient.id}">${patient.nameFirst} ${patient.nameLast}</a></div></li>
</c:forEach>
</ol>

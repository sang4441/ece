<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Update Patient</h2>
<form method="post" action="searchPatient">
    Search patient: <input type="text" name="keyword"/>
</form>
<c:forEach items="${patients}" var="patient" >
    <a href="/ece/patient/edit_profile/${patient.personId}"><div>${patient.nameFirst} ${patient.nameLast}</div></a>
</c:forEach>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Revoke Permission</h1>

<ol>
    <c:forEach items="${doctors}" var="doctor" >
        <li><a href="deletePatientDoctor/${doctor.id}">${doctor.nameFirst} ${doctor.nameLast}</a></li>
    </c:forEach>
</ol>


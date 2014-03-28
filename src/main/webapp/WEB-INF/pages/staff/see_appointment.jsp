<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>See appointment</h2>
<form method="post" action="see_appointment">
    Search patient: <input type="text" name="keyword"/>
</form>
<c:forEach items="${patients}" var="patient" >
    <a href="create_appointment_form_2/${patient.personId}"><div>${patient.nameFirst} ${patient.nameLast}</div></a>
</c:forEach>
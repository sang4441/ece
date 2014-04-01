<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Grant Permission</h1>

<form method="post" action="search_doctor">
    Search doctor: <input type="text" name="keyword"/> (leave it empty to find ALL)
</form>

<ol>
<c:forEach items="${doctors}" var="doctor" >
    	<li><a href="insertPatientDoctor/${doctor.id}">${doctor.nameFirst} ${doctor.nameLast}</a></li>
</c:forEach>
</ol>


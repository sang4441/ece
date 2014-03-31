<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Review Patient Records for a doctor</h2>
<c:forEach items="${doctors}" var="doctor">
    <a href="/ece/staff/review_records/${doctor.id}"><div>${doctor.nameFirst} ${doctor.nameLast}</div></a>
</c:forEach>

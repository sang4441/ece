<%@ page import="com.springapp.mvc.model.Doctor" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2>Patient Dashboard for ${user.nameFirst}</h2>

<table border="1">
    <tr>
        <th style="padding:5"></th>
        <th style="padding:5">Date</th>
        <th style="padding:5">Time</th>
        <th style="padding:5">Doctor</th>
    </tr>
    <c:forEach items="${appointments}" var="appointment" varStatus="counter">
        <tr>
            <td align="right" style="padding:5">${counter.count}</td>
            <td style="padding:5"> <fmt:formatDate type="date" dateStyle="long" value="${appointment.date}" /> </td>
            <td style="padding:5"> <fmt:formatDate type="time" timeStyle="short" value="${appointment.date}" /> </td>
            <td align="right" style="padding:5">${appointment.doctorName}</td>
        </tr>
    </c:forEach>
</table>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<a href="/ece/doctor/dashboard"><button>go back to doctor dashboard</button></a>

<form method="post" action="/ece/doctor/appointment/${visit.id}">
    <h1>Appointment</h1>
    <label>Patient Name</label>${visit.patientName}<br/>
    <label>Date</label>${visit.date}<br/>
    <label>Length</label>${visit.length }<br/>
    <label>Prescription</label><form:input path="visit.prescription" /><br/>
    <label>Diagnosis</label><form:input path="visit.diagnosis" /><br/>
    <label>Surgery</label><form:input path="visit.surgery" /><br/>
    <label>Comment</label><form:input path="visit.comment" /><br/>
    <button type="submit">Submit</button>
    <form:hidden path="visit.id"/>
    <form:hidden path="visit.patientId" />
    <form:hidden path="visit.date" />
    <form:hidden path="visit.length" />
    <form:hidden path="visit.parentID" />
    <form:hidden path="visit.doctorId" />
    <form:hidden path="visit.initialID" />
    <form:hidden path="visit.dateModified" />
</form>

<h1>Past Visits</h1>
<table>
    <tr>
        <th>Visit ID</th>
        <th>Doctor Name</th>
        <th>Date</th>
        <th>Date Code</th>
        <th>Length (min)</th>
        <th>Prescription</th>
        <th>Diagnosis</th>
        <th>Comment</th>
        <th>Date Modified</th>
        <th>Initial ID</th>
    </tr>
    <c:forEach items="${visits}" var="visit" >
        <tr class="${visit.id == visit.initialID ? 'parent' : 'child'}">
            <td><a href="/ece/doctor/appointment/${visit.id}/edit" title='edit appointment'>${visit.id}</a></td>
            <td>${visit.doctorName}</td>
            <td><fmt:formatDate pattern="yyyy-MM-dd" value="${visit.date}"/></td>
            <td>${visit.dateCode}</td>
            <td>${visit.length}</td>
            <td>${visit.prescription}</td>
            <td>${visit.diagnosis}</td>
            <td>${visit.comment}</td>
            <td>${visit.dateModified}</td>
            <td>${visit.initialID}</td>
        </tr>
    </c:forEach>
</table>

<script type="text/javascript">
//window.onload = function() {
function omit() {
    var visits = document.querySelectorAll("tr.parent");
    for(i in visits) {
        if(!visits[i].nodeType) { continue; }
        visits[i].onclick = trClickHandler;
        visits[i].classList.add("closed");
        collapseChildrenOf(visits[i]);      
    }
}

function trClickHandler(event) {
    if(this.classList.contains("child")) { return; }
    if(this.classList.contains("closed")) {
        this.classList.remove("closed");
        expandChildrenOf(this);
    } else {
        this.classList.add("closed");
        collapseChildrenOf(this);
    }
}

function collapseChildrenOf(parent) {
    var next = parent.nextElementSibling;
    while(isChild(next)) {
        next.classList.remove("expand");
        next.classList.add("collapse");
    }
}

function expandChildrenOf(parent) {
    var next = parent.nextElementSibling;
    while(isChild(next)) {
        next.classList.remove("collapse");
        next.classList.add("expand");
    }
}

function isChild(node) {
    if(!node) { return false; }
    if(!node.hasAttributes()) { return false; } 
    return node.classList.contains("child");
}
</script>

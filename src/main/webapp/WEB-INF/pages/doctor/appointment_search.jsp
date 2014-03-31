<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<a href="/ece/doctor/dashboard"><button>go back to doctor dashboard</button></a>

<form method="post" action="" class="search">
    <h1>Search</h1>
    <label>Visited On</label><input type="date" name="date" value="${date}" /><br/>
    <label>Patient Name</label><input type="text" name="patientName" value="${patientName}"/><br/>
    <label>Diagnosis</label><input type="text" name="diagnosis" value="${diagnosis}" /><br/>
    <label>Comment</label><input type="text" name="comment" value="${comment}" /><br/>
    <label>Prescription</label><input type="text" name="prescription" value="${prescription}" /><br/>
    <label>Surgery</label><input type="text" name="surgery" value="${surgery}"/><br/>
    <button type="submit">Search</button>
</form>

<h1>Visit Results</h1>
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
            <td>${visit.id}</td>
            <td>${visit.doctorName}</td>
            <td><fmt:formatDate pattern="yyyy-MM-dd" value="${visit.date}"/></td>
            <td>${visit.dateCode}</td>
            <td>${visit.length}</td>
            <td>${visit.prescription}</td>
            <td>${visit.diagnosis}</td>
            <td>${visit.comment}</td>
            <td>${visit.date_modified}</td>
            <td>${visit.initialID}</td>
        </tr>
    </c:forEach>
</table>

<script type="text/javascript">
document.onload = function() {
	
    var visits = document.querySelectorAll("tr.parent");
    for(visit in visits) {
    	visit.classList.add("closed");
        collapseChildrenOf(visit);    	
    }
    
    visits.addEventListener('click', function() {
    	if(this.classList.contains("closed")) {
    		this.classList.remove("closed");
    		expandChildrenOf(this);
    	} else {
            this.classList.add("closed");
            collapseChildrenOf(this);
    	}
    })
}

function collapseChildrenOf(parent) {
    var next = parent.nextSibling();
    while(isChild(next)) {
    	next.classList.remove("expand");
    	next.classList.add("collapse");
    }
}

function expandChildrenOf(parent) {
	var next = parent.nextSibling();
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

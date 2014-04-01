<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Patient profile</h2>

<c:choose>
    <c:when test="${role==1}">
        <a href="/ece/patient/dashboard"><button>Back to Dashboard</button></a>
        <a href="/ece/patient/edit_profile/${user.personId}"><button>Edit my profile</button></a>
    </c:when>
    <c:when test="${role==3}">
        <a href="/ece/staff/dashboard"><button>Back to staff dashboard</button></a>
        <a href="/ece/patient/edit_profile/${user.personId}"><button>Edit</button></a>
    </c:when>
    <c:otherwise>
        <a href="/ece/doctor/dashboard"><button>Back to doctor dashboard</button></a>
    </c:otherwise>
</c:choose>

<form method="post" action="patient_form_submit">
    <table>
        <tr>
            <td>First Name</td>
            <td>${user.nameFirst}</td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td>${user.nameLast}</td>
        </tr>
        <tr>
            <td> Phone Number</td>
            <td>${user.phone}</td>
        </tr>
        <c:choose>
            <c:when test="${role==1}">
                <tr>
                    <td>Username</td>
                    <td>${user.username}</td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td>**********</td>
                </tr>
            </c:when>
        </c:choose>
        <tr>
            <td>Street</td>
            <td>${user.street}</td>
        </tr>
        <tr>
            <td>City</td>
            <td>${user.city}</td>
        </tr>
        <tr>
            <td>Province</td>
            <td>${user.province}</td>
        </tr>
        <tr>
            <td>Postal Code</td>
            <td>${user.postalCode}</td>
        </tr>
        <tr>
            <td>Default Doctor</td>
            <td>${doctor}</td>
        </tr>
        <tr>
            <td>Health Card</td>
            <td>${user.healthCard}</td>
        </tr>
        <tr>
            <td>SIN</td>
            <td>${user.SIN}</td>
        </tr>
        <tr>
            <td>Current Health</td>
            <td>${user.currentHealth}</td>
        </tr>
    </table>
</form>

<h1>Visits</h1>
<table>
    <tr>
        <th>id</th>
        <th>Doctor Name</th>
        <th>Date</th>
        <th>Date Code</th>
        <th>Length (min)</th>
        <th>Prescription</th>
        <th>Diagnosis</th>
        <c:if test="${role==2}">
        <th>Comment</th>
        </c:if>
        <th>Date Modified</th>
        <th>Initial ID</th>
    </tr>
    <c:forEach items="${visits}" var="visit" >
        <tr>
            <td>${visit.id}</td>
            <td>${visit.doctorId}</td>
            <td>${visit.date}</td>
            <td>${visit.dateCode}</td>
            <td>${visit.length}</td>
            <td>${visit.prescription}</td>
            <td>${visit.diagnosis}</td>
            <c:if test="${role==2}">
            <td>${visit.comment}</td>
            </c:if>
            <td>${visit.date_modified}</td>
            <td>${visit.initialID}</td>
        </tr>
    </c:forEach>
</table>

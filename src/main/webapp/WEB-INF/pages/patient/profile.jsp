<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>${user.nameFirst}'s Profile</h2>



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

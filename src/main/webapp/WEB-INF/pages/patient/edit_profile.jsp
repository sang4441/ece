<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>edit profile</h2>
<a href="/ece/patient/profile"><button>back to my profile</button></a>
<form method="post" action="edit_profile_action">
    <table>
        <tr>
            <td>First Name</td>
            <td><input type="text" name="NameFirst" value="${user.nameFirst}"></td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><input type="text" name="NameLast" value="${user.nameLast}"></td>
        </tr>
        <tr>
            <td> Phone Number</td>
            <td><input type="text" name="Phone" value="${user.phone}"></td>
        </tr>
        <tr>
            <td>Username</td>
            <td><input type="text" name="Username" value="${user.username}"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" name="Password" value="${user.password}"></td>
        </tr>
        <tr>
            <td>Street</td>
            <td><input type="text" name="Street" value="${user.street}"></td>
        </tr>
        <tr>
            <td>City</td>
            <td><input type="text" name="City" value="${user.city}"></td>
        </tr>
        <tr>
            <td>Province</td>
            <td><input type="text" name="Province" value="${user.province}"></td>
        </tr>
        <tr>
            <td>Postal Code</td>
            <td><input type="text" name="PostalCode" value="${user.postalCode}"></td>
        </tr>
        <tr>
            <td>Default Doctor</td>
            <td><input type="text" name="defaultDoc" value="${user.defaultDoc}"></td>
        </tr>
        <tr>
            <td>Health Card</td>
            <td><input type="text" name="healthCard" value="${user.healthCard}"></td>
        </tr>
        <tr>
            <td>SIN</td>
            <td><input type="text" name="SIN" value="${user.SIN}"></td>
        </tr>
        <tr>
            <td> Current Health</td>
            <td><input type="text" name="currentHealth" value="${user.currentHealth}"></td>
        </tr>
    </table>
    <div><input type="submit" value="submit"></div>
</form>

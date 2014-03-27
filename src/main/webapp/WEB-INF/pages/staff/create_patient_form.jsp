<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>edit profile</h2>
<form method="post" action="patient_form_submit">
    <table>
        <tr>
            <td>First Name</td>
            <td><input type="text" name="NameFirst" ></td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><input type="text" name="NameLast" ></td>
        </tr>
        <tr>
            <td> Phone Number</td>
            <td><input type="text" name="Phone" ></td>
        </tr>
        <tr>
            <td>Username</td>
            <td><input type="text" name="Username" ></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" name="Password" ></td>
        </tr>
        <tr>
            <td>Street</td>
            <td><input type="text" name="Street"></td>
        </tr>
        <tr>
            <td>City</td>
            <td><input type="text" name="City" ></td>
        </tr>
        <tr>
            <td>Province</td>
            <td><input type="text" name="Province" ></td>
        </tr>
        <tr>
            <td>Postal Code</td>
            <td><input type="text" name="PostalCode" ></td>
        </tr>
        <tr>
            <td>Default Doctor</td>
            <td><input type="text" name="defaultDoc" ></td>
        </tr>
        <tr>
            <td>Health Card</td>
            <td><input type="text" name="healthCard" ></td>
        </tr>
        <tr>
            <td>SIN</td>
            <td><input type="text" name="SIN" ></td>
        </tr>
        <tr>
            <td> Current Health</td>
            <td><input type="text" name="currentHealth"></td>
        </tr>
    </table>
    <div><input type="submit" value="submit"></div>
</form>

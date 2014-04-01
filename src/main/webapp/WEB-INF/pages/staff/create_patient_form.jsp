<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>New Patient Information</h2>
<script type="text/javascript">
    function checkPassword(form)
    {
        if(form.pwd1.value != form.pwd2.value)
        {
            alert("Error: new password does not match");
            form.newPwd2.focus();
            return false;
        }else {
            return true;
        }
    }
</script>
<form method="post" onsubmit="return checkPassword(this)" action="patient_form_submit">
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
            <td><input type="text" name="Username" required></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input id="pwd1" type="password" name="Password" required></td>
        </tr>
        <tr>
            <td>Re-Enter</td>
            <td><input id="pwd2" type="password"></td>
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
            <td>
                <select type="number" name="defaultDoc" min="1" required>
                    <c:forEach items="${doctors}" var="doctor" >
                        <option value="${doctor.id}">Dr. ${doctor.nameLast}</option>
                    </c:forEach>
                </select>
            </td>
            <%--<td><input type="number" name="defaultDoc" min="1" required></td>--%>
        </tr>
        <tr>
            <td>Health Card</td>
            <td><input type="text" name="healthCard" ></td>
        </tr>
        <tr>
            <td>SIN</td>
            <td><input type="number" name="SIN" min="1" required></td>
        </tr>
        <tr>
            <td> Current Health</td>
            <td><input type="text" name="currentHealth"></td>
        </tr>
    </table>
    <div><input class="btn btn-default" type="submit" value="submit"></div>
</form>

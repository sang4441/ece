<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>edit profile</h2>
<c:choose>
    <c:when test="${role==3}">
        <a href="/ece/staff/dashboard"><button>go back to staff dashboard</button></a>
    </c:when>
    <c:when test="${role==1}">
        <a href="/ece/patient/profile/${user.personId}"><button>back to my profile</button></a>
    </c:when>
    <c:otherwise>
        <a href="/ece/doctor/dashboard"><button>go back to doctor dashboard</button></a>
    </c:otherwise>
</c:choose>

<script type="text/javascript">
    function checkPassword(form)
    {
        if (${role} == 3)
        {
            alert("Patient information has been changed");
            return true;
        }

        if(form.pwd.value == "")
        {
            alert("Error: please enter the current password");
            form.pwd.focus();
            return false;
        }
        else if(form.pwd.value != ${user.password})
        {
            alert("Error: current password is wrong");
            form.pwd.focus();
            return false;
        }
        else if(form.newPwd1.value == "" && form.newPwd2.value == "")
        {
            return true;
        }
        else if(form.newPwd1.value != form.newPwd2.value)
        {
            alert("Error: new password does not match");
            form.newPwd2.focus();
            return false;
        }
        else {
            form.pwd.name = "";
            form.newPwd2.name = "Password";
            alert("Password has been changed");
            return true;
        }
    }

</script>
<form method="post" onsubmit="return checkPassword(this)" action="/ece/patient/edit_profile_action">
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
            <td><input id="pwd" type="password" name="Password"></td>
        </tr>
        <tr>
            <td>New Password</td>
            <td><input id="newPwd1" type="password"></td>
        </tr>
        <tr>
            <td>Re-Enter</td>
            <td><input id="newPwd2" type="password"></td>
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
            <td>
                <select name="defaultDoc">
                    <c:forEach items="${doctors}" var="doctor">
                        <option value="${doctor.id}" ${doctor.id == user.defaultDoc ? "selected":""}> ${doctor.nameFirst} ${doctor.nameLast}</option>
                    </c:forEach>
                </select>
            </td>
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
    <input type="hidden" name="personId" value="${user.personId}"></input>
    <div><input type="submit" value="submit"></div>
</form>

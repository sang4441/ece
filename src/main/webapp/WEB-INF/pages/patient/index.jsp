<html xmlns:th="http://www.thymeleaf.org">
<body>
	<h1>Patients</h1>
	<table>
	   <tbody>
	       <tr th:each="patient : ${patients}">
	           <td th:text="${patient.personId}">a</td>
	       </tr>
	   </tbody>
	</table>
</body>
</html>
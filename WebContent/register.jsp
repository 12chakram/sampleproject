<%@page import="com.eng.gp.project.domain.ProjectType"%>
<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta name="decorator" content="app" />
<title>Alarm Library</title>

<%@ page isELIgnored="false"%>
<%@ page import="java.util.List"%>



<link rel="stylesheet" href="jquery-ui.css" />
<link rel="stylesheet" href="bootstrap.css" />

<script src="jquery-ui-1.9.2.custom.min.js"></script>
<script src="jquery-ui.js"></script>

</head>
<body>
<div>
	<form name='userRegistration' action="RegistrationController" method="post">
	<div style="margin-top: 90px; margin-left: 30px;">
	<table>
		<tbody>
			<tr>
			<td><label for="name">UserName:</label></td>
				<td><input type="text" name="userNsame" id="name"
					class="text ui-widget-content ui-corner-all" required="required" />
			</td>
			</tr>
			<tr>
			<td><label for="role">Select user role:</label></td>
			<td>
			<select name="userRole" class="text ui-widget-content ui-corner-all" required="required" id="role">
				<option value=""><strong>Select a role:</strong></option>
				<c:forEach var="roles" items="${roles}">
                   	<option value=${roles.roleId}>${roles.name}</option>
                </c:forEach>
			 </select>
		</td>
		</tr>
		<tr>
			<td><label for="password">Password:</label></td>
			<td>
			  <input type="password" name="password" id="password">
			 </td>
			</tr>
			<tr>
				<td><label for="confirmpw">ConfrimPassword</label></td>
				<td>
					<input type="password" name="confirmpw" id="confirmpw">
				</td>
			</tr>
			<tr>
				<td><label for="email">Email</label></td>
				<td>
					<input type="email" name="email" id="email">
				</td>
			</tr>
			<tr>
				<td><label for="fname">FirstName</label></td>
				<td>
					<input type="text" name="fname" id="fname">
				</td>
			</tr>
			<tr>
				<td><label for="lname">LastName</label></td>
				<td>
					<input type="text" name="lname" id="lname">
				</td>
			</tr>
			<tr>
				<td><label for="language">Language</label></td>
				<td>
					<select name="language" class="text ui-widget-content ui-corner-all" required="required" id="language">
				<option value=""><strong>Select Language:</strong></option>
				<option value="en_US"><strong>en_US</strong></option>
			 </select>
				</td>
			</tr>
			<tr>
				<td><label for="measurementSystem">Language</label></td>
				<td>
					<select name="measurementSystem" class="text ui-widget-content ui-corner-all" required="required" id="measurementSystem">
				<option value=""><strong>Select MeasurementSystem:</strong></option>
				<option value="METRIC"><strong>METRIC</strong></option>
				<option value="US_STANDARD"><strong>US_STANDARD</strong></option>
				<option value="NONE"><strong>NONE</strong></option>
			 </select>
				</td>
			</tr>
			</tbody>
			</table> 
		</div>
			<div style="margin-left:200px;">
				 <input type="submit" value="Register"/> 
			</div>
		</form>
</div>
</body>
</html>
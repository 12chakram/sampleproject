<%@page import="com.eng.gp.project.domain.ProjectTrackingItem"%>
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

<title>All Projects........</title>
</head>
<body>
  <form name='f'>
   
   <c:forEach var="school" items="${allProjects}">
    <option value="<c:out value='${school.projectName}'/>"> <c:out value='${school.projectName}'/>
    </option>
</c:forEach>
</form>
</body>
</html>
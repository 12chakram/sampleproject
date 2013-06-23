<%@page import="com.eng.gp.project.domain.ProjectTrackingItem"%>
<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
 <link type = "text/css" href = "css/avilablesites.css" rel ="stylesheet" media ="screen"  />
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
	<body><p>AVIALABLE Projects FOR U R SEARCH</p>
			     
				<table border="1">
				 	<tr>
                   		<th>ProjectId</th>
						<th>ProjectName</th>
						<th>ProjectType</th>
						<th>PremisesId</th>
						<th>StartDate</th>
						<th>EndDate</th>
						<th>Channels</th>
						<th>Status</th>
                  </tr>
                  <c:forEach var="project" items="${allProjects}"> 
                 <tr style="background-color: ;">	
					<td>${project.projectId}</td>
                  	<td>${project.projectName}</td>
                  	<td>${project.projectTypeId}</td>
                  	<td>${project.premisesId}</td>
                  	<td>${project.startDate}</td>
                  	<td>${project.endDate}</td>
                  	<td>${project.channels}</td>
                  	<td>${project.projectStatus}</td>
                  </tr>
                </c:forEach>
             </table>
      
</html>
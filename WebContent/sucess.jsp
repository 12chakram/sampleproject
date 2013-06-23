<!DOCTYPE html>
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
  <form name='f' action="getAllProjects" method="get">
  		<input type="submit" value="getAllProjects">
  
  </form>
</body>
</html>
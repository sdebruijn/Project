<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Teammenu</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h2>${team.name }</h2>
		<div class="btn-group-vertical">
			<a href="<c:url value="/team/${team.id}/manage" />"><button type="button" class="btn btn-primary btn-block">Team management</button></a> 
			<a href="<c:url value="/events" />"><button	type="button" class="btn btn-primary btn-block">Events</button></a> 
			<a href="<c:url value="/mainMenu" />"><button type="button"	class="btn btn-default btn-block">Back</button></a>
		</div>
	</div>
</body>
</html>
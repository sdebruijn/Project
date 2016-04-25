<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Mainmenu</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h2>Welkom username</h2>
		<div class="btn-group-vertical">
			<a href="user/new"><button type="button" class="btn btn-primary"> New user</button></a> <br /> 
			<a href="newTeam"><button type="button"	class="btn btn-primary btn-block">New team</button></a>



			<c:forEach items="${teams}" var="i">
				<a href="<c:url value="/teammenu/${i.id}" />"><button
						type="button" class="btn btn-primary btn-block">${i.name }</button></a>
			</c:forEach>

		</div>
	</div>
</body>
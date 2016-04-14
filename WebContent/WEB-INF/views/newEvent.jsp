<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html">
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<title>Create new event</title>
</head>
<body>
<div class="container">
  <h2>Create a new team</h2>
  <form:form commandname="event">
	<div class="form-group">
  		<label for="teamName">Title:</label>
  		<input type="text" class="form-control" name="teamName">
	</div>
	<div class="form-group">
  		<label for="sport">Description:</label>
  		<input type="text" class="form-control" name="sport">
	</div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form:form>
</div>
</body>
</html>
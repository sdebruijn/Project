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
  
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />
 <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
  
	<title>Create new event</title>
</head>
<body>
<div class="container">
  <h2>Create a new event</h2>
  <form:form commandName="defaultEvent">
	<div class="form-group">
  		<label for="title">Title:</label>
  		<form:input path="title" class="form-control" />
  		<form:errors path= "title"></form:errors>
	</div>
	<div class="form-group">
  		<label for="description">Description:</label>
  		<form:input path="description" class="form-control" />
  		<form:errors path="description"></form:errors>
	</div>
	<div class="form-group">
        <label for="date">Date:</label>
            <div class="input-group input-append date" id="datePicker">
                <form:input path="date" class="form-control" type="text" />
                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                <form:errors path="date"></form:errors>
            </div>
    </div>
	
  <button type="submit" class="btn btn-primary">Submit</button>
</form:form>
</div>

<script>
$(document).ready(function() {
    $('#datePicker')
        .datepicker({
            format: 'yyyy-mm-dd'
        })
        .on('changeDate');});
</script>

</body>
</html>
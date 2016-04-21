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
 <link rel="stylesheet" type="text/css" href="https://dist/bootstrap-clockpicker.min.css">
 <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
 <script type="text/javascript" src="https://dist/bootstrap-clockpicker.min.js"></script>
  
	<title>Create new event</title>
</head>
<body>
<div class="container">
  <h2>Create a new event</h2>
  <form:form commandName="event">
  	<div class="form-group">
  		<label for="eventtype">Type:</label>
  		<br><form:radiobutton path="type" value="Match" onclick="getRadioValue(this.id)" /> Match 
		<br><form:radiobutton path="type" value="Training" onclick="getRadioValue(this.id)" /> Training
		<br><form:radiobutton path="type" value="Party" onclick="getRadioValue(this.id)" /> Party
		<form:errors path="type"><br>Selecteer een type</form:errors>
  	</div>
	<div class="form-group">
  		<label for="title">Title:</label>
  		<form:input path="title" class="form-control" />
  		<form:errors path= "title">Deze moet ingevuld worden!</form:errors>
	</div>
	<div class="form-group">
  		<label for="description">Description:</label>
  		<form:input path="description" class="form-control" />
  		<form:errors path="description"></form:errors>
	</div>
	<div class="form-group">
  		<label for="location">Location:</label>
  		<form:input path="location" class="form-control" id="location" />
  		<form:errors path="location"></form:errors>
	</div>
	<div class="form-group" id="trainer">
  		<label for="trainer">Trainer:</label>
  		<form:input path="trainer" class="form-control" />
  		<form:errors path="trainer"></form:errors>
	</div>	
	<div class="form-group">
        <label for="date">Date:</label>
            <div class="input-group input-append date" id="datePicker">
                <form:input path="date" class="form-control" type="text" />
                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"><br></span></span>
            </div>
            <form:errors path="date">Onjuiste datum. Klik op een geldige datum.</form:errors>
    </div>
    <div class="form-group">
    	<label for="starttime">Start:</label>
    		<div class="input-group clockpicker">
    			<form:input path="starttime" type="text" class="form-control" value="09:30" />
    			<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
    		</div>	
		<form:errors path="starttime">Onjuiste datum. Klik op een geldige datum.</form:errors>
    </div>
    <div class="form-group" style="display:none" id="teams">
  		<label for="homeTeam">Home team:</label>
  		<form:input path="homeTeam" class="form-control" id="hometeam"/>
  		<form:errors path="homeTeam"></form:errors>
  		<label for="awayTeam">Away Team:</label>
  		<form:input path="awayTeam" class="form-control" />
  		<form:errors path="awayTeam"></form:errors>
	</div>
    <div class="form-group" style="display:none" id="scores">
  		<label for="score">Score:</label>
  		<form:input path="scoreHome" class="form-control" />
  		<form:errors path="scoreHome"></form:errors>
  		<form:input path="scoreAway" class="form-control" />
  		<form:errors path="scoreAway"></form:errors>
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

$(document).ready(function() {
	$('.clockpicker').clockpicker().on('changeTime');});

function getRadioValue(id) {
	var radioBtn = document.getElementById(id).value;
		console.log(radioBtn);
		if (radioBtn == "Match"){
			$("#scores").show(); 
			$("#teams").show(); 
			$("#trainer").hide(); 
			$("#location").attr('value', '')
			$("#hometeam").attr('value', '${team.name}')
			}
		if (radioBtn == "Training"){
			$("#trainer").show(); 
			$("#scores").hide(); 
			$("#teams").hide(); 
			$("#location").attr('value', 'Thuis')
			}
		if (radioBtn == "Party"){
			$("#scores").hide(); 
			$("#teams").hide();  
			$("#trainer").hide(); 
			$("#location").attr('value', '')
			}
	}
	

</script>


</body>
</html>
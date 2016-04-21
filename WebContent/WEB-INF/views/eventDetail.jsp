<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>{event.title}</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
<<<<<<< HEAD
  	<h1>${event.title}</h1>
  	<p>Date: ${event.date}</p>
  	<p>Location: ${event.location}</p>

  	<input type="radio" name="pres" id="present" value="Present" />
  	<label for="present">Present</label>
  	<input type="radio" name="pres" id="absent" value="absent" />
  	<label for="absent">absent</label>

  	<p>Present:
   	<c:forEach items="${present}" var="i">
    ${i.surname} ${i.name},
    </c:forEach></p>
  	 
  	<p>Absent:  
  	<c:forEach items="${absent}" var="i">
    ${i.surname} ${i.name},
    </c:forEach></p>
    
    <div class="btn-group-vertical">
    	<a href="<c:url value="" />"><button type="button" class="btn btn-default btn-block">Back</button></a> 
    </div>
  	<script>

  	
=======
  	<p>${event.title}</p>
  	<p>Date: ${event.date}</p>
  	<p>Location: ${event.location}</p>

  	<input type="radio" name="present" id="present" value="Present" />
  	<label for="present">Present</label>
  	<input type="radio" name="absent" id="absent" value="absent" />
  	<label for="absent">absent</label>

  	<p>Present:</p>
  	
<%--   	<c:forEach items="${event.present}" var="i">
    <li>${i.surname} ${i.name}</li>
    </c:forEach>
  	 --%>
  	<p>Absent:</p>
  	
  	<script>
>>>>>>> 651e66823299e807d7e66dd350bf7f074c3da588
  	window.onload = function() {

    	var pres = document.getElementById('present');
    	var absa = document.getElementById('absent');
<<<<<<< HEAD
    	
      	pres.checked = ${p}; 
   		absa.checked = ${a};  
    	
    	pres.onclick = present;
    	absa.onclick = absent;
    	
=======
    	pres.onclick = present;
    	absa.onclick = absent;
>>>>>>> 651e66823299e807d7e66dd350bf7f074c3da588

    	function present() {
    		  window.location.assign("/project/events/present");
        }
    	
    	function absent() {
  		  window.location.assign("/project/events/absent");
<<<<<<< HEAD
      	}
=======
      }
>>>>>>> 651e66823299e807d7e66dd350bf7f074c3da588
    }

  	</script>
</div>
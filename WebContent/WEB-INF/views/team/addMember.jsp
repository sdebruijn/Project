<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html">
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<sec:csrfMetaTags />
  <title>Add member</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
  
  <script>
	// configureer JQuery om csrf-token mee te sturen
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	$(function () {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});
	console.log("CSRF Param:  "+csrfParameter);
	console.log("CSRF Token:  "+csrfToken);
	console.log("CSRF Header: "+csrfHeader);
  
  $(document).ready(function(){
		$('input[type=submit]').click(submitted);
	})	
	

	function submitted(){

		  $('input[type=checkbox]').each(function () {
			  if (this.checked){
				  var id = this.value;
				  $.post("addmember", {id: id}, function(data){
					  console.log(data);
					  window.location.reload();
				  });
			  }  
			});
		  
		 
	  
  };
  </script>
  
</head>
<body>
<div class="container">
  <div class="btn-group-vertical">
  		<c:forEach items="${users }" var="i">
  		<div class="checkbox">
    		<input type="checkbox" class="boxes" value="${i.id}">${i.name} ${i.surname }<br>
   		</div>
    	</c:forEach>
    	<input type="submit" value="Submit" class="btn btn-info" />
    	<a href="<c:url value="/team/${team.id}/manage" />"><button type="button" class="btn btn-default btn-block">Back</button></a> 
  </div>
</div>
</body>
</html>
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

	var root_url = "/project/";
	
	function get_user_data() {
		var user = {
				name:    $('#username').val(),
				surname: $('#usersurname').val(),
			};
		return user;
	}
	
	
	function login(){
		var user = get_user_data(); 
		empty_form();
		
		 $.ajax( {
	            "contentType": 'application/json', 
	            "method": "POST", 
	            "url": root_url + 'login', 
	            "data": JSON.stringify(user),
	            "success": function(data){
	            	console.log('succes'); 
	            	console.log(data);
	            	add_user_to_list(data);
	            	}
	        } );
	}

	
	$(document).ready(function(){
		console.log("Page ready");
		$('input[type=submit]').click(login);
		get_all();
	})	
	
	</script>




<body>
<h2>Login</h2>



	<div class="container">
<div>
	<form:form commandName="user">
		Name: 
		<form:input type="text" path="name"/><br /> 
		Surname: 
		<form:input	type="text" path="surname"/><br> 
		<input type="submit" value="Login" />
		</form:form>
</div>
<ul>
		<c:forEach items="${users}" var="user">
		<li> 
		<a>
		
		</a>
		
		</li>
		
		</c:forEach>
		</ul>

	</div>
</body>
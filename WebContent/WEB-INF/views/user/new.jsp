<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html">
<html>
<head>
<sec:csrfMetaTags />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<!-- <script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script> -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script>
	// configureer JQuery om csrf-token mee te sturen
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	$(function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});
	console.log("CSRF Param:  " + csrfParameter);
	console.log("CSRF Token:  " + csrfToken);
	console.log("CSRF Header: " + csrfHeader);

	var root_url = "/project/api/users/";

	function get_user_data() {
		var user = {
			name : $('#name').val().trim(),
			surname : $('#surname').val().trim(),
		};
		return user;
	}

	function send_ajax() {
		var user = get_user_data();
		user.name = user.name.trim();
		if (user.name.length < 2) {
			// make error message
			console.log('user name empty');
			$('#fe-name').text('Enter a valid first name (2 to 45 characters)');
			$("#submit").prop('disabled', true);
			return;
		} else {
			$('#fe-name').text('');
		}

		if (user.surname.length < 2) {
			// make error message
			console.log('user surname empty');
			$('#fe-surname').text(
					'Enter a valid first last name (2 to 45 characters)');
			$("#submit").prop('disabled', true);
			return;
		} else {
			$('#fe-surname').text('');
		}

		$.ajax({
			"contentType" : 'application/json',
			"method" : "GET",
			"url" : root_url + 'exists/' + user.name + '/' + user.surname,
			"success" : function(data) {
				if (data === 'true') {
					$('#error').text('This name and surname is already recorded, please enter something else.');
				} else {
					$("#submit").prop('disabled', false);
				}
			}
		});
	}

	$(document).ready(function() {

		$('#myform').change(function() {
			send_ajax();
		})

	})
</script>

<title>Create new user</title>
</head>
<body>
	<div class="container">
		<h2>Create a new user</h2>

		<form:form commandName="user" id="myform">
			<%--<c:if test="${not empty error}">--%>
				<div id="error" style="color:red">${error}</div>
			<%--</c:if>--%>
			<div class="form-group">
				<label for="name">First name:</label>
				<form:input path="name" class="form-control" />
				<br /> <font color="red" class="form-error" id="fe-name"><form:errors
						path="name">Enter a valid first name (2 to 45 characters)</form:errors></font>
			</div>
			<div class="form-group">
				<label for="name">Last name:</label>
				<form:input path="surname" class="form-control" />
				<font color="red" class="form-error" id="fe-surname"><form:errors
						path="surname">Enter a valid last name (2 to 45 characters)</form:errors></font><br />
			</div>
			<div class="form-group">
				<label for="name">Age:</label>
				<form:input path="age" class="form-control" />
				<font color="red" class="form-error"><form:errors
						path="surname">Enter a valid age</form:errors></font><br />
			</div>
			<div class="form-group">
				<label for="name">Email:</label>
				<form:input path="email" class="form-control" />
				<font color="red" class="form-error"><form:errors
						path="surname">Enter a valid email address</form:errors></font><br />
			</div>

			<button type="submit" class="btn btn-primary" id="submit" disabled>Submit</button>
		</form:form>


	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<!doctype html>
<html>
<head>
	<!-- Deze tag is nodig voor verzenden van data met CSRF-protectie -->
	<sec:csrfMetaTags />
	<title>TESTING</title>	
	<h1>TESTING</h1>

	<script src="https://code.jquery.com/jquery-2.2.3.min.js"></script>
	
	
	<script type="text/javascript" language="javascript">
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
	
		var root_url = "/project/api/users/";
		
		function get_all(){
			$.get(root_url, function(data){
				$('#result').append(data);
				$('#result').append("GET_ALL<br />");
			});
		}
		
		function create(){
			var rit = {
				name: $('#name').val(),
				surname: $('#surname').val(),
			};
			$.post(root_url, rit, function(data){
				doSomething(data);
			});
		}
		
		function doSomething(data) {
			$('#resultCreate').append(data);
			$('#result').val("DOSOMETHING<br />");
		}
		
		function remove(){
			// called as an event handler, 'this' will be bound to element clicked
			var element = $(this);
			var id = 13;
			$.ajax(root_url + id, {method: 'DELETE', success: get_all});
			$('#result').val("REMOVE<br />");
		}
		
		$(document).ready(function(){
			$('.create').click(create);
			$('.delete').click(remove);
			get_all();
		})	
	</script>
</head>
<body>
	<ul>
		<!--  Lege lijst - te vullen dmv JS -->
	</ul>
	
	<!-- Let op! We MOETEN hier form:form gebruiken in plaats van een normale HTML form -->

		<p>
			Name: <input type="text" id="name"><br>
			Surname: <input type="text" id="surname"><br>
			<input type="submit" class="create" value="create">
		</p>
		
		<input type="submit" class="delete" value="delete">

<h3>Result</h3>
	<div id="result">
	</div>
	<h3>Result Create </h3>
	<div id="resultCreate">
	</div>
</body>
</html>
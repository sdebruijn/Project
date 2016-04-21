<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html>
<head>
<!-- Deze tag is nodig voor verzenden van data met CSRF-protectie -->
<sec:csrfMetaTags />
<title>TESTING</title>
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

	var root_url = "/project/api/users/";
	
	function add_user_to_list(user){
		var element = $('<li>User ' + user.id + ': ' + user.name + ' ' + user.surname +
						'<input type="button" value="verwijder" data-id="' + user.id+ '" class="deletebutton">'+
						'</li>');
		$('ul').append(element);
		// don't forget to bind click handler
		$('.deletebutton').click(remove);
	}
	
	function get_all(){
		$.get(root_url, function(data){
			for(var i=0; i < data.length; ++i){
				add_user_to_list(data[i]);
			}
		});
	}
	
	function get_user_data() {
		var user = {
				name:    $('#name').val(),
				surname: $('#surname').val(),
			};
		return user;
	}
	
	function empty_form() {
		$('#name').val('');
		$('#surname').val('');
	}
	
	function create(){
		var user = get_user_data(); 
		empty_form();
		
		 $.ajax( {
	            "contentType": 'application/json', 
	            "method": "POST", 
	            "url": root_url, 
	            "data": JSON.stringify(user),
	            "success": function(data){
	            	console.log('succes'); 
	            	console.log(data);
	            	add_user_to_list(data);
	            	}
	        } );
	}
	
	function change(user, id){
		 $.ajax( {
	            "contentType": 'application/json', 
	            "method": "PUT", 
	            "url": root_url + id, 
	            "data": JSON.stringify(user),
	            "success": function(data){
	            	console.log('succes'); 
	            	console.log(data)
	            	}
	        } );
	}
	
	function remove(){
		// called as an event handler, 'this' will be bound to element clicked
		var element = $(this);
		var id = element.data().id;
		$.ajax(root_url + id, {
			method: 'DELETE', 
			success: function(){
				element.parent().remove()
				}
		});
	}
	
	$(document).ready(function(){
		console.log("Page ready");
		$('input[type=submit]').click(create);
		get_all();
	})	
	
	</script>

</head>
<body>
<h1>TESTING</h1>
	<!-- Let op! We MOETEN hier form:form gebruiken in plaats van een normale HTML form -->
<h2>POST method</h2>
	<p>
		Name: 
		<input type="text" id="name"><br /> 
		Surname: 
		<input	type="text" id="surname"><br> 
		<input type="submit">
	</p>


	<h3>Result</h3>
	<ul>
	</ul>
</body>
</html>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en" ng-app="phonecatApp">
<head>
  <meta charset="utf-8">
  <sec:csrfMetaTags />
  <title>POLLL</title>
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
	</script>
  
  <link rel="stylesheet" href="resources/bootstrap.css">
  <link rel="stylesheet" href="resources/app.css">
  
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.min.js"></script>
  <script src="resources/spring-security-csrf-token-interceptor.min.js"></script>
  <script src="resources/controllers.js"></script>
  
  
</head>
<body ng-controller="pollCtrl">
<!-- <p>{{hallo}}</p>

        Sidebar content

        Title: <input ng-model="title"><br>
        Add option: <input ng-model="option">
        Sort by:
        <select ng-model="orderProp">
          <option value="name">Alphabetical</option>
          <option value="age">Newest</option>
        </select>

         <ul class="phones">
          <li ng-repeat="phone in phones | filter:title | orderBy:orderProp">
            {{phone}}
          </li>
        </ul> -->
		
Title of poll: <input ng-model="title"><br>


<form ng-submit="submit()" ng-controller="pollCtrl">
  Add options:
  <input type="text" ng-model="text" name="text" />
  <input type="submit" id="submit" value="Add" />
  
  <h2>{{title}}</h2>
  
  <ol class="phones">
  	<li ng-repeat="option in list">- {{option}}</li>
  </ol>
  
  <pre>list={{list}}</pre>
</form>

</body>
</html>

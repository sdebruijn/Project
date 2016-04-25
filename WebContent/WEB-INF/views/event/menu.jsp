<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Mainmenu</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <h2>Events</h2>
  <div class="btn-group-vertical">
    <a href="/project/events/new"><button type="button" class="btn btn-primary btn-block">New match</button></a>

    <c:forEach items="${events}" var="i">
    	<a href="<c:url value="events/${i.id}" />"><button type="button" class="btn btn-primary btn-block">${i.start} ${i.title}</button></a>
    </c:forEach>
    
   	<a href="<c:url value="/mainMenu" />"><button type="button"	class="btn btn-default btn-block">Main</button></a>
   	<a href="<c:url value="/team/${team.id}" />"><button type="button"	class="btn btn-default btn-block">Back to Team</button></a>
    
  </div>
</div>
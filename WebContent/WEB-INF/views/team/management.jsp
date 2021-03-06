<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Teammenu</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <h2>${team.name }</h2>

  <p>Sport: ${team.sport }</p>
  <p>Coach: ${team.coach.surname} ${team.coach.name}</p>
  <p>Members:</p>
  
  <c:forEach items="${team.members}" var="i">
    <li>${i.surname} ${i.name}</li>
    </c:forEach>
    
  <div class="btn-group-vertical">
  	<a href="<c:url value="/team/${team.id}/coach" />"><button type="button" class="btn btn-primary btn-block">Add coach</button></a>
    <a href="<c:url value="/team/${team.id}/members" />"><button type="button" class="btn btn-primary btn-block">Add member</button></a>
    <a href="<c:url value="/team/${team.id}/removeMember" />"><button type="button" class="btn btn-primary btn-block">Remove member</button></a>
    <a href="<c:url value="/team/${team.id}/removeMembers" />"><button type="button" class="btn btn-primary btn-block">Remove all</button></a>
    <a href="<c:url value="/team/${team.id}/remove" />"><button type="button" class="btn btn-primary btn-block">Remove team</button></a> 
    <a href="<c:url value="/team/${team.id}" />"><button type="button" class="btn btn-default btn-block">Back</button></a> 
  </div>
</div>
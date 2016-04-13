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
  <p>Members:</p>
  
  <c:forEach items="${users}" var="i">
    <li>${i.team}</li>
    </c:forEach>
    
  <div class="btn-group-vertical">
    <a href="<c:url value="/addmember/${team.id}" />"><button type="button" class="btn btn-primary">Add member</button></a>
    <button type="button" class="btn btn-primary">Remove member</button>
    <a href="<c:url value="/delete/${team.id}" />"><button type="button" class="btn btn-primary">Remove team</button></a> 
  </div>
</div>
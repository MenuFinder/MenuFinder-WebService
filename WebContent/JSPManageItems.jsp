<%@page import="java.sql.ResultSet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="database.RestaurantBean"%>
<%@page import="database.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu Finder - Manage Items</title>
</head>
<body>
<%
	long restaurantId =(long) session.getAttribute("restaurantid");
%>
	<form method="get" action="sManageItems">
		<input type="hidden" name="action" value="additem"> 
		<input type="hidden" name="restaurantid" value="<%=restaurantId%>">
		<input type="submit" value="Add item">
	</form>
	<c:forEach var="item" items="${items}">
		<h2>${item.name}</h2>
		<p>Description: ${item.description}</p>
		<p>Price: ${item.price}</p>
		<p>Score: ${item.score}</p>
		
		<form method="get" action="sManageItems">
			<input type="hidden" name="action" value="edititem"> 
			<input type="hidden" name="itemid" value="${item.id}">
			<input type="submit" value="Edit item">
		</form>
		<form method="post" action="sManageItems">
			<input type="hidden" name="action" value="deleteitem"> 
			<input type="hidden" name="itemid" value="${item.id}"> 
			<input type="hidden" name="name" value="${item.name}"> 
			<input type="hidden" name="description" value="${item.description}"> 
			<input type="hidden" name="price" value="${item.price}"> 
			<input type="hidden" name="score" value="${item.score}"> 
			<input type="hidden" name="restaurantid" value="${item.restaurant}">
			<input type="submit" value="Delete item">
		</form>

	</c:forEach>
</body>
</html>
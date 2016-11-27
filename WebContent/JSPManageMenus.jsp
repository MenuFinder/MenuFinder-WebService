<%@page import="java.sql.ResultSet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="database.RestaurantBean"%>
<%@page import="database.MenuBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu Finder - Manage Menu</title>
</head>
<body>
<%
	long restaurantId =(long) session.getAttribute("restaurantid");
%>
<form method="get" action="sManageMenus">
	<input type="hidden" name="action" value="addmenu"> 
	<input type="hidden" name="restaurantid" value="<%=restaurantId%>">
	<input type="submit" value="Add menu">
</form>
	<c:forEach var="menu" items="${menus}">
		<h2>${menu.name}</h2>
		<p>Description: ${menu.description}</p>
		<p>Price: ${menu.price}</p>
		<p>Score: ${menu.score}</p>
		
		<form method="get" action="sManageMenus">
			<input type="hidden" name="action" value="managemenuitems">
			<input type="hidden" name="menuid" value="${menu.id}">
			<input type="submit" value="Manage menu items">
		</form>
		<form method="get" action="sManageMenus">
			<input type="hidden" name="action" value="editmenu"> 
			<input type="hidden" name="menuid" value="${menu.id}">
			<input type="submit" value="Edit menu">
		</form>
		<form method="post" action="sManageMenus">
			<input type="hidden" name="action" value="deletemenu"> 
			<input type="hidden" name="menuid" value="${menu.id}"> 
			<input type="hidden" name="name" value="${menu.name}"> 
			<input type="hidden" name="description" value="${menu.description}"> 
			<input type="hidden" name="price" value="${menu.price}"> 
			<input type="hidden" name="score" value="${menu.score}"> 
			<input type="hidden" name="restaurantid" value="${menu.restaurant}">
			<input type="submit" value="Delete menu">
		</form>
	</c:forEach>
</body>
</html>
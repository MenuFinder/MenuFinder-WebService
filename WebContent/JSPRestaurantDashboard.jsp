<%@page import="java.sql.ResultSet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="database.RestaurantBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Menu Finder - Restaurant Dashboard</title>
</head>
<body>
	<c:forEach var="restaurant" items="${restaurants}">
		<h2>${restaurant.name}</h2>
		<p>CIF: ${restaurant.cif}</p>
		<p>Address: ${restaurant.address}</p>
		<p>City: ${restaurant.city}</p>
		<p>Postal code: ${restaurant.postalCode}</p>
		<p>State: ${restaurant.state}</p>
		<p>Country: ${restaurant.country}</p>
		<p>Email: ${restaurant.email}</p>
		<p>Phone: ${restaurant.phone}</p>
	</c:forEach>
</body>
</html>
<%@page import="java.sql.ResultSet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="database.RestaurantBean"%>
<%@page import="database.AccountBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Menu Finder - My Restaurants</title>
<link rel="stylesheet" href="css/stylesMain.css" type="text/css" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
</head>
<body class="blurBg-false">
<link rel="stylesheet" href="addmenu_files/formoid1/formoid-solid-green.css" type="text/css" />
<script type="text/javascript" src="addmenu_files/formoid1/jquery.min.js"></script>

<%
	
	AccountBean userId = (AccountBean) session.getAttribute("loggedUser");
%>
		<section id="body" class="width">
			<aside id="sidebar" class="column-left">

			<header>
				
				<img src="img/new_logo_android.png" width="125" height="117" style="padding-left: 40px">
				<h1><a href="#">MenuFinder</a></h1>
					<h2>Usuario: <%=userId.getId()%> </h2>			
			</header>
			
			<nav id="mainnav">
  				<ul>
                    <li class="selected-item"><a href="index.html">Home</a></li>
                    <li><a href="examples.html">Settings</a></li>
                    <li><a href="#">Contact</a></li>
                    <li>	<form method="get" action="sManageAccount">
							<input type="hidden" name="action" value="logout">
							<input type="submit" value="Log out">
					</form></li>
                            		
                </ul>
			</nav>		
			</aside>
			<section id="content" class="column-right">
                		
	    <article >
			<form method="get" action="sManageRestaurants">
			<input type="hidden" name="action" value="addrestaurant"> 
			<input type="submit" class="button" value="+ Add Restaurant">
			</form>
		</article>	
		<article class="expanded">            		
			<div class="article-info">MenuFInder DashBoard</div>	
	
<table>
					<tr>
						<th>Name</th>
						<th>CIF</th>
						<th>Address</th>
						<th>City</th>
						<th>Postal code</th>
						<th>State</th>
						<th>Country</th>
						<th>Email</th>
						<th>Phone</th>
						<th>Manage Restaurant</th>
						<th>Manage Others</th>
					</tr>
	<c:forEach var="restaurant" items="${restaurants}">
	<tr>
						<td>${restaurant.name}</td>
						<td>${restaurant.cif} </td>
						<td>${restaurant.address}</td>
						<td>${restaurant.city}</td>
						<td>${restaurant.postalCode}</td>
						<td> ${restaurant.state}</td>
						<td> ${restaurant.country}</td>
						<td> ${restaurant.email}</td>
						<td>  ${restaurant.phone}</td>
						<td>		
						<form method="get" action="sManageRestaurants">
			<input type="hidden" name="action" value="editrestaurant"> 
			<input type="hidden" name="accountid" value="${accountid}">
			<input type="hidden" name="restaurantid" value="${restaurant.id}">
			<input type="submit"  class="button" value="Edit restaurant">
		</form>
		<br>
		<form method="post" action="sManageRestaurants">
			<input type="hidden" name="action" value="deleterestaurant"> 
			<input type="hidden" name="accountid" value="${accountid}">
			<input type="hidden" name="restaurantid" value="${restaurant.id}">
			<input type="submit"  class="button" value="Delete restaurant" onclick="return confirm('Do you want to delete this restaurant?')">
		</form>
					</td>
					<td>
		<form method="get" action="sManageMenus">
			<input type="hidden" name="action" value="managemenus"> 
			<input type="hidden" name="restaurantid" value="${restaurant.id}">
			<input type="submit"  class="button" value="Manage menus">
		</form>
		<br>
		<form method="get" action="sManageItems">
			<input type="hidden" name="action" value="manageitems">
			<input type="hidden" name="restaurantid" value="${restaurant.id}">
			<input type="submit"  class="button" value="Manage items">
		</form>			
						
						</td>

					</tr>		

	</c:forEach>
		</table>		
			
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
		</article>
			
			<footer class="clear">
				<p>&copy; 2016. </p>
			</footer>

		</section>

		<div class="clear"></div>

	</section>
	
<script type="text/javascript" src="addmenu_files/formoid1/formoid-solid-green.js"></script>
</body>
</html>

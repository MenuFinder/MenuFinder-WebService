<%@page import="java.sql.ResultSet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="database.RestaurantBean"%>
<%@page import="database.ItemBean"%>
<%@page import="database.AccountBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu Finder - Manage Items</title>
<link rel="stylesheet" href="css/stylesMain.css" type="text/css" />

<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
</head>
<body class="blurBg-false">
<link rel="stylesheet" href="addmenu_files/formoid1/formoid-solid-green.css" type="text/css" />
<script type="text/javascript" src="addmenu_files/formoid1/jquery.min.js"></script>

<%
	long restaurantId =(long) session.getAttribute("restaurantid");
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
                    <li><a href="#">LogOut</a></li>
                            		
                </ul>
			</nav>		
			
			</aside>
			<section id="content" class="column-right">
			 <article>
			 <form method="get" action="sManageItems">

				<input type="hidden" name="action" value="additem"> 
				<input type="hidden" name="restaurantid" value="<%=restaurantId%>">
				<input type="submit" class="button" value="+ Add Items">
			
			</form>			
			</article>
		<article class="expanded">            		
			<div class="article-info">MenuFInder DashBoard</div>
			<table>
					<tr>					
						<th>Name</th>
						<th>Description</th>
						<th>Price</th>
						<th>Score</th>
						<th>Manage</th>
					</tr>
			
			<c:forEach var="item" items="${items}">
					<tr>
						<td>${item.name}</td>
						<td>${item.description} </td>
						<td>${item.price}</td>
					<td>
		<%			double menuScore = ((ItemBean)(pageContext.findAttribute("item"))).getScore();
		%>
							<form class="formoid-solid-green" style="box-shadow: 0 0px 0px;">
								<div class="element-rating">
							<div class="rating">
				<input type="radio" class="rating-input" id="rating-5" name="score"
					value="5" disabled readonly <%if ( menuScore >= 5) {%>
					checked="checked" <%}%> /> <label for="rating-5"
					class="rating-star"></label> <input type="radio"
					class="rating-input" id="rating-4" name="score" value="4" disabled
					readonly <%if ( menuScore >= 4 &&  menuScore < 5) {%>
					checked="checked" <%}%> /> <label for="rating-4"
					class="rating-star"></label> <input type="radio"
					class="rating-input" id="rating-3" name="score" value="3" disabled
					<%if ( menuScore >= 3 &&  menuScore < 4) {%> checked="checked" <%}%> />
				<label for="rating-3" class="rating-star"></label> <input
					type="radio" class="rating-input" id="rating-2" name="score"
					value="2" disabled readonly
					<%if (menuScore >= 2 && menuScore < 3) {%> checked="checked" <%}%> />
				<label for="rating-2" class="rating-star"></label> <input
					type="radio" class="rating-input" id="rating-1" name="score"
					value="1" enabled="false" disabled readonly
					<%if (menuScore >= 1 && menuScore < 2) {%> checked="checked" <%}%> />
				<label for="rating-1" class="rating-star"></label>
			</div>
								</div>
							</form>
						</td>
						<td>
							<form method="get" action="sManageItems">				
							<input type="hidden" name="action" value="edititem"> 
							<input type="hidden" name="itemid" value="${item.id}">
							<input type="submit"  class="button" value="Edit item">				
							</form>		
							<form method="post" action="sManageItems">				
								<input type="hidden" name="action" value="deleteitem"> 
								<input type="hidden" name="itemid" value="${item.id}"> 
								<input type="hidden" name="name" value="${item.name}"> 
								<input type="hidden" name="description" value="${item.description}"> 
								<input type="hidden" name="price" value="${item.price}"> 
								<input type="hidden" name="score" value="${item.score}"> 
								<input type="hidden" name="restaurantid" value="${item.restaurant}">
								<input type="submit" class="button button-reversed" value="Delete item" onclick="return confirm('Do you want to delete this item?')">				
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
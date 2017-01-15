<%@page import="database.MenuBean"%>
<%@page import="java.util.Map"%>
<%@page import="database.ItemCategoryBean"%>
<%@page import="database.ItemBean"%>
<%@page import="java.util.List"%>
<%@page import="database.AccountBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	long menuId = Long.parseLong(session.getAttribute("menuid").toString());
	MenuBean menu = MenuBean.getMenuById(menuId);
	List<ItemBean> restaurantItems = ItemBean.getRestaurantItems(menu.getRestaurant());
	List<ItemCategoryBean> categories = ItemCategoryBean.getAllBeans();
	AccountBean userId = (AccountBean) session.getAttribute("loggedUser");
%>
<title><%=menu.getName()%> Items</title>
<link rel="stylesheet" href="css/stylesMain.css" type="text/css" />
<link rel="stylesheet" href="css/back.css" type="text/css" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
</head>
<body class="blurBg-false">
<link rel="stylesheet" href="addmenu_files/formoid1/formoid-solid-green.css" type="text/css" /><
<script type="text/javascript" src="addrestaurant_files/formoid1/jquery.min.js"></script>
<script type="text/javascript" src="addmenu_files/formoid1/jquery.min.js"></script>
	<section id="body" class="width">
			<aside id="sidebar" class="column-left">

			<header>
				
				<img src="img/new_logo_android.png" width="125" height="117" style="padding-left: 40px">
				<h1><a href="#">MenuFinder</a></h1>
				<h2>User: <%=userId.getId()%> </h2>
			</header>

			<nav id="mainnav">
  				<ul>
                    <li><a href="jManageRestaurants">My restaurants</a></li>
                    <li><a href="sManageAccount?action=logout">Log out</a></li>
                            		
                </ul>
			</nav>		
			
			</aside>
				<section id="content" class="column-right">
                		
	    <article style="width: 1050px">
				<div class="title"><h2>Add Item to Menu</h2></div>
				<div class="element-address"><label class="title">		
	<form method="post" action="sManageMenus">
		<input type="hidden" name="action" value="addmenuitem">
		<input type="hidden" name="menuid" value="<%=menuId%>">
			<select name="itemid" >
			<option selected="selected" value="" disabled="disabled">--- Select a Item ---</option>
				<c:forEach var="item" items="<%=restaurantItems%>">
					<option value="${item.id}">${item.name}</option>
				</c:forEach>
			</select>
		
			<select name="categoryid" >
			<option selected="selected" value="" disabled="disabled">--- Select a Category ---</option>
				<c:forEach var="category" items="<%=categories%>">
					<option value="${category.id}">${category.name}</option>
				</c:forEach>
			</select>
		<div class="submit">
		<br>
			<input type="submit" class="button" value="+ Add Item">
		</div>
	</form>	
			</article>	
					<article class="expanded">            		
			<div class="article-info"><%=menu.getName()%> Items</div>
			
			
	<c:forEach var="menuitem" items="${menuitems}">
	<a href="#" class="collapsible-header"><%=ItemCategoryBean.getItemCategoryById(
						((Map.Entry<Long, List<ItemBean>>) pageContext.
								getAttribute("menuitem")).getKey()).getName()%><img src="img/ico_plegado.png" /></a>  
			<c:forEach var="item" items="${menuitem.value}">
				<table>
					<tr>
						<th>ID</th>
						<th>Name</th>						
						<th>Manage</th>
					</tr>
					
					<tr>
						<td>${item.id}</td>
						<td>${item.name} </td>						
						<td>						
						<form method="post" action="sManageMenus">
							<input type="hidden" name="action" value="deletemenuitem">
							<input type="hidden" name="itemid" value="${item.id}">
							<input type="hidden" name="menuid" value="<%=menuId%>">
							<input type="hidden" name="categoryid" value="${menuitem.key}">
							<input type="submit"  class="button" value="Delete item" onclick="return confirm('Do you want to delete this item of this menu?')">
						</form>					
						</td>

					</tr>	
					</table>					
			</c:forEach>

	</c:forEach>
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
<script type="text/javascript" src="addmenuitems_files/vendor/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="addmenuitems_files/vendor/jQuery.fileinput.js"></script>
<script type="text/javascript" src="addmenuitems_files/vendor/bootstrap-modal.js"></script>
<script type="text/javascript" src="addmenuitems_files/back.js"></script>
<script type="text/javascript" src="addmenuitems_files/main.js"></script>
</body>
</html>
